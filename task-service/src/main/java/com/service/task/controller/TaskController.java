package com.service.task.controller;

import com.service.task.model.TaskDetails;
import com.service.task.service.TaskAssignedService;
import com.service.task.service.TaskDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskDetailsService taskDetailsService;

    private final TaskAssignedService taskAssignedService;

    @Autowired
    public TaskController(TaskDetailsService service, TaskAssignedService taskAssigned){
        taskDetailsService =service;
        taskAssignedService=taskAssigned;
    }

    @GetMapping("/getTaskById")
    public TaskDetails getTaskById(@RequestParam Long taskId){
        return taskDetailsService.getTaskById(taskId);
    }

    @GetMapping("/getAllTasks")
    public List<TaskDetails> getAllTasks(){
        return taskDetailsService.getAllTasks();
    }

    @PostMapping("/createTask")
    public TaskDetails createTask(@RequestBody TaskDetails taskDetails, @RequestParam List<Long> stuRollNums){
        TaskDetails t= taskDetailsService.createTask(taskDetails, stuRollNums);
        taskAssignedService.addManyStudentsTasks(taskDetails.getTaskId(), stuRollNums);
        return t;
    }

    @DeleteMapping("/deleteTaskById")
    public TaskDetails deleteTaskById(@RequestParam Long taskId){
        TaskDetails task= taskDetailsService.deleteTask(taskId);
        taskAssignedService.deleteTaskById(taskId);
        return task;
    }

    @PutMapping("/updateTask")
    public TaskDetails updateTask(@RequestBody TaskDetails taskDetails){
        return taskDetailsService.updateTask(taskDetails);
    }

    @PostMapping("/addStudentsToTask")
    public void addStudentsToTask(@RequestParam Long taskId, @RequestParam List<Long> stuIds){
         taskAssignedService.addManyStudentsTasks(taskId,stuIds);
    }

    @PostMapping("/addStudentToTask")
    public void addStudentToTask(@RequestParam Long taskId, @RequestParam Long stuIds){
        taskAssignedService.addStudentToTask(taskId,stuIds);
    }

    @DeleteMapping("/deleteStudentFromTask")
    public void deleteStudentFromTask(@RequestParam Long taskId, @RequestParam Long stuId){
        taskAssignedService.deleteStudentFromTask(stuId,taskId);
    }

    @DeleteMapping("/deleteAllStudentTasks")
    public void deleteAllStudentTasks(@RequestParam Long rollNum){
        taskAssignedService.deleteAllStudentTasks(rollNum);
    }

    @GetMapping("/getTasksOfTeacher")
    public List<Long> getTasksOfTeacher(Long empId){
        return taskDetailsService.getTasksOfTeacher(empId);
    }

    @GetMapping("/getTasksOfStudent")
    public List<Long> getTasksOfStudent(Long rollNum){
        return taskAssignedService.getTasksByStudentRoll(rollNum);
    }
}
