package com.time.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author mjw
 * @date 2023/5/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tm_log_model")
public class LogModel {

    @ApiModelProperty("表ID，主键")
    private Long modelId;

    @ApiModelProperty("操作类型编码")
    private String modelCode;

    @ApiModelProperty("操作类型名称")
    private String modelName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
}
