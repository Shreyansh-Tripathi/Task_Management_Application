package com.service.student.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;

@HttpExchange
public interface TeacherClient {
    @PatchExchange("/teachers/addNewStudent")
    public void addNewStudent(@RequestParam Long empId, @RequestParam Long stuRollNum);

    @PatchExchange("/removeStudent")
    public void removeStudent(@RequestParam Long empId,@RequestParam Long stuRollNum);
}
