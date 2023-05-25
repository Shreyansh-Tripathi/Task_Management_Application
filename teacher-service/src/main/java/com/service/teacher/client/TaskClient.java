package com.service.teacher.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange
public interface TaskClient {
    @GetMapping("/getTaskIdsOfTeacher")
    public List<Long> getTaskIdsOfTeacher(Long empId);
}
