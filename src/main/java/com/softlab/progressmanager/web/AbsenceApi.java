package com.softlab.progressmanager.web;

import com.softlab.progressmanager.common.ProException;
import com.softlab.progressmanager.common.RestData;
import com.softlab.progressmanager.common.utils.JsonUtils;
import com.softlab.progressmanager.core.model.Absence;
import com.softlab.progressmanager.service.AbsenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public RestData addAbsence(@RequestBody Absence absence){
        logger.info("add absence :" + JsonUtils.getJsonFromObj(absence));

        try {
            return absenceService.insertAbsence(absence);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteAbsence")
    public RestData deleteAbsence(@RequestParam("courseId") int courseId,
                                  @RequestParam("studentId") int studentId){
        logger.info("delete absence by course id: " + courseId + "and student id :" + studentId);

        try {
            return absenceService.deleteAbsence(studentId, courseId);
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getAbsence")
    public RestData getAbsence(@RequestParam("id") int courseId,
                               @RequestParam("date") String date){
        logger.info("get absence by course id :" + courseId);

        try {
            return new RestData(absenceService.selectCourseAbsenceById(courseId,date));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }

    @GetMapping(value = "/getAbsenceByDate")
    public RestData getAbsenceByDate(@RequestParam("date") String date){
        logger.info("get absence by date : " + date);

        try {
            return new RestData(absenceService.selectAbsenceByDate(date));
        }catch (ProException ex){
            return new RestData(1, ex.getMessage());
        }
    }
}
