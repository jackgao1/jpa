package com.baidu.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @team mackie Studio
 * @Author QQ:15577969
 * @Date 2020-09-04 21:49:52
 */
@Entity
@Table(name = "sys_dictionary")
public class Dictionary implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "idGenerator", strategy = "com.baidu.utils.CustomIdentifierGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 代码
     */
    @Column(name = "code")
    private String code;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 代码最大长度
     */
    @Column(name = "code_length")
    private BigDecimal codeLength;

    /**
     * 名称最大长度
     */
    @Column(name = "name_length")
    private BigDecimal nameLength;

    /**
     * 序号
     */
    @Column(name = "seq")
    private BigDecimal seq;

    /**
     * 系统标识
     */
    @Column(name = "system_identity")
    private String systemIdentity;

    /**
     * 系统名称
     */
    @Column(name = "system_name")
    private String systemName;

    /**
     * 系统简称
     */
    @Column(name = "system_shortname")
    private String systemShortname;

    /**
     * 字典标准
     */
    @Column(name = "standard")
    private String standard;

    /**
     * 注释
     */
    @Column(name = "comments")
    private String comments;

    /**
     * 版本号
     */
    @Column(name = "version_number")
    private String versionNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public BigDecimal getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(BigDecimal codeLength) {
        this.codeLength = codeLength;
    }


    public BigDecimal getNameLength() {
        return nameLength;
    }

    public void setNameLength(BigDecimal nameLength) {
        this.nameLength = nameLength;
    }


    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }


    public String getSystemIdentity() {
        return systemIdentity;
    }

    public void setSystemIdentity(String systemIdentity) {
        this.systemIdentity = systemIdentity;
    }


    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }


    public String getSystemShortname() {
        return systemShortname;
    }

    public void setSystemShortname(String systemShortname) {
        this.systemShortname = systemShortname;
    }


    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

}
