package com.dfbz.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "meeting")
public class Meeting {
    @Id
    private Integer id;

    /**
     * 部门名称
     */
    @Column(name = "dept_name")
    private String deptName;

    /**
     * 部门id
     */
    @Column(name = "dept_id")
    private Integer deptId;

    /**
     * 会议标题
     */
    private String title;

    /**
     * 会议内容
     */
    private String content;

    /**
     * 发布时间
     */
    @Column(name = "publish_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 会议状态 0:未开始 1:进行中 2:已结束
     */
    private Integer status;

    /**
     * 抄送人id，以逗号分隔
     */
    @Column(name = "make_user")
    private String makeUser;

    /*
    * 应到人数
    * */
    @Transient
    private String[] getUser;

    /*
    * 实到人数
    * */
    @Transient
    private Integer arrivals;

    /*
    * 未到人数
    * */

    @Transient Integer late;

    public Integer getArrivals() {
        return arrivals;
    }

    public void setArrivals(Integer arrivals) {
        this.arrivals = arrivals;
    }

    public Integer getLate() {
        return late;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public String[] getGetUser() {
        return getUser;
    }

    public void setGetUser(String[] getUser) {
        this.getUser = getUser;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门名称
     *
     * @return dept_name - 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置部门名称
     *
     * @param deptName 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    /**
     * 获取部门id
     *
     * @return dept_id - 部门id
     */
    public Integer getDeptId() {
        return deptId;
    }

    /**
     * 设置部门id
     *
     * @param deptId 部门id
     */
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取会议标题
     *
     * @return title - 会议标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置会议标题
     *
     * @param title 会议标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取会议内容
     *
     * @return content - 会议内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置会议内容
     *
     * @param content 会议内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取发布时间
     *
     * @return publish_date - 发布时间
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * 设置发布时间
     *
     * @param publishDate 发布时间
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取会议状态 0:未开始 1:进行中 2:已结束
     *
     * @return status - 会议状态 0:未开始 1:进行中 2:已结束
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置会议状态 0:未开始 1:进行中 2:已结束
     *
     * @param status 会议状态 0:未开始 1:进行中 2:已结束
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取抄送人id，以逗号分隔
     *
     * @return make_user - 抄送人id，以逗号分隔
     */
    public String getMakeUser() {
        return makeUser;
    }

    /**
     * 设置抄送人id，以逗号分隔
     *
     * @param makeUser 抄送人id，以逗号分隔
     */
    public void setMakeUser(String makeUser) {
        this.makeUser = makeUser == null ? null : makeUser.trim();
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", makeUser='" + makeUser + '\'' +
                '}';
    }
}