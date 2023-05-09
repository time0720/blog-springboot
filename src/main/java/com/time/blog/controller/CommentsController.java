package com.time.blog.controller;

import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.Comments;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.CommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mjw
 * @date 2023/5/9
 */
@RestController
@Api(tags = "评论区管理")
@Slf4j
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @ApiOperation("根据条件查询评论")
    @GetMapping("/comments/selectComments")
    public ResponseResult<PageInfo<Comments>> selectCommentsByCondition(int pageNum, int pageSize, Long articleId) {
        return ResponseResult.success(commentsService.selectCommentsByCondition(pageNum, pageSize, articleId));
    }

    @ApiModelProperty("新增一条评论")
    @PostMapping("/comments/addComments")
    public ResponseResult<Void> addComments(@RequestBody Comments comments, HttpServletRequest request) throws Exception {
        commentsService.addComments(comments, request);
        return ResponseResult.success();
    }
}
