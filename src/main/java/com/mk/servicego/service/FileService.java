package com.mk.servicego.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mk.servicego.domain.bean.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author guocz
 * @date 2023/5/22 16:43
 */
public interface FileService extends IService<File> {

    /**
     * 文件上传
     * @param files 文件
     * @param groupId 文件组id集
     * @return 结果
     */
    String upload(List<MultipartFile> files, String groupId);

    /**
     * 文件下载
     * @param url 文件相对路径
     */
    void download(String url);

    /**
     * 获取文件访问路径
     * @param url 文件相对路径
     * @return 结果
     */
    String getUrl(String url);

    /**
     * 文件删除（物理删除）
     * @param id 文件id
     */
    void deleteById(String id);

    /**
     * 批量删除（物理删除）
     * @param groupId 文件组id集
     */
    void deleteByGroupId(String groupId);

    /**
     * 文件删除（逻辑删除）
     * @param id 文件id
     */
    void useless(String id);

}
