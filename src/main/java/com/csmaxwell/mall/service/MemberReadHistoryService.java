package com.csmaxwell.mall.service;

import com.csmaxwell.mall.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * S
 * Created by maxwell on 2020/4/24.
 */
public interface MemberReadHistoryService {
    /**
     * 生成浏览记录
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览历史记录
     */
    List<MemberReadHistory> list(Long memberId);
}
