package com.mk.servicego.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mk.servicego.domain.bean.File;
import com.mk.servicego.mapper.FileMapper;
import com.mk.servicego.service.FileService;
import com.mk.servicego.util.MinioClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author guocz
 * @date 2023/5/22 16:44
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {

    @Autowired
    private MinioClientUtil minioClientUtil;

    @Autowired
    private HttpServletResponse response;

    @Value("${minio.bucketName}")
    private String bucketName;

    /**
     * 文件上传
     * @param files 文件
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upload(List<MultipartFile> files, String groupId) {
        for (MultipartFile file : files) {
            File file1 = new File();
            //拿到图片  MultipartFile封装接受的类
            //拿到图片的名称
            String filename = file.getOriginalFilename();
            file1.setFileName(filename);

            //拿到图片的 UUId + 图片类型 (解决图片重名的问题 )
            String uuid = UUID.randomUUID().toString();
            assert filename != null;
            String imgType = filename.substring(filename.lastIndexOf("."));

            //图片文件的新名称 xxx/uuid.jpg   图片拼接后的名
            String fileName = uuid + imgType;


            try {
                minioClientUtil.putObject(fileName, file.getInputStream());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            String path = "/" + bucketName + "/" + fileName;
            String objectUrl;
            try {
                objectUrl = minioClientUtil.getObjectUrl(fileName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            file1.setPath(objectUrl);
            file1.setRelativeUrl(path);
            file1.setGroupId(groupId);
            save(file1);
        }
        return groupId;
    }

    /**
     * 文件下载
     * @param url 文件相对路径
     */
    @Override
    public void download(String url) {
        String trim = url.trim();
        String path = trim.substring(trim.indexOf("/", 1));
        minioClientUtil.getObject(path,response);
    }

    /**
     * 获取文件访问路径
     * @param url 文件相对路径
     * @return 结果
     */
    @Override
    public String getUrl(String url) {
        String trim = url.trim();
        String path = trim.substring(trim.indexOf("/", 1));
        path = path.substring(1);
        try {
            return minioClientUtil.getObjectUrl(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件删除
     * @param id 文件id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        File byId = getById(id);
        String relativeUrl = byId.getRelativeUrl();
        String trim = relativeUrl.trim();
        String path = trim.substring(trim.indexOf("/", 1));
        path = path.substring(1);
        try {
            minioClientUtil.removeObject(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        removeById(id);
    }

    /**
     * 批量删除（物理删除）
     * @param groupId 文件组id集
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByGroupId(String groupId) {
        List<File> files = baseMapper.selectList(Wrappers.<File>lambdaQuery().eq(File::getGroupId, groupId));
        List<String> urls = files.stream().map(File::getRelativeUrl).collect(Collectors.toList());
        List<String> paths = new ArrayList<>();
        for (String url : urls) {
            String trim = url.trim();
            String path = trim.substring(trim.indexOf("/", 1));
            path = path.substring(1);
            paths.add(path);
        }
        try {
            minioClientUtil.removeObject(paths);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        remove(Wrappers.<File>lambdaQuery().eq(File::getGroupId, groupId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void useless(String id) {
        update(Wrappers.<File>lambdaUpdate().set(File::getUseful, "0").eq(File::getId, id));
    }
}
