package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.Logistics;
import com.mk.servicego.mapper.LogisticsMapper;
import com.mk.servicego.service.LogisticsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author guocz
 * @date 2023/4/11 15:12
 */
@Service
@Log4j2
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements LogisticsService {
}
