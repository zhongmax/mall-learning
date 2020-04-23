package com.csmaxwell.mall.service;

/**
 * redis 操作 Service
 * 对象和数组都以 json 形式进行存储
 * Created by maxwell on 2020/4/23.
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     */
    Long increment(String key, long delta);
}
