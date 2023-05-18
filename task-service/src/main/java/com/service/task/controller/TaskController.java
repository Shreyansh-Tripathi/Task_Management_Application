package com.service.task.controller;

import com.service.task.client.StudentClient;
import com.service.task.client.TeacherClient;
import com.service.task.model.Task;
import com.service.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TeacherClient teacherClient;

    @Autowired
    private StudentClient studentClient;

    private final TaskService taskService;

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
        Task t= taskService.createTask(task);
        List<Long> students=task.getStudentIds();
        for(long id : students){
            studentClient.addNewTask(id,task.getTaskId());
        }
        teacherClient.addNewTask(task.getTeacherId(),task.getTaskId());
        return t;
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
    public void addStudentsToTask(@RequestParam Long taskId, @RequestParam List<Long> stuIds){
        taskService.addNewStudents(taskId,stuIds);
    }

}
