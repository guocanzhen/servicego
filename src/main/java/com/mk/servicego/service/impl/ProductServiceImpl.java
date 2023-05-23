package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.Product;
import com.mk.servicego.mapper.ProductMapper;
import com.mk.servicego.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author guocz
 * @date 2023/4/10 17:23
 */
@Service
@Log4j2
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
