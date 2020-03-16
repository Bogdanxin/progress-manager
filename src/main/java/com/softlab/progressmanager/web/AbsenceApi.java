package com.softlab.progressmanager.web;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.JsonUtils;
import com.softlab.progressmanager.common.utils.VerifyUtil;
import com.softlab.progressmanager.core.model.Absence;
import com.softlab.progressmanager.service.AbsenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author gwx
 * @version 1.0
 * @className AbsenceApi
 * @description 签到的web层
 * @date 2020/3/14 11:05
 */
@RestController
@CrossOrigin(origins = "*")
public class AbsenceApi {

    private final static Logger logger = LoggerFactory.getLogger(AbsenceApi.class);

    private final AbsenceService absenceService;

    @Autowired
    public AbsenceApi(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @PostMapping(value = "/addAbsence")
    public RestData addAbsence(@RequestBody Absence absence, HttpServletRequest request){
        logger.info("add absence :" + JsonUtils.getJsonFromObj(absence));

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return absenceService.insertAbsence(absence);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @PostMapping(value = "/addAbsences")
    public RestData addAbsences(@RequestBody List<Absence> absences,
                                HttpServletRequest request){
        logger.info("add absence : " + JsonUtils.getJsonFromObj(absences));

        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1, "用户未授权！");
        }

        try {
            return new RestData(absenceService.insertAbsences(absences));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }

    }

    @DeleteMapping(value = "/deleteAbsence")
    public RestData deleteAbsence(@RequestParam("courseId") int courseId,
                                  @RequestParam("studentId") int studentId,
                                  HttpServletRequest request){
        logger.info("delete absence by course id: " + courseId + "and student id :" + studentId);

        if (VerifyUtil.verifyUserType(request) != 1) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return absenceService.deleteAbsence(studentId, courseId);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getAbsence")
    public RestData getAbsence(@RequestParam("id") int courseId,
                               @RequestParam("date") String date,
                               HttpServletRequest request){
        logger.info("get absence by course id :" + courseId);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return new RestData(absenceService.selectCourseAbsenceById(courseId,date));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getAbsenceByDate")
    public RestData getAbsenceByDate(@RequestParam("date") String date,
                                     HttpServletRequest request){
        logger.info("get absence by date : " + date);
        if (VerifyUtil.verifyUserType(request) != 0) {
            return new RestData(1,"用户未授权！");
        }

        try {
            return new RestData(absenceService.selectAbsenceByDate(date));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }
}
