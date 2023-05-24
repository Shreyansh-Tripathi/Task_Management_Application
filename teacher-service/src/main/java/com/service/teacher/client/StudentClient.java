package com.service.teacher.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.List;

@HttpExchange
public interface StudentClient {

    @GetExchange("/getStudentsOfTeacher")
    public List<Long> getStudentsOfTeacher(@RequestParam Long empId);

    @PatchExchange("/removeTeacherWithId")
    public void removeTeacherWithId(@RequestParam Long empId);
}
