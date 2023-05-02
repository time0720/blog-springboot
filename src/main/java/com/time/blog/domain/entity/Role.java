package com.time.blog.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@ApiModel("角色表")
@TableName("tm_role")
public class Role {

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色的身份key，例如admin、user")
    private String roleKey;

    @ApiModelProperty("删除标识，Y表示删除，N表示未删除")
    private String deleteFlag;

    @ApiModelProperty("创建日期")
    private Date creationDate;

    @ApiModelProperty("最后更新时间")
    private Date lastUpdateTime;

    @ApiModelProperty("备注")
    private String remark;

}
