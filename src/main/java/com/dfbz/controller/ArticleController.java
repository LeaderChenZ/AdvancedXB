package com.dfbz.controller;

import com.dfbz.entity.Article;
import com.dfbz.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
