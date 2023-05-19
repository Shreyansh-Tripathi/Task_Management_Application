package com.service.teacher.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;

@HttpExchange
public interface StudentClient {

    @PatchExchange("/students/addTeacher")
    public void addTeacher(@RequestParam Long rollNum, @RequestParam Long empId);

    @PatchExchange("/settings/removeTeacher")
    public void removeTeacher(@RequestParam Long rollNum);
}
