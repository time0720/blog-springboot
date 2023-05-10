package com.time.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author majianwei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tm_log")
public class Log {

    @ApiModelProperty("表ID，主键")
    @TableId
    private Long id;

    @ApiModelProperty("用户编码")
    private String userCode;

    @ApiModelProperty("ip")
    private String ip;

    @ApiModelProperty("操作类型")
    private String type;

    @ApiModelProperty("操作描述")
    private String description;

    @ApiModelProperty("类型编码")
    private String modelCode;

    @ApiModelProperty("操作时间")
    private Date operationTime;

    @ApiModelProperty("返回的结果")
    private String result;

}