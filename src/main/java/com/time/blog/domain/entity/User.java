package com.time.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mjw
 * @date 2023/4/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户表")
@TableName("tm_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户登陆名称")
    private String userName;

    @ApiModelProperty("别名，显示用")
    private String nickName;

    @ApiModelProperty("密码(加密)")
    private String password;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("删除标识，Y表示删除，N表示未删除")
    private String deleteFlag;

    @ApiModelProperty("创建日期")
    private Date creationDate;

    @ApiModelProperty("最后更新时间")
    private Date lastUpdateTime;

}
