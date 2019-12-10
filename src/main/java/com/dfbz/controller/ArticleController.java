package com.dfbz.controller;

import com.dfbz.entity.Article;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 12
 */
@RestController
@RequestMapping("xbjy/article")
public class ArticleController {
    @Autowired
    private ArticleService service;

    @RequestMapping("list")
    PageInfo<Article> list(@RequestBody Map<String, Object> params) {
        return service.selectArticleConditicon(params);
    }

    @RequestMapping("selectByAId")
    Article selectByAId(long aid, long uid) {
        return service.selectByAId(aid, uid);
    }

    /*
    * 添加文章
    * */
    @RequestMapping("insertArticle")
    Result insertArticle(@RequestBody Article article, HttpSession session){
        User user = (User) session.getAttribute("user");
        Result result = new Result();
        article.setUserId(user.getId());
        article.setPublishRealName(user.getRealName());
        article.setPublishDate(new Date());
        article.setBrowseCount(0);
        int insert = service.insert(article);
        if (insert>0)
        {
            result.setSuccess(true);
            result.setMsg("添加成功！");
        }
        return result;
    }

}
