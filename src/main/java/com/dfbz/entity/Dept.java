package com.dfbz.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "dept")
public class Dept {
    /**
     * 部门id
     */
    @Id
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /*
    * 部门下的用户数量
    * */
    @Transient
    private Integer count;

    /**
     * 获取部门id
     *
     * @return id - 部门id
     */
    @Transient
    private List<User> user;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    /**
     * 获取部门id
     *
     * @return id - 部门id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置部门id
     *
     * @param id 部门id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门名称
     *
     * @return name - 部门名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名称
     *
     * @param name 部门名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}