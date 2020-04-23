package com.csmaxwell.mall.service;

import com.csmaxwell.mall.common.api.CommonResult;

/**
 * S
 * Created by maxwell on 2020/4/23.
 */
public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
