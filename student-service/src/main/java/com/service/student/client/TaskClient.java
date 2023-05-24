package com.service.student.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange
public interface TaskClient {
    @DeleteExchange("/deleteAllStudentTasks")
    public void deleteAllStudentTasks(@RequestParam Long rollNum);

}
