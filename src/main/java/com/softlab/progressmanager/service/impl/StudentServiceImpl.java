package com.softlab.progressmanager.service.impl;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.mapper.StudentMapper;
import com.softlab.progressmanager.core.model.Student;
import com.softlab.progressmanager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className StudentServiceImpl
 * @description 实现StudentService接口
 * @date 2020/3/14 9:28
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }


    @Override
    public RestData insertStudent(Student student) throws ProException {
        if (studentMapper.selectStudentById(student.getStudentId()) != null) {
            throw new ProException("添加失败！已经有该学生的信息！");
        }

        if (studentMapper.insertStudent(student) > 0) {
            return new RestData(0,"添加成功！");
        }else {
            throw new ProException("添加失败！");
        }
    }

    @Override
    public RestData deleteStudentById(int studentId) throws ProException {
        if (studentMapper.deleteStudentById(studentId) > 0) {
            return new RestData(0,"删除成功!");
        }else {
            throw new ProException("删除失败！");
        }
    }

    @Override
    public RestData updateStudentById(int studentId, Student student) throws ProException {
        if (studentMapper.updateStudentById(studentId, student) > 0) {
            return new RestData(0,"修改成功！");
        }else {
            throw new ProException("修改失败!");
        }
    }

    @Override
    public Map<String, Object> selectStudentById(int studentId) throws ProException {
         Map<String, Object> map = new HashMap<>();
         Student student = studentMapper.selectStudentById(studentId);
        if (student != null) {
            map.put("studentId",student.getStudentId());
            map.put("studentName", student.getStudentName());
            map.put("absenceTimes", student.getAbsenceTimes());
        }else {
            throw new ProException("查找失败！");
        }

        return map;
    }
}
