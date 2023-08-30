package com.mk.servicego.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author guocz
 * @date 2023/5/22 16:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "文件")
@TableName(value = "file")
public class File extends BaseBean{

    @ApiModelProperty(value = "文件组id")
    @TableField(value = "group_id")
    private String groupId;

    @ApiModelProperty(value = "文件名")
    @TableField(value = "file_name")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    @TableField(value = "path")
    private String path;

    @ApiModelProperty(value = "文件相对路径")
    @TableField(value = "relative_url")
    private String relativeUrl;

    @ApiModelProperty(value = "逻辑删除：1-有效；0-无效")
    @TableField(value = "useful")
    private String useful="1";
}
