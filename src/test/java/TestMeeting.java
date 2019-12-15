import com.dfbz.config.SpringMybatis;
import com.dfbz.entity.Dept;
import com.dfbz.entity.Meeting;
import com.dfbz.service.DeptService;
import com.dfbz.service.MeetingService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestMeeting {


    @Autowired
    private MeetingService service;
    @Autowired
    private DeptService deptService;

    @Test
    public void testDept(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("title","会议");
//        map.put("status",0);
        PageInfo<Meeting> meetingPageInfo = service.selectByCondition(map);
        System.out.println(meetingPageInfo);
    }

    @Test
    public void test1(){
        Dept dept = deptService.selectDeptAll("1");
        System.out.println(dept.getId());
        System.out.println(dept.getName());
    }

}
