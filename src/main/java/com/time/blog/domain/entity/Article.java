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

import javax.persistence.Transient;
import java.util.Date;

/**
 * @author mjw
 * @date 2023/4/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tm_article")
@ApiModel("文章")
public class Article {

    @ApiModelProperty("文章id")
    @TableId
    private Long articleId;

    @ApiModelProperty("分类id，关联tm_category的category_id")
    private Long categoryId;

    @ApiModelProperty("作者id")
    private Long userId;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章图片路径，页面展示的图片和详情页面的背景图片，存放在minio中")
    private String articlePicture;

    @ApiModelProperty("删除标识，Y表示删除，N表示未删除")
    private String deleteFlag;

    @ApiModelProperty("创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;

    @ApiModelProperty("最后更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    @Transient
    @ApiModelProperty("类型名称：原创、转载等")
    private String categoryName;

    @Transient
    @ApiModelProperty("创建时间从")
    private String creationDateFrom;

    @Transient
    @ApiModelProperty("创建时间至")
    private String creationDateTo;
}
