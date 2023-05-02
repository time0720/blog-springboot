package com.time.blog.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author mjw
 * @date 2023/4/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分类")
public class Category {

    @ApiModelProperty("类型id")
    private Long categoryId;

    @ApiModelProperty("类型名称：原创、转载等")
    private String categoryName;

    @ApiModelProperty("创建日期")
    private Date creationDate;

    @ApiModelProperty("最后更新时间")
    private Date lastUpdateTime;

}
