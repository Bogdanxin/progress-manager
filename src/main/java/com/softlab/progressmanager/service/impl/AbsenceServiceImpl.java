package com.softlab.progressmanager.service.impl;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.core.mapper.AbsenceMapper;
import com.softlab.progressmanager.core.mapper.CourseMapper;
import com.softlab.progressmanager.core.mapper.StudentMapper;
import com.softlab.progressmanager.core.model.Absence;
import com.softlab.progressmanager.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gwx
 * @version 1.0
 * @className AbsenceServiceImpl
 * @description 实现AbsenceService接口，主要负责签到
 * @date 2020/3/14 9:50
 */
@Service
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceMapper absenceMapper;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    @Autowired
    public AbsenceServiceImpl(AbsenceMapper absenceMapper, StudentMapper studentMapper, CourseMapper courseMapper) {
        this.absenceMapper = absenceMapper;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }

    /**
     * 这里有一个严重的问题，是否添加一个学生选课表？
     * 因为添加学生信息时候会有问题
     */
    @Override
    public RestData insertAbsence(Absence absence) throws ProException {
        //首先查看学生是否存在，不存在则直接报错未登记该学生信息
        if (studentMapper.selectStudentById(absence.getStudentId()) == null) {
            throw new ProException("不存在该学生信息！请先添加学生信息！");
        }
        if (courseMapper.selectCourseById(absence.getCourseId()) == null) {
            throw new ProException("不存在该课程信息！请先添加该课程信息！");
        }
        //存在，则将学生未签到加一，未签到表中记录
        if (studentMapper.absenceStudent(absence.getStudentId()) > 0
                && absenceMapper.insertAbsence(absence) > 0) {
            return new RestData(0,"记录成功！");
        }else {
            throw new ProException("记录失败！");
        }
    }

    @Override
    public RestData deleteAbsence(int studentId, int courseId) throws ProException {
        //学生不存在，报错并要求添加该学生信息
        if (studentMapper.selectStudentById(studentId) == null) {
            throw new ProException("不存在该学生信息！请先添加后在进行记录！");
        }
        //存在，删除该记录，并给学生减一记录
        if (studentMapper.absenceStudentLift(studentId) > 0
                && absenceMapper.deleteAbsenceById(studentId, courseId) > 0) {
            return new RestData(0, "修改成功！");
        }else {
            throw new ProException("记录失败！");
        }
    }

    @Override
    public Map<String, Object> selectCourseAbsenceById(int courseId, String date) throws ProException {
        Map<String, Object> map = new HashMap<>();
        List<Absence> absences = absenceMapper.selectAbsenceById(courseId);
        if (absences != null && absences.size() > 0) {
            map.put("createTime", absences.get(0).getCreateTime());
            map.put("absenceStudentNum", absenceMapper.getStudentAbsenceNum(courseId, date));
            for (int i = 0;i < absences.size();i++){
                map.put("studentId" + i, absences.get(i).getStudentId());
            }
        }else {
            throw new ProException("查找失败！");
        }
        return map;
    }

    /**
     * 问题很大
     */
    @Override
    public List<Map<String, Object>> selectAbsenceByDate(String date) throws ProException {
        List<Map<String, Object>>  al = new ArrayList<>();
        List<Absence> absences = absenceMapper.selectAbsenceByDate(date);
        if (absences != null && absences.size() > 0) {
            for (Absence absence : absences){
                Map<String, Object> map = new HashMap<>();
                map.put("studentId", absence.getStudentId());
                map.put("courseId", absence.getCourseId());
                al.add(map);
            }
        }else {
            throw new ProException("查找失败！");
        }

        return al;
    }


}
