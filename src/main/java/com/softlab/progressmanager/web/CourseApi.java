package com.softlab.progressmanager.web;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.JsonUtils;
import com.softlab.progressmanager.common.utils.VerifyUtil;
import com.softlab.progressmanager.core.model.Course;
import com.softlab.progressmanager.service.impl.CourseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gwx
 * @version 1.0
 * @className CourseApi
 * @description 课程web层
 * @date 2020/3/12 16:07
 */
@RestController
@CrossOrigin(origins = "*")
public class CourseApi {

    private final static Logger logger = LoggerFactory.getLogger(CourseApi.class);
    private final CourseServiceImpl courseService;

    @Autowired
    public CourseApi(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "/addCourse")
    public RestData addCourse(@RequestBody Course course, HttpServletRequest request){
        logger.info("add course: " + JsonUtils.getJsonFromObj(course));

        if (VerifyUtil.verifyUserType(request) != 1) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.insertCourse(course);
        }catch (ProException ex){
            return new RestData(1,ex.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteCourseById")
    public RestData deleteCourseById(@RequestParam("id") int id,
                                     HttpServletRequest request){
        logger.info("delete course by id " + id);
        if (VerifyUtil.verifyUserType(request) != 1) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.deleteCourseById(id);
        }catch (ProException ex){
            return new RestData(1,ex.getMessage());
        }
    }

    @PostMapping(value = "/updateCourseById")
    public RestData updateCourseById(@RequestParam("id") int id,
                                     @RequestBody Course course,
                                     HttpServletRequest request){
        logger.info("update course"+ JsonUtils.getJsonFromObj(course) + "by id : " + id);

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }
        try {
            return courseService.updateCourseById(id, course);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getCourseById")
    public RestData getCourseById(@RequestParam("id") int id){
        logger.info("get course by id : " + id);

        try {
            return new RestData(courseService.selectCourseById(id));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getCalculation/{id}")
    public RestData getCalculation(@PathVariable int id){
        logger.info("get calculation progress by id : " + id);

        try {
            return new RestData(courseService.calculateProgressById(id)*100 + "%");
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getVideoProgress/{id}")
    public RestData getVideoProgress(@PathVariable int id){
        logger.info("get video progress by id : " + id );

        try {
            return new RestData(courseService.selectVideoProgress(id));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getCourseByUserId")
    public RestData getCoursesByUserId(@RequestParam("id") int id){
        logger.info("get courses by user id :" + id);

        try {
            return new RestData(courseService.selectVideoProgress(id));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/updateHours/{id}")
    public RestData updateHours(@RequestParam("increaseHours") int increaseHours,
                                @PathVariable int id,
                                HttpServletRequest request){
        logger.info("update hours " + increaseHours);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return courseService.updateFinishHours(increaseHours, id);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }
}
