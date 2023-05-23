package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.Platform;
import com.mk.servicego.mapper.PlatformMapper;
import com.mk.servicego.service.PlatformService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author guocz
 * @date 2023/4/10 17:02
 */
@Service
@Log4j2
public class PlatformServiceImpl extends ServiceImpl<PlatformMapper, Platform> implements PlatformService {
}
