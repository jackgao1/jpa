package com.baidu.entity.repository;

import com.baidu.entity.Minio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author 高鑫
 * @date 2020/10/18 14:38
 */
public interface MinioRepository extends JpaRepository<Minio, String>, JpaSpecificationExecutor<Minio> {
    void deleteByOssFileName(String ossFileName);
}
