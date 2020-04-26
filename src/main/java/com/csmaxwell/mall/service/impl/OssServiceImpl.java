package com.csmaxwell.mall.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.csmaxwell.mall.dto.OssCallbackParam;
import com.csmaxwell.mall.dto.OssCallbackResult;
import com.csmaxwell.mall.dto.OssPolicyResult;
import com.csmaxwell.mall.service.OssService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * oss上传管理Service实现类
 * Created by maxwell on 2020/4/26.
 */
@Service
public class OssServiceImpl implements OssService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OssServiceImpl.class);
    @Value("${oss.aliyun.policy.expire}")
    private int expire;
    @Value("${oss.aliyun.maxSize}")
    private int maxSize;
    @Value("${oss.aliyun.callback}")
    private String callback;
    @Value("${oss.aliyun.bucketName}")
    private String bucketName;
    @Value("${oss.aliyun.endpoint}")
    private String endpoint;
    @Value("${oss.aliyun.dir.prefix}")
    private String dirPrefix;

    @Autowired
    private OSSClient ossClient;

    /**
     * 签名生成
     * @return
     */
    @Override
    public OssPolicyResult policy() {
        OssPolicyResult result = new OssPolicyResult();
        // 存储目录
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dir = dirPrefix + sdf.format(new Date());
        // 签名有效期
        long expireEndTime = System.currentTimeMillis() + expire * 1000;
        Date expiration = new Date(expireEndTime);
        // 文件大小
        long size = maxSize * 1024 * 1024;
        // 回调
        OssCallbackParam callbackParam = new OssCallbackParam();
        callbackParam.setCallbackUrl(callback);
        callbackParam.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
        callbackParam.setCallbackBodyType("application/x-www-form-urlencoded");
        // 提交节点
        String action = "http://" + bucketName + "." + endpoint;
        try {
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, size);
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String policy = BinaryUtil.toBase64String(binaryData);
            String signature = ossClient.calculatePostSignature(postPolicy);
            String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callbackParam).toString().getBytes("utf-8"));
            // 返回结果
            result.setAccessKeyId(ossClient.getCredentialsProvider().getCredentials().getAccessKeyId());
            result.setPolicy(policy);
            result.setSignature(signature);
            result.setDir(dir);
            result.setCallback(callback);
            result.setHost(action);
        } catch (Exception e) {
            LOGGER.error("签名生成失败", e);
        }
        return result;
    }

    @Override
    public OssCallbackResult callback(HttpServletRequest request) {
        OssCallbackResult result = new OssCallbackResult();
        String filename = request.getParameter("filename");
        filename = "http://".concat(bucketName).concat(".").concat(endpoint).concat("/").concat(filename);
        result.setFilename(filename);
        result.setSize(request.getParameter("size"));
        result.setMimeType(request.getParameter("mimeType"));
        result.setWidth(request.getParameter("width"));
        result.setHeight(request.getParameter("height"));
        return result;
    }
}
