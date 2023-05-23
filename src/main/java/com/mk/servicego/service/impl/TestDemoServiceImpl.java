package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.File;
import com.mk.servicego.domain.bean.TestDemo;
import com.mk.servicego.domain.dto.TestDemoDto;
import com.mk.servicego.domain.result.ContentResultForm;
import com.mk.servicego.domain.result.ResultForm;
import com.mk.servicego.mapper.TestDemoMapper;
import com.mk.servicego.service.FileService;
import com.mk.servicego.service.TestDemoService;
import com.mk.servicego.util.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @author guocz
 * @date 2023/4/10 11:45
 */
@Service
@Log4j2
public class TestDemoServiceImpl extends ServiceImpl<TestDemoMapper, TestDemo> implements TestDemoService {

    @Autowired
    private FileService fileService;

    /**
     * 保存或更新
     * @param request 图片
     * @param dto 测试
     * @return ResultForm
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultForm saveOrUpdate(MultipartHttpServletRequest request, TestDemoDto dto) {
        TestDemo demo = new TestDemo();
        BeanUtils.copyProperties(dto, demo);
        String groupId = IdWorker.get32UUID();
        demo.setFileId(groupId);
        saveOrUpdate(demo);
        List<MultipartFile> files = request.getFiles("files");
        files.forEach(item -> {
            File file = new File();
            file.setFileName(item.getOriginalFilename());
            file.setGroupId(groupId);
            file.setPath(FileUtil.saveMultipartFile(item, "D:\\tmp"));
            fileService.save(file);
        });
        return new ContentResultForm<>(true, getById(demo.getId()));
    }
}
