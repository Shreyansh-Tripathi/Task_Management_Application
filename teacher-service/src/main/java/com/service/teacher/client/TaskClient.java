package com.service.teacher.client;

import com.service.teacher.request.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;

@HttpExchange
public interface TaskClient {

    @GetExchange("/getTaskById")
    public Task getTaskById(@RequestParam Long taskId);

    @PostExchange("/createTask")
    public Task createTask(@RequestBody Task task);

    @DeleteExchange("/deleteTaskById")
    public Task deleteTaskById(@RequestParam Long taskId);

    @PutExchange("/updateTask")
    public Task updateTeacher(@RequestBody Task task);

    @PatchExchange("/addStudentToTask")
    public void addStudentToTask(@RequestParam Long taskId, @RequestParam Long stuId);

}
