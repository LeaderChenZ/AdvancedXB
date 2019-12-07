package com.dfbz.dao;

import com.dfbz.entity.Article;
import com.dfbz.entity.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper extends Mapper<Article> {

    @SelectProvider(type = ArticleProvider.class,method = "selectArticleConditicon")
    List<Article> selectArticleConditicon(Map<String,Object> params);

    @Select("select   " +
            "u.*   " +
            "from   " +
            "article ar,`user` u,favorite fa  " +
            "where  " +
            "u.id=fa.u_id  " +
            "and   " +
            "ar.id=fa.a_id  " +
            "and  " +
            "fa.a_id =#{fid}")
    List<User> selectByFaId(long id);
}