package com.mk.servicego.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author guocz
 * @date 2023/4/10 17:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "产品")
@TableName(value = "product")
public class Product extends BaseBean{

    @ApiModelProperty(value = "产品编号")
    @TableField(value = "num")
    private String num;

    @ApiModelProperty(value = "产品名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "备注")
    @TableField(value = "bz")
    private String bz;

}
