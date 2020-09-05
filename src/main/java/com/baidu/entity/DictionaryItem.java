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
@Table(name = "sys_dictionary_item")
public class DictionaryItem implements Serializable {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "idGenerator", strategy = "com.baidu.utils.CustomIdentifierGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "dictionary_id")
    private String dictionaryId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "py")
    private String py;

    @Column(name = "status")
    private String status;

    @Column(name = "seq")
    private BigDecimal seq;

    @Column(name = "pid")
    private String pid;

    @Column(name = "short_name")
    private String shortName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
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


    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
