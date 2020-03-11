package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @program Course
 * @description 课程实体类
 * @date 2020/3/11 14:40
 */
@Data
public class Course {

    /**
     * 课程id，自增，用于查找
     */
    private int courseId;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程视频上传进度
     */
    private String courseVideoProgress;

    /**
     * 课时总学时
     */
    private int courseHours;

    /**
     * 课程已完成学时
     */
    private int courseFinishHours;

    /**
     * 此课程教学老师
     */
    private int userId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 课程介绍
     */
    private String courseIntroduction;
}
