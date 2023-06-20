package com.time.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @date 2023/6/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tm_friend_link")
@ApiModel("评论")
public class FriendLink {

    @ApiModelProperty("表ID，主键")
    @TableId(type = IdType.AUTO)
    private Long linkId;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("友链地址")
    private String linkUrl;

    @ApiModelProperty("友链名称")
    private String linkName;

    @ApiModelProperty("友链添加时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @ApiModelProperty("删除标识")
    private String deleteFlag;

}
