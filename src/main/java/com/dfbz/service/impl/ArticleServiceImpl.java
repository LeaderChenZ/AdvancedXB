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
        if (params.containsKey("id") && !StringUtils.isEmpty(params.get("id"))) {
            Integer uid = (Integer) params.get("id");
            for (Article article : articles) {
                //设置查到的用户是否为登录账号用户的关注人
                List<User> users1 = mapper.selectByFaId(article.getId());
                List<User> users = userMapper.selectFocus(uid);
                /*
                 * 去重后得到的user集合
                 * */
                List<User> same = Deduplication.getSame(users, users1);
                article.setCommon(same);

            }
        }

        /*if (params.containsKey("id")&&!StringUtils.isEmpty(params.get("id"))){
            Integer uid = (Integer) params.get("id");
            for (Article article : articles) {
                //设置这篇文章是否是已经收藏的文章
                List<Article> collect = mapper.selectByUId(uid);
                for (Article c : collect) {
                    if (article.getId()==c.getId()){
                        article.setFlag(1);
                    }
                }

            }
        }*/
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

        //设置这篇文章是否是已经收藏的文章
        List<Article> collect = mapper.selectByUId(uid);
        for (Article c : collect) {
            if (article.getId() == c.getId()) {
                article.setFlag(1);
            }
        }
        return article;
    }


    /*
     * 根据用户ID查询用户的收藏文章
     * */
    @Override
    public PageInfo<Article> SelectCollectArticle(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 3);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));


        List<Article> articles = mapper.selectFavorriteConditicon(params);
        if (params.containsKey("uid") && !StringUtils.isEmpty(params.get("uid"))) {
            Integer uid = (Integer) params.get("uid");
            for (Article article : articles) {
                //设置查到的用户是否为登录账号用户的关注人
                List<User> users1 = mapper.selectByFaId(article.getId());
                List<User> users = userMapper.selectFocus(uid);
                /*
                 * 去重后得到的user集合
                 * */
                List<User> same = Deduplication.getSame(users, users1);
                article.setCommon(same);

            }
        }

        return new PageInfo<Article>(articles);
    }

    /*
     * 添加收藏文章
     * */
    @Override
    public int addArticle(long uid, long aid) {
        return mapper.addArticle(uid, aid);
    }

    /*
     * 删除收藏文章
     * */
    @Override
    public int deleteArticle(long uid, long aid) {
        return mapper.deleteArticle(uid, aid);
    }


}
