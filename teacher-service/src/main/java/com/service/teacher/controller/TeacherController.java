package com.service.teacher.controller;

import com.service.teacher.client.StudentClient;
import com.service.teacher.client.TaskClient;
import com.service.teacher.model.Teacher;
import com.service.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private TaskClient taskClient;

    public TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService service){
        teacherService=service;
    }

    @GetMapping("/getTeacherById")
    public Teacher readTeacherById(@RequestParam Long empId){
        return teacherService.readTeacher(empId);
    }

    @GetMapping("/getAllTeachers")
    public List<Teacher> getAllTeachers(){
        return teacherService.getAllTeachers();
    }

    @PostMapping("/createTeacher")
    public Teacher createTeacher(@RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    @DeleteMapping("/deleteTeacherById")
    public Teacher deleteTeacherById(@RequestParam Long empId){
        return teacherService.deleteTeacher(empId);
    }

    @PutMapping("/updateTeacher")
    public Teacher updateTeacher(@RequestBody Teacher teacher){
        return teacherService.updateTeacher(teacher);
    }

    @PatchMapping("/addNewTask")
    public void addNewTask(@RequestParam Long empId,@RequestParam Long taskId){
        teacherService.addTask(empId,taskId);
    }

    @PatchMapping("/addNewStudent")
    public void addNewStudent(@RequestParam Long empId,@RequestParam Long stuRollNum){
        teacherService.addStudent(empId,stuRollNum);
    }




    @PostMapping("/jsonImport")
    public List<Teacher> jsonImport(@RequestBody List<Teacher> teachers){
        return teacherService.jsonImport(teachers);
    }

}
