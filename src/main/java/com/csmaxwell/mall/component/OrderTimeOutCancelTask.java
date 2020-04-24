package com.csmaxwell.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单超时取消并解锁库存的定时器
 * Created by maxwell on 2020/4/24.
 */
@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron 表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时间之前下的订单，如果没有支付则取消订单
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO 2020/4/24 此处应调用取消订单的方法，具体查看 mall 项目源码
        LOGGER.info("取消订单，并根据sku编号释放锁定库存");
    }
}
