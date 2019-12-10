package com.dfbz.service.impl;

import com.dfbz.dao.ArticleMapper;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.Article;
import com.dfbz.entity.User;
import com.dfbz.service.ArticleService;
import com.dfbz.util.Deduplication;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 18
 */
@Service
@Transactional
public class ArticleServiceImpl extends IServiceImpl<Article> implements ArticleService {

    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<Article> selectArticleConditicon(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));


        List<Article> articles = mapper.selectArticleConditicon(params);
        return new PageInfo<Article>(articles);
    }

    /*
     * 根据文章ID查询文章信息，在根据当前的用户ID和文章id查询两个用户的信息，然后去重，存入Common中
     * */
    @Override
    public Article selectByAId(long aid, long uid) {
        Article article = mapper.selectByPrimaryKey(aid);
        List<User> users1 = mapper.selectByFaId(aid);
        List<User> users = userMapper.selectFocus(uid);
        /*
         * 去重后得到的user集合
         * */
        List<User> same = Deduplication.getSame(users, users1);
        article.setCommon(same);
        return article;
    }





}
