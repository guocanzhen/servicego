package com.mk.servicego.util;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guocz
 * @date 2023/8/29 16:31
 */
@Component
public class MinioClientUtil {

    @Value("${minio.bucketName}")
    private String bucketName;
    @Resource
    private MinioClient minioClient;

    private static final int DEFAULT_EXPIRY_TIME = 7 * 24 * 3600;

    /**
     * 检查存储桶是否存在
     */
    private boolean bucketExists(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException {
         return minioClient.bucketExists(this.bucketName);
    }

    /**
     * 通过InputStream上传对象
     *
     * @param objectName 存储桶里的对象名称
     * @param stream     要上传的流 (文件的流)
     */
    public boolean putObject(String objectName, InputStream stream) throws Exception {

        //判断 桶是否存在
        boolean flag = bucketExists(bucketName);
        if (!flag) {
            return false;
        }

        //往桶中添加数据   minioClient 进行添加
        /**
         *   参数1： 桶的名称
         *   参数2： 文件的名称
         *   参数3： 文件的流
         *   参数4： 添加的配置
         */
        minioClient.putObject(bucketName, objectName, stream, new PutObjectOptions(stream.available(), -1));
        ObjectStat statObject = statObject(objectName);
        return statObject != null && statObject.length() > 0;
    }

    /**
     * 删除一个对象
     * @param objectName 存储桶里的对象名称
     */
    public boolean removeObject(String objectName)throws Exception {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            minioClient.removeObject(bucketName, objectName);
            return true;
        }
        return false;
    }
    /**
     * 删除指定桶的多个文件对象,返回删除错误的对象列表，全部删除成功，返回空列表
     * @param objectNames 含有要删除的多个object名称的迭代器对象
     */
    public List<String> removeObject(List<String> objectNames) throws Exception {
        List<String> deleteErrorNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<DeleteError>> results = minioClient.removeObjects(bucketName, objectNames);
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                deleteErrorNames.add(error.objectName());
            }
        }
        return deleteErrorNames;
    }

    /**
     * 获取对象的元数据
     * @param objectName 存储桶里的对象名称
     */
    public ObjectStat statObject(String objectName) throws Exception {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            return minioClient.statObject(bucketName, objectName);
        }
        return null;
    }

    /**
     * 文件访问路径
     * @param objectName 存储桶里的对象名称
     */
    public String getObjectUrl(String objectName) throws Exception {
        boolean flag = bucketExists(bucketName);
        String url = "";
        if (flag) {
            url = minioClient.presignedGetObject(bucketName, objectName);
        }
        return url;
    }

    public void getObject(String filename, HttpServletResponse response){
        InputStream in = null;
        OutputStream out = null;
        try{
            in=minioClient.getObject(bucketName,filename);
            int length;
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            response.reset();
            response.addHeader("Content-Disposition",
                    " attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentType("application/octet-stream");
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null){
                try {
                    in.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
