package com.softlab.progressmanager.core.model;

import lombok.Data;

/**
 * @author gwx
 * @version 1.0
 * @className Student
 * @description 学生类，主要用于记录缺席
 * @date 2020/3/13 19:49
 */
@Data
public class Student {

    /**
     * 学生id，20********
     */
    private int studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 缺席次数
     */
    private int absenceTimes;
}
