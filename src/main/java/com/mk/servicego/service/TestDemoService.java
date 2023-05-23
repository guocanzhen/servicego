package com.mk.servicego.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.servicego.domain.bean.TestDemo;
import com.mk.servicego.domain.dto.TestDemoDto;
import com.mk.servicego.domain.result.ResultForm;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author guocz
 * @date 2023/4/10 11:44
 */
public interface TestDemoService extends IService<TestDemo> {

    /**
     * 保存或更新
     * @param request 图片
     * @param dto 测试
     * @return ResultForm
     */
    ResultForm saveOrUpdate(MultipartHttpServletRequest request, TestDemoDto dto);
}
