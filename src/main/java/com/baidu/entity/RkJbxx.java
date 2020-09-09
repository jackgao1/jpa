package com.baidu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @team mackie Studio
 * @Author QQ:15577969
 * @Date 2020-09-04 21:49:52
 */
@Entity
@Table(name = "t_rk_jbxx")
public class RkJbxx implements Serializable {

    /**
     * 人口编码
     */
    @Id
    @Column(name = "rkbm")
    @GenericGenerator(name = "idGenerator", strategy = "com.baidu.utils.CustomIdentifierGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String rkbm;

    /**
     * 人口管理类别（字典）
     */
    @Column(name = "ykgllb")
    private String ykgllb;

    /**
     * 姓名(不可修改)
     */
    @Column(name = "xm")
    private String xm;

    /**
     * 证件种类（字典）
     */
    @Column(name = "zjzl")
    private String zjzl;

    /**
     * 证件号码(不可修改)
     */
    @Column(name = "zjhm")
    private String zjhm;

    /**
     * 外文姓
     */
    @Column(name = "wwx")
    private String wwx;

    /**
     * 外文名
     */
    @Column(name = "wwm")
    private String wwm;

    /**
     * 出生日期
     */
    @Column(name = "csrq")
    private Date csrq;

    /**
     * 性别（字典）
     */
    @Column(name = "xb")
    private String xb;

    /**
     * 民族（字典）
     */
    @Column(name = "mz")
    private String mz;

    /**
     * 手机号码
     */
    @Column(name = "sjhm")
    private String sjhm;

    /**
     * 照片id
     */
    @Column(name = "zpid")
    private String zpid;

    /**
     * 国家
     */
    @Column(name = "gj")
    private String gj;

    /**
     * 户籍地省市县区代码（字典）
     */
    @Column(name = "hjdssxdm")
    private String hjdssxdm;

    /**
     * 户籍地区划内详细地址
     */
    @Column(name = "hjdqhnxxdz")
    private String hjdqhnxxdz;

    /**
     * 户籍地房屋编码（本市户籍填写）
     */
    @Column(name = "hjdfwbm")
    private String hjdfwbm;

    /**
     * 文化程度（字典：博士、研究生、大学、高中、初中、小学、文盲）
     */
    @Column(name = "whcd")
    private String whcd;

    /**
     * 政治面貌（字典：中共党员、共青团员、民主党派、群众）
     */
    @Column(name = "zzmm")
    private String zzmm;

    /**
     * 婚姻状况（字典）
     */
    @Column(name = "hyzk")
    private String hyzk;

    /**
     * 配偶证件种类（字典）
     */
    @Column(name = "pozjzl")
    private String pozjzl;

    /**
     * 配偶证件号码
     */
    @Column(name = "pozjhm")
    private String pozjhm;

    /**
     * 配偶姓名
     */
    @Column(name = "poxm")
    private String poxm;

    /**
     * 监护人证件种类（字典）
     */
    @Column(name = "jhrzjzl")
    private String jhrzjzl;

    /**
     * 监护人证件号码
     */
    @Column(name = "jhrzjhm")
    private String jhrzjhm;

    /**
     * 监护人姓名
     */
    @Column(name = "jhrxm")
    private String jhrxm;

    /**
     * 监护人联系电话
     */
    @Column(name = "jhrlxdh")
    private String jhrlxdh;

    /**
     * 交通工具（字典：助动车、汽车、摩托车）
     */
    @Column(name = "jtgj")
    private String jtgj;

    /**
     * 车牌号
     */
    @Column(name = "cph")
    private String cph;

    /**
     * 拟离开时间
     */
    @Column(name = "nlksj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nlksj;

    /**
     * 与房主关系（字典：本人、配偶、直系家属、非直系亲属、非亲属、其他）
     */
    @Column(name = "yfzgx")
    private String yfzgx;

    /**
     * 居住事由（字典）
     */
    @Column(name = "jzsy")
    private String jzsy;

    /**
     * 居住类型（字典）
     */
    @Column(name = "jzlx")
    private String jzlx;

    /**
     * 人户一致标识（是否）
     */
    @Column(name = "rhyzbs")
    private String rhyzbs;

    /**
     * 居住地址类型（字典）
     */
    @Column(name = "jzdzlx")
    private String jzdzlx;

    /**
     * 工作单位或就读学校名称
     */
    @Column(name = "gzdw")
    private String gzdw;

    /**
     * 单位地址编码（单位地址楼牌号编码）
     */
    @Column(name = "dwdzbm")
    private String dwdzbm;

    /**
     * 人标签（（不在页面展示、后台打标签）：延用原实口系统中的“特殊房间图例”分类，并增加“维族”、“藏族”标签）
     */
    @Column(name = "rbq")
    private String rbq;

    /**
     * 维护时间（精确到分，结合房屋走访日志功能，对房屋内居住人员进行“在住”操作时，记录为维护时间）
     */
    @Column(name = "whsj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date whsj;

    /**
     * 居住地房屋编码
     */
    @Column(name = "jzdfwbm")
    private String jzdfwbm;

    /**
     * 居住地行政区划代码（字典）
     */
    @Column(name = "jzdxzqhdm")
    private String jzdxzqhdm;

    /**
     * 居住地行政区划名称
     */
    @Column(name = "jzdxzqhmc")
    private String jzdxzqhmc;

    /**
     * 居住地类行政区域代码（字典）
     */
    @Column(name = "jzdlxzqydm")
    private String jzdlxzqydm;

    /**
     * 居住地类行政区域名称
     */
    @Column(name = "jzdlxzqymc")
    private String jzdlxzqymc;

    /**
     * 居住地乡镇街道代码（字典）
     */
    @Column(name = "jzdxzjddm")
    private String jzdxzjddm;

    /**
     * 居住地乡镇街道名称
     */
    @Column(name = "jzdxzjdmc")
    private String jzdxzjdmc;

    /**
     * 居住地社区居村委代码（字典）
     */
    @Column(name = "jzdsqjwdm")
    private String jzdsqjwdm;

    /**
     * 居住地社区居村委名称
     */
    @Column(name = "jzdsqjwmc")
    private String jzdsqjwmc;

    /**
     * 居住地街路巷（小区）代码
     */
    @Column(name = "jzdjlxdm")
    private String jzdjlxdm;

    /**
     * 居住地街路巷（小区）名称
     */
    @Column(name = "jzdjlxmc")
    private String jzdjlxmc;

    /**
     * 居住地门弄牌号
     */
    @Column(name = "jzdmnph")
    private String jzdmnph;

    /**
     * 居住地房屋楼牌号
     */
    @Column(name = "jzdfwlph")
    private String jzdfwlph;

    /**
     * 居住地房屋室号
     */
    @Column(name = "jzdfwsh")
    private String jzdfwsh;

    /**
     * 居住地址（居住地街路巷（小区）名称+居住地门弄牌号+居住地房屋楼牌号+居住地房屋室号）
     */
    @Column(name = "jzdz")
    private String jzdz;

    /**
     * 所属分县局代码（字典）
     */
    @Column(name = "ssfxjdm")
    private String ssfxjdm;

    /**
     * 所属分县局名称
     */
    @Column(name = "ssfxjmc")
    private String ssfxjmc;

    /**
     * 所属派出所代码（字典）
     */
    @Column(name = "sspcsdm")
    private String sspcsdm;

    /**
     * 所属派出所名称
     */
    @Column(name = "sspcsmc")
    private String sspcsmc;

    /**
     * 所属警务区代码（字典）
     */
    @Column(name = "ssjwqdm")
    private String ssjwqdm;

    /**
     * 所属警务区名称
     */
    @Column(name = "ssjwqmc")
    private String ssjwqmc;

    /**
     * 居住地网格代码（字典）
     */
    @Column(name = "jjdwgdm")
    private String jjdwgdm;

    /**
     * 居住地网格名称
     */
    @Column(name = "jjdwgmc")
    private String jjdwgmc;

    /**
     * 操作人id
     */
    @Column(name = "czrid")
    private String czrid;

    /**
     * 操作人姓名
     */
    @Column(name = "czrxm")
    private String czrxm;

    /**
     * 操作时间（时间戳）
     */
    @Column(name = "czsj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date czsj;

    /**
     * 操作人类型代码（字典）
     */
    @Column(name = "czrlxdm")
    private String czrlxdm;

    /**
     * 操作人类型名称（字典）
     */
    @Column(name = "czrlxmc")
    private String czrlxmc;

    /**
     * 操作终端编号
     */
    @Column(name = "czzdbh")
    private String czzdbh;

    /**
     * 操作终端类型代码（字典）
     */
    @Column(name = "czzdlxdm")
    private String czzdlxdm;

    /**
     * 操作终端类型名称（字典）
     */
    @Column(name = "czzdlxmc")
    private String czzdlxmc;

    /**
     * 操作人所属单位代码（字典）
     */
    @Column(name = "czrssdwdm")
    private String czrssdwdm;

    /**
     * 操作人所属单位名称
     */
    @Column(name = "czrssdwmc")
    private String czrssdwmc;

    /**
     * 操作责任民警警号
     */
    @Column(name = "czzrmjjh")
    private String czzrmjjh;

    /**
     * 操作责任民警姓名
     */
    @Column(name = "czzrmjxm")
    private String czzrmjxm;

    /**
     * 登记时间（时间戳）
     */
    @Column(name = "djsj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date djsj;

    /**
     * 注销类型（字典）
     */
    @Column(name = "zxlx")
    private String zxlx;

    /**
     * 注销时间（时间戳）
     */
    @Column(name = "zxsj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zxsj;

    /**
     * 人员状态（字典）
     */
    @Column(name = "ryzt")
    private String ryzt;

    /**
     * 数据来源（字典）
     */
    @Column(name = "sjly")
    private String sjly;

    /**
     * 数据更新时间（时间戳）
     */
    @Column(name = "sjgxsj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sjgxsj;

    /**
     * 单位地址
     */
    @Column(name = "dwdz")
    private String dwdz;

    /**
     * 入住时间
     */
    @Column(name = "rzsj")
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rzsj;

    @Column(name = "zp")
    private String zp;

    @PrePersist
    private void onCreate() {
        this.setCzsj(new Date());
        this.setSjgxsj(new Date());
        this.setDjsj(new Date());
    }

    @PreUpdate
    private void onUpdata() {
        this.setSjgxsj(new Date());
        this.setCzsj(new Date());
    }

    public String getRkbm() {
        return rkbm;
    }

    public void setRkbm(String rkbm) {
        this.rkbm = rkbm;
    }


    public String getYkgllb() {
        return ykgllb;
    }

    public void setYkgllb(String ykgllb) {
        this.ykgllb = ykgllb;
    }


    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }


    public String getZjzl() {
        return zjzl;
    }

    public void setZjzl(String zjzl) {
        this.zjzl = zjzl;
    }


    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }


    public String getWwx() {
        return wwx;
    }

    public void setWwx(String wwx) {
        this.wwx = wwx;
    }


    public String getWwm() {
        return wwm;
    }

    public void setWwm(String wwm) {
        this.wwm = wwm;
    }


    public Date getCsrq() {
        return csrq;
    }

    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }


    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }


    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }


    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }


    public String getZpid() {
        return zpid;
    }

    public void setZpid(String zpid) {
        this.zpid = zpid;
    }


    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }


