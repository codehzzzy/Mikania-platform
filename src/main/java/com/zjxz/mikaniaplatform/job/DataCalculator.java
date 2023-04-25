package com.zjxz.mikaniaplatform.job;

import com.zjxz.mikaniaplatform.model.dto.DataResponse;
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

    /**
     * 保存计算出的数据
     */
    private DataResponse dataResponse;

    /**
     * 每 5 秒钟计算一次数据
     */
    @Scheduled(fixedRate = 100000)
    private void calculateData() {
        // TODO: 计算数据的逻辑
        dataResponse = null;
    }

    /**
     * 获取计算出的数据
     */
    public DataResponse getData() {
        return dataResponse;
    }
}