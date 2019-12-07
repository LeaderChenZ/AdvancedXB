package com.dfbz.entity;

import javax.persistence.Table;

/**
 * @author Chen
 * @description
 * @date 2019/12/6 21
 */
@Table(name = "favorite")
public class Favorite {

    /**
     * 收藏用户id
     */
    private String UId;

    /**
     * 文章id
     */
    private String AId;

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getAId() {
        return AId;
    }

    public void setAId(String AId) {
        this.AId = AId;
    }
}
