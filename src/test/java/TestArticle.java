import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.ArticleMapper;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.Article;
import com.dfbz.entity.User;
import com.dfbz.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestArticle {

    @Autowired
    private UserMapper userMapper;
    @Autowired
   private ArticleMapper articleMapper;

    @Autowired
    private ArticleService service;

    @Test
    public void testArticle(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("uid",1);
//        map.put("title","如何");
        /*List<Article> articles = articleMapper.selectArticleConditicon(map);
        for (Article article : articles) {
            System.out.println(article);
        }*/

        PageInfo<Article> articlePageInfo = service.selectArticleConditicon(map);
        System.out.println(articlePageInfo);

    }
    @Test
    public void testAId(){
        Article article = articleMapper.selectByPrimaryKey(2);
        System.out.println(article);
    }
    @Test
    public void testByFaId(){
        Article article = service.selectByAId(1, 1);
        List<User> common = article.getCommon();
        for (User user : common) {
            System.out.println(user);
        }
    }
    @Test
    public void testByUId(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("uid",1);
        map.put("title","如何");
        PageInfo<Article> page = service.SelectCollectArticle(map);
        System.out.println(page);
    }
}
