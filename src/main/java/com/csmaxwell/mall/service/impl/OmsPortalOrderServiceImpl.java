package com.csmaxwell.mall.service.impl;

import com.csmaxwell.mall.common.api.CommonResult;
import com.csmaxwell.mall.component.CancelOrderSender;
import com.csmaxwell.mall.dto.OrderParam;
import com.csmaxwell.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 前台订单管理Service
 * Created by maxwell on 2020/4/24.
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        // TODO 执行一系列下单操作，具体参考 mall 项目
        LOGGER.info("process generateOrder");
        // 下单完成后开启一个延迟消息，用于当用户没有付款时取消订单 (orderId应该下单后生成)
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null, "下单成功");
    }

    @Override
    public void cancelOrder(Long orderId) {
        // todo 执行一系列取消订单操作，具体参考 mall 项目
        LOGGER.info("process cancelOrder orderId: {}", orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        // 获取订单超时时间，假设60分钟
        long delayTime = 30 * 10000;
        // 发送延迟信息
        cancelOrderSender.sendMessage(orderId, delayTime);
    }
}
