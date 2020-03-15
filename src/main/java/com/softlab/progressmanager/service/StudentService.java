package com.softlab.progressmanager.service;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.model.Student;

import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className StudentService
 * @description 学生service层
 * @date 2020/3/13 21:56
 */
public interface StudentService {

    /**
     * 添加一个学生
     * @param student
     * @return
     * @throws ProException
     */
    RestData insertStudent(Student student)throws ProException;

    /**
     * 批量添加学生
     * 思路为：前端传入一个List<Student>,for循环获取，先查看是不是有这个学生，
     * 没有就添加，记录添加成功。有的话，就记录已经有这名学生了
     * 出错就抛出异常
     * @param students
     * @return
     * @throws ProException
     */
    List<Map<Integer, String>> insertStudents(List<Student> students) throws ProException;

    /**
     * 删除指定id的学生
     * @param studentId
     * @return
     * @throws ProException
     */
    RestData deleteStudentById(int studentId) throws ProException;

    /**
     * 修改指定学生信息
     * @param studentId
     * @param student
     * @return
     * @throws ProException
     */
    RestData updateStudentById(int studentId, Student student) throws ProException;

    /**
     * 查找指定id的学生
     * @param studentId
     * @return
     * @throws ProException
     */
    Map<String, Object> selectStudentById(int studentId) throws ProException;

}
