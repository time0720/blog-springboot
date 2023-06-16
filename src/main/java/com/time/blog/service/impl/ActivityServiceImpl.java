package com.time.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.time.blog.domain.entity.Activity;
import com.time.blog.mapper.ActivityMapper;
import com.time.blog.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author mjw
 * @date 2023/6/16
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityMapper activityMapper;

    @Autowired
    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    @Override
    public List<Activity> selectAll() {
        LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Activity::getActivityId, Activity::getTimestamp);
        return activityMapper.selectList(queryWrapper);
    }

    @Override
    public void saveActivity(Activity activity) {
        if (Objects.isNull(activity)) {
            return;
        }
        // 判断是新增还是修改
        if (activity.getActivityId() == null) {
            // 默认为large尺寸
            activity.setSize("large");
            activityMapper.insert(activity);
        } else {
            activityMapper.updateById(activity);
        }
    }
}
