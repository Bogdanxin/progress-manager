package com.softlab.progressmanager.service;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.model.Absence;

import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className AbsenceService
 * @description 缺席类service层
 * @date 2020/3/13 21:55
 */
public interface AbsenceService {

    /**
     * 添加一个签到
     * @param absence
     * @return
     * @throws ProException
     */
    RestData insertAbsence(Absence absence) throws ProException;

    /**
     * 批量添加签到
     * @param absences
     * @return
     * @throws ProException
     */
    List<Map<Integer, String>> insertAbsences(List<Absence> absences) throws ProException;

    /**
     * 删除一个签到，指的是一个学生某节课的签到
     * @param studentId
     * @param courseId
     * @return
     * @throws ProException
     */
    RestData deleteAbsence(int studentId, int courseId) throws ProException;

    /**
     * 查看指定课程的签到
     * @param courseId
     * @param date
     * @return
     * @throws ProException
     */
    Map<String, Object> selectCourseAbsenceById(int courseId, String date) throws ProException;

    /**
     * 查看指定日期的签到
     * @param date
     * @return
     * @throws ProException
     */
    List<Map<String, Object>> selectAbsenceByDate(String date) throws ProException;
}
