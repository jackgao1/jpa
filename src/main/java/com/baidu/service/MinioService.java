package com.baidu.service;

import com.baidu.entity.Minio;
import com.baidu.entity.repository.MinioRepository;
import com.baidu.utils.StringUtils;
import com.google.api.client.util.IOUtils;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author 高鑫
 * @date 2020/10/17 20:34
 */
@Service
@Component
@Transactional
public class MinioService {
    @Autowired
    private MinioRepository minioRepository;
    @Value("${url}")
    private String url;
    @Value("${accessKey}")
    private String accessKey;
    @Value("${secretKey}")
    private String secretKey;
    @Value("${bucketName}")
    private String bucketName;

    //上传文件到minio服务
    public Map<String, Object> upload(MultipartFile file) {
        Minio minio = new Minio();
        Map<String, Object> map = new HashMap<>();
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            InputStream inputStream = file.getInputStream(); //得到文件流
            String fileType = file.getContentType();  //类型
            String fileSize = StringUtils.getNetFileSizeDescription(file.getSize());
            String fileName = file.getOriginalFilename(); //文件名
            String ossFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + fileName.substring(fileName.lastIndexOf('.'), fileName.length());
            minioClient.putObject(bucketName, ossFileName, inputStream, fileType); //把文件放置Minio桶(文件夹)
            String url = minioClient.presignedGetObject(bucketName, ossFileName);
            minio.setFileName(fileName);
            minio.setFileSize(fileSize);
            minio.setFileType(fileType);
            minio.setOssFileName(ossFileName);
            minio.setUrl(url);
            minioRepository.save(minio);
            map.put("url", url);
            map.put("code", "上传成功");
            map.put("ossFileName", ossFileName);
            map.put("fileType", fileType);
            map.put("fileSize", fileSize);
        } catch (Exception e) {
            map.put("code", "上传失败");
        }
        return map;
    }

    //下载minio服务的文件
    public String download(HttpServletResponse response, String ossFileName) {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            InputStream fileInputStream = minioClient.getObject(bucketName, ossFileName);
            response.setHeader("Content-Disposition", "attachment;filename=" + ossFileName);
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
            return "下载完成";
        } catch (Exception e) {
            e.printStackTrace();
            return "下载失败";
        }
    }

    //文件删除
    public String delete(String ossFileName) {
        try {
            MinioClient minioClient = new MinioClient(url, accessKey, secretKey);
            minioClient.removeObject(bucketName, ossFileName);
            minioRepository.deleteByOssFileName(ossFileName);
        } catch (Exception e) {
            return "删除失败" + e.getMessage();
        }
        return "删除成功";
    }

    //获取文件列表
    public List<Minio> getFileList() {
        return minioRepository.findAll();
    }

}
