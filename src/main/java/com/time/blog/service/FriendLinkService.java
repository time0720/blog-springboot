package com.time.blog.service;

import com.time.blog.domain.entity.FriendLink;

import java.util.List;

/**
 * @author mjw
 * @date 2023/6/20
 */
public interface FriendLinkService {

    /**
     * 查询所有的友链信息
     * @return list
     */
    List<FriendLink> selectFriendLink();

    /**
     * 添加一个友链信息
     * @param friendLink friendLink
     */
    void addFriendLink(FriendLink friendLink);
}
