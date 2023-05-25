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


    @GetMapping("/getStudentsOfTeacher")
    public List<Long> getStudentsOfTeacher(@RequestParam Long empId){
        return teacherService.getStudentsOfTeacher(empId);
    }

    @GetMapping("/getTasksOfTeacher")
    public List<Long> getTasksOfTeacher(@RequestParam Long empId){
        return teacherService.getTasksOfTeacher(empId);
    }

    //to import teachers data
    @PostMapping("/jsonImport")
    public List<Teacher> jsonImport(@RequestBody List<Teacher> teachers){
        return teacherService.jsonImport(teachers);
    }

}
