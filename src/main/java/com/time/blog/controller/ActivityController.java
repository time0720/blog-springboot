package com.time.blog.controller;

import com.time.blog.domain.entity.Activity;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mjw
 * @date 2023/6/16
 */
@RestController
@Api(tags = "时间线活动管理")
@Slf4j
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @ApiOperation("查询所有的时间线活动")
    @GetMapping("/activity/selectAll")
    public ResponseResult<List<Activity>> selectAll() {
        return ResponseResult.success(activityService.selectAll());
    }

    @ApiOperation("新增或修改活动")
    @PostMapping("/admin/saveActivity")
    public ResponseResult<Void> saveActivity(@RequestBody Activity activity) {
        activityService.saveActivity(activity);
        return ResponseResult.success();
    }

}
