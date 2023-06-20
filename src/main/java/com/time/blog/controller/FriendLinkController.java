package com.time.blog.controller;

import com.time.blog.domain.entity.FriendLink;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.FriendLinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mjw
 * @date 2023/6/20
 */
@RestController
@Api(tags = "友链管理")
@Slf4j
public class FriendLinkController {

    private final FriendLinkService friendLinkService;

    public FriendLinkController(FriendLinkService friendLinkService) {
        this.friendLinkService = friendLinkService;
    }

    @ApiOperation("/查询所有的友链信息")
    @GetMapping("/friend/selectFriendLink")
    public ResponseResult<List<FriendLink>> selectFriendLink() {
        return ResponseResult.success(friendLinkService.selectFriendLink());
    }

    @ApiOperation("/添加一个友链信息")
    @PostMapping("/admin/addFriendLink")
    public ResponseResult<Void> addFriendLink(@RequestBody FriendLink friendLink) {
        friendLinkService.addFriendLink(friendLink);
        return ResponseResult.success();
    }
}
