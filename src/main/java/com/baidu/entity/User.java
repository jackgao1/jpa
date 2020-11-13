package com.baidu.entity;

import com.baidu.utils.SpringContextHolder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "idGenerator", strategy = "com.baidu.utils.CustomIdentifierGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 名字
     */
    @Column(name = "name", nullable = true, length = 30)
    private String name;

    /**
     * 身高
     */
    @Column(name = "height", nullable = true, length = 10)
    private Integer height;

    public User() {
    }
    @Transient
    public String getPozjzl_name() {
        return SpringContextHolder.dictionaryService.getMcBydm("D_RK_WHCD", "2");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
