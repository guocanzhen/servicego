package com.mk.servicego.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author guocz
 * @date 2023/4/11 15:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "售后单")
@TableName(value = "after_sales_order")
public class AfterSalesOrder extends BaseBean{

    @ApiModelProperty(value = "平台id")
    @TableField(value = "platform_id")
    private String platformId;

    @ApiModelProperty(value = "产品明细组（挂单号）")
    @TableField(value = "group_id")
    private String groupId;

    @ApiModelProperty(value = "单号")
    @TableField(value = "bill_no")
    private String billNo;

    @ApiModelProperty(value = "物流状态：0-配送中；1-已到；")
    @TableField(value = "logistics_status")
    private String logisticsStatus;

    @ApiModelProperty(value = "寄出日期")
    @TableField(value = "send_time")
    private Date sendTime;

    @ApiModelProperty(value = "售后原因")
    @TableField(value = "reason")
    private String reason;

    @ApiModelProperty(value = "处理客户：1-退货退款；")
    @TableField(value = "deal_way")
    private String dealWay;

    @ApiModelProperty(value = "处理机器：1-验货；")
    @TableField(value = "machine")
    private String machine;

    @ApiModelProperty(value = "产品问题：1-没问题；2-已到货；3-报废配件包材；4-报废配件；")
    @TableField(value = "pro_matter")
    private String proMatter;

    @ApiModelProperty(value = "平台完结状态：1-已退款；")
    @TableField(value = "plat_end_status")
    private String platEndStatus;

    @ApiModelProperty(value = "备注")
    @TableField(value = "bz")
    private String bz;

}
