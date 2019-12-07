import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.UserMapper;
import com.dfbz.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    UserMapper userMapper;
    @Test
    public void testUser(){
        System.out.println(userMapper);
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
