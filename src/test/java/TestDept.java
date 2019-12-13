import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.DeptMapper;
import com.dfbz.entity.Dept;
import com.dfbz.entity.User;
import com.dfbz.service.DeptService;
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
public class TestDept {
    @Autowired
   private DeptMapper mapper;

    @Autowired
    private DeptService service;
    @Test
    public void testDept(){
        List<User> users = mapper.selectByUName(1);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void test01(){
        List<Dept> dept = mapper.selectDept();
        for (Dept dept1 : dept) {
            System.out.println(dept1);
            System.out.println(dept1.getCount());
        }
    }
    @Test
    public void test02(){
        List<Dept> dept = service.selectByCondition();
        for (Dept dept1 : dept) {
            System.out.println(dept1);
            System.out.println(dept1.getUser());
        }
    }
    @Test
    public void test03(){
        List<User> dept = service.selectUser(1);
        for (User dept1 : dept) {
            System.out.println(dept1);
        }
    }

}
