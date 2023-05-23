package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.AfterSalesOrder;
import com.mk.servicego.mapper.AfterSalesOrderMapper;
import com.mk.servicego.service.AfterSalesOrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author guocz
 * @date 2023/4/11 15:50
 */
@Service
@Log4j2
public class AfterSalesOrderServiceImpl extends ServiceImpl<AfterSalesOrderMapper, AfterSalesOrder> implements AfterSalesOrderService {
}
