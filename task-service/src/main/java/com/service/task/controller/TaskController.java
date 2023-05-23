package com.service.task.controller;

import com.service.task.client.StudentClient;
import com.service.task.client.TeacherClient;
import com.service.task.model.TaskDetails;
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
    public TaskDetails getTaskById(@RequestParam Long taskId){
        return taskService.readtask(taskId);
    }

    @GetMapping("/getAllTasks")
    public List<TaskDetails> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/createTask")
    public TaskDetails createTask(@RequestBody TaskDetails taskDetails){
        TaskDetails t= taskService.createTask(taskDetails);
        List<Long> students= taskDetails.getStudentIds();
        for(long id : students){
            studentClient.addNewTask(id, taskDetails.getTaskId());
        }
        teacherClient.addNewTask(taskDetails.getTeacherId(), taskDetails.getTaskId());
        return t;
    }

    @DeleteMapping("/deleteTaskById")
    public TaskDetails deleteTaskById(@RequestParam Long taskId, @RequestParam Long teacherId, @RequestParam List<Long> studentIds){
        TaskDetails t= taskService.deleteTask(taskId);
        for(long id : studentIds){
            studentClient.deleteTask(id,taskId);
        }
        teacherClient.deleteTask(teacherId,taskId);
        return t;
    }

    @PutMapping("/updateTask")
    public TaskDetails updateTask(@RequestBody TaskDetails taskDetails){
        TaskDetails t= taskService.updateTask(taskDetails);
        List<Long> oldStudents=taskService.getAllStudents(taskDetails.getTaskId());
        List<Long> newStudents = taskDetails.getStudentIds();

        for(long id : oldStudents){
            if(!newStudents.contains(id))
                studentClient.deleteTask(id, taskDetails.getTaskId());
        }
        for(long id : newStudents){
            if(!oldStudents.contains(id))
                studentClient.addNewTask(id, taskDetails.getTaskId());
        }
        return t;
    }

    @PatchMapping("/addStudentToTask")
    public void addStudentsToTask(@RequestParam Long taskId, @RequestParam List<Long> stuIds){
        taskService.addNewStudents(taskId,stuIds);
    }

    @PatchMapping("/deleteStudentFromTask")
    public void deleteStudentFromTask(@RequestParam Long taskId, @RequestParam Long stuId){
        taskService.deleteStudent(taskId,stuId);
    }

}
