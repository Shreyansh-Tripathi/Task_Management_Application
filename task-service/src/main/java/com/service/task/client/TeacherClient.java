package com.service.task.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;

@HttpExchange
public interface TeacherClient {
    @PatchExchange("/teachers/addNewTask")
    public void addNewTask(@RequestParam Long empId, @RequestParam Long taskId);
}
