package com.dfbz.service;

import com.dfbz.entity.Article;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 18
 */
public interface ArticleService extends IService<Article> {
    PageInfo<Article> selectArticleConditicon(Map<String, Object> params);

    /*
    * 根据文章ID 再次
    * */
    Article selectByAId(long aid, long uid);


}
