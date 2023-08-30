package com.mk.servicego.controller;

import com.mk.servicego.domain.dto.TestDemoDto;
import com.mk.servicego.domain.result.ContentResultForm;
import com.mk.servicego.domain.result.ResultForm;
import com.mk.servicego.service.TestDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author guocz
 * @date 2023/4/10 11:46
 */
@Api(tags = {"测试"})
@Log4j2
@RestController
@RequestMapping("test/testdemo")
public class TestDemoController {

    @Autowired
    private TestDemoService service;

    @GetMapping(value = "addhtml")
    public ModelAndView testdemoAdd() {
        ModelAndView view = new ModelAndView();
        view.setViewName("testdemoAdd");
        return view;
    }

    @ApiOperation(value = "新增或更新，更新操作id必需", notes = "新增或更新，更新操作id必需")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", dataType = "String"),
            @ApiImplicitParam(name = "column1", value = "字段1", dataType = "String"),
            @ApiImplicitParam(name = "column2", value = "字段2", dataType = "String"),
            @ApiImplicitParam(name = "column3", value = "字段3", dataType = "String"),
            @ApiImplicitParam(name = "column4", value = "字段4", dataType = "String"),
            @ApiImplicitParam(name = "column5", value = "字段5", dataType = "String")
    })
    @PostMapping("saveOrUpdate")
    public ResultForm saveOrUpdate(MultipartHttpServletRequest request, TestDemoDto dto) {
        return service.saveOrUpdate(request, dto);
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键id", dataType = "String")
    @GetMapping("delete")
    public ResultForm delete(String id) {
        if (service.removeById(id)) {
            return new ResultForm(true, "删除成功");
        }
        return new ResultForm(false, "删除失败");
    }

    @ApiOperation(value = "逻辑删除")
    @ApiImplicitParam(name = "id", value = "主键id", dataType = "String")
    @GetMapping("logicDelete")
    public ResultForm logicDelete(String id) {
        return null;
    }

    public ResultForm page() {
        return null;
    }

    @ApiOperation(value = "获取所有数据")
    @GetMapping("list")
    public ResultForm list() {
        return new ContentResultForm<>(true, service.list());
    }


}
