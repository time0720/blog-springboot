package com.time.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mjw
 * @date 2023/6/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tm_activity")
@ApiModel("时间线活动")
public class Activity {

    @ApiModelProperty("时间线ID，主键")
    @TableId(type = IdType.AUTO)
    private Long activityId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("时间戳")
    private String timestamp;

    @ApiModelProperty("尺寸")
    private String size;

    @ApiModelProperty("颜色")
    private String color;

    @ApiModelProperty("百分比")
    private String percentage;
}
