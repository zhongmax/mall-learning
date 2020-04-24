package com.csmaxwell.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * S
 * Created by maxwell on 2020/4/23
 */
@Configuration
@MapperScan({"com.csmaxwell.mall.mbg.mapper", "com.csmaxwell.mall.dao"})
public class MyBatisConfig {
}
