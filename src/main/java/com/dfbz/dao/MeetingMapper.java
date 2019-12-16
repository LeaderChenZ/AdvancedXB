package com.dfbz.dao;

import com.dfbz.entity.Meeting;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface MeetingMapper extends Mapper<Meeting> {

    @Select("select meeting.* " +
            "from " +
            "meeting  " +
            "where " +
            "meeting.id=#{id}")
    Meeting selectDetail(long id);
    
    /*
    * 查询应到人数
    * */
    @Select("select " +
            "count(1) " +
            "from " +
            "`user` u " +
            "LEFT JOIN " +
            "meeting_join mj " +
            "on " +
            "u.id = mj.u_id " +
            "LEFT JOIN " +
            "meeting m " +
            "on " +
            "m.id = mj.c_id " +
            "where " +
            "mj.c_id = #{mid}")
    int selectGetUser(long mid);

    /*
    *
    * 参加会议*/
    @Insert("INSERT INTO `xb`.`meeting_join`(`u_id`, `c_id`) VALUES (#{uid}, #{mid})")
    int addMeeting(@Param("uid") long uid,@Param("mid") long mid);

    /*
    * 退出会议
    * */
    @Delete("delete from meeting_join where meeting_join.u_id = #{uid} and meeting_join.c_id =#{mid}")
    int deleteMeeting(@Param("uid") long uid,@Param("mid") long mid);
}