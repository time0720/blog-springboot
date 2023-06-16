package com.time.blog.service;

import com.time.blog.domain.entity.Activity;

import java.util.List;

/**
 * @author mjw
 * @date 2023/6/16
 */
public interface ActivityService {

    /**
     * 查询所有的时间线活动
     * @return list
     */
    List<Activity> selectAll();

    /**
     * 新增或修改活动
     * @param activity activity
     */
    void saveActivity(Activity activity);
}
