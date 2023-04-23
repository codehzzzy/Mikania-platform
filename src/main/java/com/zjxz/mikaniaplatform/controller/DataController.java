package com.zjxz.mikaniaplatform.controller;

import com.zjxz.mikaniaplatform.job.DataCalculator;
import com.zjxz.mikaniaplatform.model.dto.DataResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hzzzzzy
 * @date 2023/4/23
 * @description 数据控制器
 */
@Tag(name = "2-数据管理")
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private DataCalculator dataCalculator;

    @ApiOperation("获取数据(前端5s调用一次)")
    @GetMapping("/getData")
    public DataResponse getData() {
        return dataCalculator.getData();
    }
}
