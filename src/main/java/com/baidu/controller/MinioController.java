package com.baidu.controller;

import com.baidu.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 高鑫
 * @date 2020/10/17 21:44
 */
@RestController
public class MinioController {
    @Autowired
    private MinioService minioService;

    //上传文件到minio服务
    @PostMapping("upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        return minioService.upload(file);
    }

    //下载minio服务的文件
    @GetMapping("download")
    public String download(HttpServletResponse response, String OssFileName) {
        return minioService.download(response, OssFileName);
    }

    //获取minio文件的下载地址
    @GetMapping("url")
    public String getUrl(String OssFileName) {
        return minioService.getUrl(OssFileName);
    }
}
