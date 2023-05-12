package com.time.blog.service;

import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.Comments;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mjw
 * @date 2023/5/9
 */
public interface CommentsService {

    /**
     * 根据条件查询评论
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @param articleId articleId
     * @return pageInfo
     */
    PageInfo<Comments> selectCommentsByCondition(int pageNum, int pageSize, Long articleId);

    /**
     * 新增一条评论
     * @param comments comments
     * @param request request
     */
    void addComments(Comments comments, HttpServletRequest request) throws Exception;

    /**
     * 删除一条评论
     * @param commentsId commentsId
     */
    void deleteComments(Long commentsId);

}
