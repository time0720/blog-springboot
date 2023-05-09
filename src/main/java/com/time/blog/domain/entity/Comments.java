package com.time.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author mjw
 * @date 2023/5/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tm_comments")
@ApiModel("评论")
public class Comments {

    @ApiModelProperty("评论ID，主键")
    @TableId
    private Long commentsId;

    @ApiModelProperty("评论人名称")
    private String commentsName;

    @ApiModelProperty("评论人的IP")
    private String ip;

    @ApiModelProperty("评论人的地域地址")
    private String address;

    @ApiModelProperty("评论的时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @ApiModelProperty("评论人的头像信息")
    private String via;

    @ApiModelProperty("评论的内容")
    private String content;

    @ApiModelProperty("上级评论ID，如果存在，默认此条为回复的评论")
    private Long parentId;

    @ApiModelProperty("点赞数")
    private String upvote;

    @ApiModelProperty("关联的文章ID，如果为-1，则代表为评论区的评论")
    private Long articleId;
}
