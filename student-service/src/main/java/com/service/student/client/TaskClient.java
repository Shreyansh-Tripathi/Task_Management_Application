package com.service.student.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange
public interface TaskClient {

    @PatchExchange("/deleteStudentFromTask")
    public void deleteStudentFromTask(@RequestParam Long taskId, @RequestParam Long stuId);

    @DeleteExchange("/deleteAllStudentTasks")
    public void deleteAllStudentTasks(@RequestParam Long rollNum);

    @PostExchange("/addStudentToTask")
    public void addStudentToTask(@RequestParam Long taskId, @RequestParam Long stuIds);

    @GetExchange("/getTasksOfStudent")
    public List<Long> getTasksOfStudent(Long rollNum);
}
