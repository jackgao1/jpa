package com.baidu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @team mackie Studio
 * @Author QQ:15577969
 * @Date 2020-10-18 14:29:49
 */
@Entity
@Table(name = "t_minio")
public class Minio implements Serializable {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "idGenerator", strategy = "com.baidu.utils.CustomIdentifierGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "url")
    private String url;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "oss_file_name")
    private String ossFileName;

    @Column(name = "file_size")
    private String fileSize;

    /**
     * 入库时间（时间戳）
     */
    @Column(name = "rksj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rksj;

    @PrePersist
    private void onCreate() {
        Date date = new Date();
        this.setRksj(date);
    }

    @PreUpdate
    private void onUpdata() {
    }

    public Date getRksj() {
        return rksj;
    }

    public void setRksj(Date rksj) {
        this.rksj = rksj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getOssFileName() {
        return ossFileName;
    }

    public void setOssFileName(String ossFileName) {
        this.ossFileName = ossFileName;
    }


    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

}