    public String getHjdssxdm() {
        return hjdssxdm;
    }

    public void setHjdssxdm(String hjdssxdm) {
        this.hjdssxdm = hjdssxdm;
    }


    public String getHjdqhnxxdz() {
        return hjdqhnxxdz;
    }

    public void setHjdqhnxxdz(String hjdqhnxxdz) {
        this.hjdqhnxxdz = hjdqhnxxdz;
    }


    public String getHjdfwbm() {
        return hjdfwbm;
    }

    public void setHjdfwbm(String hjdfwbm) {
        this.hjdfwbm = hjdfwbm;
    }


    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }


    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }


    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }


    public String getPozjzl() {
        return pozjzl;
    }

    public void setPozjzl(String pozjzl) {
        this.pozjzl = pozjzl;
    }


    public String getPozjhm() {
        return pozjhm;
    }

    public void setPozjhm(String pozjhm) {
        this.pozjhm = pozjhm;
    }


    public String getPoxm() {
        return poxm;
    }

    public void setPoxm(String poxm) {
        this.poxm = poxm;
    }


    public String getJhrzjzl() {
        return jhrzjzl;
    }

    public void setJhrzjzl(String jhrzjzl) {
        this.jhrzjzl = jhrzjzl;
    }


    public String getJhrzjhm() {
        return jhrzjhm;
    }

    public void setJhrzjhm(String jhrzjhm) {
        this.jhrzjhm = jhrzjhm;
    }


    public String getJhrxm() {
        return jhrxm;
    }

    public void setJhrxm(String jhrxm) {
        this.jhrxm = jhrxm;
    }


    public String getJhrlxdh() {
        return jhrlxdh;
    }

    public void setJhrlxdh(String jhrlxdh) {
        this.jhrlxdh = jhrlxdh;
    }


    public String getJtgj() {
        return jtgj;
    }

    public void setJtgj(String jtgj) {
        this.jtgj = jtgj;
    }


    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }


    public Date getNlksj() {
        return nlksj;
    }

    public void setNlksj(Date nlksj) {
        this.nlksj = nlksj;
    }


    public String getYfzgx() {
        return yfzgx;
    }

    public void setYfzgx(String yfzgx) {
        this.yfzgx = yfzgx;
    }


    public String getJzsy() {
        return jzsy;
    }

    public void setJzsy(String jzsy) {
        this.jzsy = jzsy;
    }


    public String getJzlx() {
        return jzlx;
    }

    public void setJzlx(String jzlx) {
        this.jzlx = jzlx;
    }


    public String getRhyzbs() {
        return rhyzbs;
    }

    public void setRhyzbs(String rhyzbs) {
        this.rhyzbs = rhyzbs;
    }


    public String getJzdzlx() {
        return jzdzlx;
    }

    public void setJzdzlx(String jzdzlx) {
        this.jzdzlx = jzdzlx;
    }


    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }


    public String getDwdzbm() {
        return dwdzbm;
    }

    public void setDwdzbm(String dwdzbm) {
        this.dwdzbm = dwdzbm;
    }


    public String getRbq() {
        return rbq;
    }

    public void setRbq(String rbq) {
        this.rbq = rbq;
    }


    public Date getWhsj() {
        return whsj;
    }

    public void setWhsj(Date whsj) {
        this.whsj = whsj;
    }


    public String getJzdfwbm() {
        return jzdfwbm;
    }

    public void setJzdfwbm(String jzdfwbm) {
        this.jzdfwbm = jzdfwbm;
    }


    public String getJzdxzqhdm() {
        return jzdxzqhdm;
    }

    public void setJzdxzqhdm(String jzdxzqhdm) {
        this.jzdxzqhdm = jzdxzqhdm;
    }


    public String getJzdxzqhmc() {
        return jzdxzqhmc;
    }

    public void setJzdxzqhmc(String jzdxzqhmc) {
        this.jzdxzqhmc = jzdxzqhmc;
    }


    public String getJzdlxzqydm() {
        return jzdlxzqydm;
    }

    public void setJzdlxzqydm(String jzdlxzqydm) {
        this.jzdlxzqydm = jzdlxzqydm;
    }


    public String getJzdlxzqymc() {
        return jzdlxzqymc;
    }

    public void setJzdlxzqymc(String jzdlxzqymc) {
        this.jzdlxzqymc = jzdlxzqymc;
    }


    public String getJzdxzjddm() {
        return jzdxzjddm;
    }

    public void setJzdxzjddm(String jzdxzjddm) {
        this.jzdxzjddm = jzdxzjddm;
    }


    public String getJzdxzjdmc() {
        return jzdxzjdmc;
    }

    public void setJzdxzjdmc(String jzdxzjdmc) {
        this.jzdxzjdmc = jzdxzjdmc;
    }


    public String getJzdsqjwdm() {
        return jzdsqjwdm;
    }

    public void setJzdsqjwdm(String jzdsqjwdm) {
        this.jzdsqjwdm = jzdsqjwdm;
    }


    public String getJzdsqjwmc() {
        return jzdsqjwmc;
    }

    public void setJzdsqjwmc(String jzdsqjwmc) {
        this.jzdsqjwmc = jzdsqjwmc;
    }


    public String getJzdjlxdm() {
        return jzdjlxdm;
    }

    public void setJzdjlxdm(String jzdjlxdm) {
        this.jzdjlxdm = jzdjlxdm;
    }


    public String getJzdjlxmc() {
        return jzdjlxmc;
    }

    public void setJzdjlxmc(String jzdjlxmc) {
        this.jzdjlxmc = jzdjlxmc;
    }


    public String getJzdmnph() {
        return jzdmnph;
    }

    public void setJzdmnph(String jzdmnph) {
        this.jzdmnph = jzdmnph;
    }


    public String getJzdfwlph() {
        return jzdfwlph;
    }

    public void setJzdfwlph(String jzdfwlph) {
        this.jzdfwlph = jzdfwlph;
    }


    public String getJzdfwsh() {
        return jzdfwsh;
    }

    public void setJzdfwsh(String jzdfwsh) {
        this.jzdfwsh = jzdfwsh;
    }


    public String getJzdz() {
        return jzdz;
    }

    public void setJzdz(String jzdz) {
        this.jzdz = jzdz;
    }


    public String getSsfxjdm() {
        return ssfxjdm;
    }

    public void setSsfxjdm(String ssfxjdm) {
        this.ssfxjdm = ssfxjdm;
    }


    public String getSsfxjmc() {
        return ssfxjmc;
    }

    public void setSsfxjmc(String ssfxjmc) {
        this.ssfxjmc = ssfxjmc;
    }


    public String getSspcsdm() {
        return sspcsdm;
    }

    public void setSspcsdm(String sspcsdm) {
        this.sspcsdm = sspcsdm;
    }


    public String getSspcsmc() {
        return sspcsmc;
    }

    public void setSspcsmc(String sspcsmc) {
        this.sspcsmc = sspcsmc;
    }


    public String getSsjwqdm() {
        return ssjwqdm;
    }

    public void setSsjwqdm(String ssjwqdm) {
        this.ssjwqdm = ssjwqdm;
    }


    public String getSsjwqmc() {
        return ssjwqmc;
    }

    public void setSsjwqmc(String ssjwqmc) {
        this.ssjwqmc = ssjwqmc;
    }


    public String getJjdwgdm() {
        return jjdwgdm;
    }

    public void setJjdwgdm(String jjdwgdm) {
        this.jjdwgdm = jjdwgdm;
    }


    public String getJjdwgmc() {
        return jjdwgmc;
    }

    public void setJjdwgmc(String jjdwgmc) {
        this.jjdwgmc = jjdwgmc;
    }


    public String getCzrid() {
        return czrid;
    }

    public void setCzrid(String czrid) {
        this.czrid = czrid;
    }


    public String getCzrxm() {
        return czrxm;
    }

    public void setCzrxm(String czrxm) {
        this.czrxm = czrxm;
    }


    public Date getCzsj() {
        return czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }


    public String getCzrlxdm() {
        return czrlxdm;
    }

    public void setCzrlxdm(String czrlxdm) {
        this.czrlxdm = czrlxdm;
    }


    public String getCzrlxmc() {
        return czrlxmc;
    }

    public void setCzrlxmc(String czrlxmc) {
        this.czrlxmc = czrlxmc;
    }


    public String getCzzdbh() {
        return czzdbh;
    }

    public void setCzzdbh(String czzdbh) {
        this.czzdbh = czzdbh;
    }


    public String getCzzdlxdm() {
        return czzdlxdm;
    }

    public void setCzzdlxdm(String czzdlxdm) {
        this.czzdlxdm = czzdlxdm;
    }


    public String getCzzdlxmc() {
        return czzdlxmc;
    }

    public void setCzzdlxmc(String czzdlxmc) {
        this.czzdlxmc = czzdlxmc;
    }


    public String getCzrssdwdm() {
        return czrssdwdm;
    }

    public void setCzrssdwdm(String czrssdwdm) {
        this.czrssdwdm = czrssdwdm;
    }


    public String getCzrssdwmc() {
        return czrssdwmc;
    }

    public void setCzrssdwmc(String czrssdwmc) {
        this.czrssdwmc = czrssdwmc;
    }


    public String getCzzrmjjh() {
        return czzrmjjh;
    }

    public void setCzzrmjjh(String czzrmjjh) {
        this.czzrmjjh = czzrmjjh;
    }


    public String getCzzrmjxm() {
        return czzrmjxm;
    }

    public void setCzzrmjxm(String czzrmjxm) {
        this.czzrmjxm = czzrmjxm;
    }


    public Date getDjsj() {
        return djsj;
    }

    public void setDjsj(Date djsj) {
        this.djsj = djsj;
    }


    public String getZxlx() {
        return zxlx;
    }

    public void setZxlx(String zxlx) {
        this.zxlx = zxlx;
    }


    public Date getZxsj() {
        return zxsj;
    }

    public void setZxsj(Date zxsj) {
        this.zxsj = zxsj;
    }


    public String getRyzt() {
        return ryzt;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
    }


    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }


    public Date getSjgxsj() {
        return sjgxsj;
    }

    public void setSjgxsj(Date sjgxsj) {
        this.sjgxsj = sjgxsj;
    }


    public String getDwdz() {
        return dwdz;
    }

    public void setDwdz(String dwdz) {
        this.dwdz = dwdz;
    }


    public Date getRzsj() {
        return rzsj;
    }

    public void setRzsj(Date rzsj) {
        this.rzsj = rzsj;
    }


    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }

}
