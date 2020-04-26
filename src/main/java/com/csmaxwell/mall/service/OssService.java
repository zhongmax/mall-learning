package com.csmaxwell.mall.service;

import com.csmaxwell.mall.dto.OssCallbackResult;
import com.csmaxwell.mall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * Created by maxwell on 2020/4/26.
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
