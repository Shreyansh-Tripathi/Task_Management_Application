package com.service.task.controller;

import com.service.task.model.Task;
import com.service.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService service){
        taskService=service;
    }

    @GetMapping("/getTaskById")
    public Task getTaskById(@RequestParam Long taskId){
        return taskService.readtask(taskId);
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/createTask")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @DeleteMapping("/deleteTaskById")
    public Task deleteTaskById(@RequestParam Long taskId){
        return taskService.deleteTask(taskId);
    }

    @PutMapping("/updateTask")
    public Task updateTeacher(@RequestBody Task task){
        return taskService.updateTask(task);
    }

    @PatchMapping("/addStudentToTask")
    public void addStudentToTask(@RequestParam Long taskId, @RequestParam Long stuId){
        taskService.addNewStudent(taskId,stuId);
    }

}
