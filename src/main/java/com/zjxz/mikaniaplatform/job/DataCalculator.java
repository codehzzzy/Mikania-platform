package com.zjxz.mikaniaplatform.job;

import com.zjxz.mikaniaplatform.controller.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hzzzzzy
 * @date 2023/4/23
 * @description 定时更新数据
 */
@Component
public class DataCalculator {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WebSocketServer webSocketServer;

    /**
     * 每 5 秒钟计算一次数据
     */
    @Scheduled(fixedRate = 100000)
    private void calculateData() {
        // todo: 计算数据的逻辑
        // todo: 发送数据
        // DataRespons dataRespons = new DataRespons();
        // webSocketServer.sendMessage(dataRespons);
    }
}