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
@ApiModel("菜单表")
@TableName("tm_menu")
public class Menu {

    @ApiModelProperty("菜单id")
    private Long menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("路由地址，存放该菜单的路由信息")
    private String routerPath;

    @ApiModelProperty("组件路径信息")
    private String componentPath;

    @ApiModelProperty("菜单显示状态：0显示，1隐藏")
    private String visible;

    @ApiModelProperty("启用标识：0正常，1停用")
    private String enabledFlag;

    @ApiModelProperty("权限标识")
    private String permissions;

    @ApiModelProperty("删除标识，Y表示删除，N表示未删除")
    private String deleteFlag;

    @ApiModelProperty("创建日期")
    private Date creationDate;

    @ApiModelProperty("最后更新时间")
    private Date lastUpdateTime;

    @ApiModelProperty("备注")
    private String remark;
}
