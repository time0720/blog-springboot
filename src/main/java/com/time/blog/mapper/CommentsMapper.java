package com.time.blog.mapper;

import com.time.blog.domain.entity.Comments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mjw
 * @date 2023/5/9
 */
@Mapper
public interface CommentsMapper {

    /**
     * 根据条件查询评论
     * @param articleId articleId
     * @return list
     */
    List<Comments> selectCommentsByCondition(Long articleId);

    /**
     * 删除一条评论
     * @param comments comments
     */
    void addComments(Comments comments);

    /**
     * 删除一条评论
     * @param commentsId commentsId
     */
    void deleteComments(Long commentsId);
}
