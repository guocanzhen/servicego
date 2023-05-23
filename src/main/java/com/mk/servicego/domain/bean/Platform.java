package com.mk.servicego.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author guocz
 * @date 2023/4/10 16:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "平台")
@TableName(value = "platform")
public class Platform extends BaseBean{

    @ApiModelProperty(value = "平台编号")
    @TableField(value = "num")
    private String num;

    @ApiModelProperty(value = "平台名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "备注")
    @TableField(value = "bz")
    private String bz;
}
