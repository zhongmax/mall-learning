package com.csmaxwell.mall.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * S
 * Created by maxwell on 2020/4/24.
 */
@Configuration
public class OssConfig {
   @Value("${oss.aliyun.endpoint}")
   private String ENDPOINT;
   @Value("${oss.aliyun.accessKeyId}")
   private String ACCESSKEY;
   @Value("${oss.aliyun.accessKeySecret}")
   private String SECRETKEY;

   @Bean
   public OSSClient ossClient() {
      return new OSSClient(ENDPOINT, ACCESSKEY, SECRETKEY);
   }
}
