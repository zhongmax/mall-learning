package com.csmaxwell.mall.service;

import com.csmaxwell.mall.mbg.model.UmsAdmin;
import com.csmaxwell.mall.mbg.model.UmsPermission;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/4/23.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的 JWT 的 token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

}
