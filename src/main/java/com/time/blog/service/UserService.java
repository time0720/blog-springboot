package com.time.blog.service;

import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.User;

/**
 * @author mjw
 * @date 2023/6/15
 */
public interface UserService {

    /**
     * 根据条件批量查询user
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @param user user
     * @return List<User>
     */
    PageInfo<User> selectUserByCondition(int pageNum, int pageSize, User user);
}
