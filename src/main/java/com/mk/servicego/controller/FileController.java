package com.mk.servicego.controller;

import com.mk.servicego.domain.result.ContentResultForm;
import com.mk.servicego.domain.result.ResultForm;
import com.mk.servicego.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

/**
 * @author guocz
 * @date 2023/8/30 14:01
 */
@Api(tags = {"文件"})
@Log4j2
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService service;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public ResultForm upload(MultipartHttpServletRequest request, String groupId) {
        List<MultipartFile> files = request.getFiles("files");
        return new ContentResultForm<>(true, service.upload(files, groupId));
    }

    @ApiOperation(value = "文件下载")
    @GetMapping("download")
    public ResultForm download(@RequestParam("url") String url) {
        service.download(url);
        return new ResultForm(true, "下载成功");
    }

    @ApiOperation(value = "获取文件访问路径")
    @GetMapping("getUrl")
    public ResultForm getUrl(@RequestParam("url") String url) {
        return new ContentResultForm<>(true, service.getUrl(url));
    }

    @ApiOperation(value = "物理删除")
    @GetMapping("deleteById")
    public ResultForm deleteById(@RequestParam("id") String id) {
        service.deleteById(id);
        return new ResultForm(true);
    }

    @ApiOperation(value = "批量删除（物理删除）")
    @GetMapping("deleteByGroupId")
    public ResultForm deleteByGroupId(@RequestParam("groupId") String groupId) {
        service.deleteByGroupId(groupId);
        return new ResultForm(true);
    }

    @ApiOperation(value = "文件删除（逻辑删除）")
    @GetMapping("useless")
    public ResultForm useless(String id) {
        service.useless(id);
        return new ResultForm(true);
    }

}
