package com.mk.servicego.domain.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author guocz
 * @date 2023/4/11 15:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "产品明细")
@TableName(value = "product_describe")
public class ProductDescribe extends BaseBean{

    @ApiModelProperty(value = "售后单id")
    @TableField(value = "after_sales_order_id")
    private String afterSalesOrderId;

    @ApiModelProperty(value = "产品id")
    @TableField(value = "product_id")
    private String productId;

}
