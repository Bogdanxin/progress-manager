package com.softlab.progressmanager.core.mapper;

import com.softlab.progressmanager.core.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author gwx
 * @version 1.0
 * @program Course
 * @description course操作数据库方法
 * @date 2020/3/11 15:01
 */
@Mapper
@Repository
public interface CourseMapper {

    /**
     * 添加一个课程
     * @param course
     * @return
     */
    public int insertCourse(Course course);

    /**
     * 删除指定id的课程
     * @param courseId
     * @return
     */
    public int deleteCourseById(int courseId);

    /**
     * 修改指定id的课程
     * @param courseId
     * @param course
     * @return
     */
    public int updateCourseById(@Param("courseId") int courseId,
                                @Param("course") Course course);

    /**
     * 查找指定id的课程
     * @param courseId
     * @return
     */
    public Course selectCourseById(int courseId);

    /**
     * 查找符合不同条件的课程
     * @param course
     * @return
     */
    public List<Course> selectCourseByCondition(Course course);

    /**
     * 查看指定id的进程
     * @param courseId
     * @return
     */
    public int calculateProgressById(int courseId);

    /**
     * 查看指定id的课程视频进度
     * @param courseId
     * @return
     */
    public String selectVideoProgress(int courseId);

    /**
     * 查找指定用户id的所有课程
     * @param userId
     * @return
     */
    public List<Course> selectCourseByUserId(int userId);
}
