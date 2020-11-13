package com.baidu.service;

import com.baidu.entity.Minio;
import com.baidu.entity.repository.MinioRepository;
import com.baidu.utils.PicUtils;
import com.baidu.utils.StringUtils;
import com.google.api.client.util.IOUtils;
import io.minio.MinioClient;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 高鑫
 * @date 2020/10/17 20:34
 */
@Service
@Transactional
public class MinioService {
    private Logger logger = LoggerFactory.getLogger(MinioService.class);
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

    /**
     * 上传文件到minio服务
     *
     * @param file
     * @return
     */
    public Map<String, Object> upload(MultipartFile file) {
        Minio minio = null;
        byte[] bytes = null;
        InputStream inputStream = null;
        MinioClient minioClient = null;
        Map<String, Object> map = null;
        MockMultipartFile mockMultipartFile = null;
        try {
            //压缩图片大小
            bytes = PicUtils.compressPicForScale(file.getBytes(), 200, "x");
            inputStream = new ByteArrayInputStream(bytes);
            mockMultipartFile = new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);

            minioClient = getMinioClient();
            //类型
            String fileType = file.getContentType();
            String fileSize = StringUtils.getNetFileSizeDescription(file.getSize());
            //文件名
            String fileName = file.getOriginalFilename();
            String ossFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + fileName.substring(fileName.lastIndexOf('.'), fileName.length());
            //把文件放置Minio桶(文件夹)
            minioClient.putObject(bucketName, ossFileName, mockMultipartFile.getInputStream(), fileType);
            String url = minioClient.presignedGetObject(bucketName, ossFileName);
            minio = new Minio();
            minio.setFileName(fileName);
            minio.setFileSize(fileSize);
            minio.setFileType(fileType);
            minio.setOssFileName(ossFileName);
            minio.setUrl(url);
            minioRepository.save(minio);
            map = new HashMap<>();
            map.put("url", url);
            map.put("code", "上传成功");
            map.put("ossFileName", ossFileName);
            map.put("fileType", fileType);
            map.put("fileSize", fileSize);
            logger.info("文件保存成功");
        } catch (Exception e) {
            logger.info("文件保存失败");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 下载minio服务的文件
     *
     * @param response
     * @param ossFileName
     * @return
     */
    public String download(HttpServletResponse response, String ossFileName) {
        try {
            InputStream fileInputStream = getMinioClient().getObject(bucketName, ossFileName);
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

    /**
     * 文件删除
     *
     * @param ossFileName
     * @return
     */
    public String delete(String ossFileName) {
        try {
            getMinioClient().removeObject(bucketName, ossFileName);
            minioRepository.deleteByOssFileName(ossFileName);
            logger.info("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除失败");
        }
        return "删除成功";
    }

    /**
     * 获取文件信息列表
     *
     * @return
     */
    public List<Minio> getFileInfoList() {
        return minioRepository.findAll();
    }

    /**
     * 获取MinioClient对象
     *
     * @return
     */
    public MinioClient getMinioClient() {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(url, accessKey, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minioClient;
    }
}