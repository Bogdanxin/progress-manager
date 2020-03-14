package com.softlab.progressmanager.core.mapper;

import com.softlab.progressmanager.core.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author gwx
 * @version 1.0
 * @className StudentMapper
 * @description 学生类对数据库操作
 * @date 2020/3/13 19:56
 */
@Mapper
@Repository
public interface StudentMapper {

    /**
     * 添加一个学生
     * @param student
     * @return
     */
    int insertStudent(Student student);

    /**
     * 删除指定id一个学生
     * @param studentId
     * @return
     */
    int deleteStudentById(int studentId);

    /**
     * 修改一个指定id的student
     * @param studentId
     * @param student
     * @return
     */
    int updateStudentById(@Param("studentId") int studentId,
                          @Param("student") Student student);

    /**
     * 查找指定id的学生
     * @param studentId
     * @return
     */
    Student selectStudentById(int studentId);

    /**
     * 缺席学生记录
     * @param studentId
     * @return
     */
    int absenceStudent(int studentId);

    /**
     * 解除一次缺席
     * @param studentId
     * @return
     */
    int absenceStudentLift(int studentId);
}
