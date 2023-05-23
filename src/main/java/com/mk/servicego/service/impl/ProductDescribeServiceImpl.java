package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.ProductDescribe;
import com.mk.servicego.mapper.ProductDescribeMapper;
import com.mk.servicego.service.ProductDescribeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author guocz
 * @date 2023/4/11 16:01
 */
@Service
@Log4j2
public class ProductDescribeServiceImpl extends ServiceImpl<ProductDescribeMapper, ProductDescribe> implements ProductDescribeService {
}
