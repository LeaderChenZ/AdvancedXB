import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
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
public class TestUser {
    @Autowired
   private UserMapper userMapper;

    @Autowired
    private UserService service;
    @Test
    public void testUser(){
        System.out.println(userMapper);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testService(){
        HashMap<String,Object> map = new HashMap<>();
        PageInfo<User> userPageInfo = service.selectByCondition(map);
        System.out.println(userPageInfo);

    }

    @Test
    public void testByUid(){
        User user = userMapper.selectByPrimaryKey(2);
        System.out.println(user);
    }

    @Test
    public void testSelectFocus(){
        HashMap<String,Object> map = new HashMap<>();
        PageInfo<User> userPageInfo = service.selectFocus(map);
        System.out.println(userPageInfo);


    }

    @Test
    public void deleteFocus(){
        int i = service.deleteFacus(1, 3);
        System.out.println(i);

    }
}
