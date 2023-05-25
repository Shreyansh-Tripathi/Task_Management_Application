package com.service.teacher.client;

import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange
public interface TaskClient {
    @GetExchange("/getTasksOfTeacher")
    public List<Long> getTasksOfTeacher(Long empId);
}
