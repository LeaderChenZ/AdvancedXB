import com.dfbz.config.SpringMybatis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestDataSource {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws Exception{
        System.out.println(dataSource.getConnection());
    }
}
