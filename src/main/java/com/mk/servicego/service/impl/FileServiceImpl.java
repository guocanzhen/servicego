package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.File;
import com.mk.servicego.mapper.FileMapper;
import com.mk.servicego.service.FileService;
import org.springframework.stereotype.Service;

/**
 * @author guocz
 * @date 2023/5/22 16:44
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
}
