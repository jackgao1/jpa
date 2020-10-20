package com.baidu.controller;

import com.baidu.entity.Minio;
import com.baidu.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    public String download(HttpServletResponse response, String ossFileName) {
        return minioService.download(response, ossFileName);
    }

    //删除minio服务的文件
    @DeleteMapping("delete")
    public String delete(String ossFileName) {
        return minioService.delete(ossFileName);
    }

    //获取文件信息列表
    @GetMapping("getFileInfoList")
    public List<Minio> getFileInfoList() {
        return minioService.getFileInfoList();
    }

}

