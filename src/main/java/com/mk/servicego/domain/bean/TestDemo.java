package com.mk.servicego.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author guocz
 * @date 2023/4/10 10:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "测试表")
@TableName(value = "test_demo")
public class TestDemo extends BaseBean{

    @ApiModelProperty(value = "字段1")
    @TableField(value = "Column1")
    private String column1;

    @ApiModelProperty(value = "字段2")
    @TableField(value = "Column2")
    private String column2;

    @ApiModelProperty(value = "字段3")
    @TableField(value = "Column3")
    private String column3;

    @ApiModelProperty(value = "字段4")
    @TableField(value = "Column4")
    private String column4;

    @ApiModelProperty(value = "字段5")
    @TableField(value = "Column5")
    private String column5;

    @ApiModelProperty(value = "文件组id")
    @TableField(value = "file_id")
    private String fileId;

}
