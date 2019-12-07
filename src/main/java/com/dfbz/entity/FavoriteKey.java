package com.dfbz.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "favorite")
public class FavoriteKey {
    /**
     * 收藏用户id
     */
    @Id
    @Column(name = "u_id")
    private Integer uId;

    /**
     * 文章id
     */
    @Id
    @Column(name = "a_id")
    private Integer aId;

    /**
     * 获取收藏用户id
     *
     * @return u_id - 收藏用户id
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * 设置收藏用户id
     *
     * @param uId 收藏用户id
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * 获取文章id
     *
     * @return a_id - 文章id
     */
    public Integer getaId() {
        return aId;
    }

    /**
     * 设置文章id
     *
     * @param aId 文章id
     */
    public void setaId(Integer aId) {
        this.aId = aId;
    }
}