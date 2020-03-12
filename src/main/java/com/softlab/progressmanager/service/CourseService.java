package com.softlab.progressmanager.service;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.model.Course;

import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className CourseService
 * @description 课程service层
 * @date 2020/3/11 19:48
 */
public interface CourseService {

    /**
     * 添加一个课程
     * @param course
     * @return
     * @throws ProException
     */
    RestData insertCourse(Course course) throws ProException;

    /**
     * 删除指定 id的课程
     * @param courseId
     * @return
     * @throws ProException
     */
    RestData deleteCourseById(int courseId) throws ProException;

    /**
     * 修改指定id的课程
     * @param courseId
     * @param course
     * @return
     * @throws ProException
     */
    RestData updateCourseById(int courseId, Course course) throws ProException;

    /**
     * 查找指定id的课程
     * @param courseId
     * @return
     * @throws ProException
     */
    Map<String, Object> selectCourseById(int courseId) throws ProException;

    /**
     * 根据不同条件查找
     * @param course
     * @return
     * @throws ProException
     */
    List<Map<String, Object>> selectCourseByCondition(Course course) throws ProException;

    /**
     * 计算课程进度
     * @param courseId
     * @return
     * @throws ProException
     */
    int calculateProgressById(int courseId) throws ProException;

    /**
     * 查看视频上传进度
     * @param courseId
     * @return
     * @throws ProException
     */
    String selectVideoProgress(int courseId) throws ProException;

    /**
     * 查看该用户所有的课程
     * @param userId
     * @return
     * @throws ProException
     */
    List<Map<String, Object>> selectCourseByUserId(int userId) throws ProException;

    /**
     * 更新完成课时时间
     * @param increaseHours
     * @param courseId
     * @return
     * @throws ProException
     */
    RestData updateFinishHours(int increaseHours, int courseId) throws ProException;
}
