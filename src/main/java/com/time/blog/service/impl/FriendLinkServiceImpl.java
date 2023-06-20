package com.time.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.time.blog.domain.entity.FriendLink;
import com.time.blog.mapper.FriendLinkMapper;
import com.time.blog.service.FriendLinkService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author mjw
 * @date 2023/6/20
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    private final FriendLinkMapper friendLinkMapper;

    public FriendLinkServiceImpl(FriendLinkMapper friendLinkMapper) {
        this.friendLinkMapper = friendLinkMapper;
    }

    @Override
    public List<FriendLink> selectFriendLink() {
        LambdaQueryWrapper<FriendLink> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(FriendLink::getCreationDate);
        return friendLinkMapper.selectList(queryWrapper);
    }

    @Override
    public void addFriendLink(FriendLink friendLink) {
        friendLink.setCreationDate(new Date());
        friendLink.setDeleteFlag("N");
        friendLinkMapper.insert(friendLink);
    }
}
