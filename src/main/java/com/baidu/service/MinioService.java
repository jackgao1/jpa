package com.baidu.service;

import com.google.api.client.util.IOUtils;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 高鑫
 * @date 2020/10/17 20:34
 */
@Service
@Component
public class MinioService {
    @Value("${url}")
    private String url;
    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${bucketName}")
    private String bucketName;

    //上传文件到minio服务
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            InputStream inputStream = file.getInputStream(); //得到文件流
            String contentType = file.getContentType();  //类型
            String fileName = file.getOriginalFilename(); //文件名
            String OssFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + fileName.substring(fileName.lastIndexOf('.'), fileName.length());
            minioClient.putObject(bucketName, OssFileName, inputStream, contentType); //把文件放置Minio桶(文件夹)
            String url = minioClient.presignedGetObject(bucketName, OssFileName);
            map.put("url", url);
            map.put("code", "上传成功");
            map.put("OssFileName", OssFileName);
            map.put("contentType", contentType);
        } catch (Exception e) {
            map.put("code", "上传失败");
        }
        return map;
    }

    //下载minio服务的文件
    @GetMapping("download")
    public String download(HttpServletResponse response, String OssFileName) {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            InputStream fileInputStream = minioClient.getObject(bucketName, OssFileName);
            response.setHeader("Content-Disposition", "attachment;filename=" + OssFileName);
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
            return "下载完成";
        } catch (Exception e) {
            e.printStackTrace();
            return "下载失败";
        }
    }

    //获取minio文件的下载地址
    @GetMapping("url")
    public String getUrl(String OssFileName) {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            return minioClient.presignedGetObject(bucketName, OssFileName);
        } catch (Exception e) {
            return "获取失败";
        }
    }

}
