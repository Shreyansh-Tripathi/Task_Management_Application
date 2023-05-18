package com.service.task.client;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PatchExchange;

@HttpExchange
public interface StudentClient {
    @PatchExchange("/students/addNewTask")
    public void addNewTask(@RequestParam Long rollNum, @RequestParam Long taskId);

    @PatchMapping("/students/deleteTask")
    public void deleteTask(@RequestParam Long rollNum,@RequestParam Long taskId);
}
