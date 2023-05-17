package com.service.student.controller;

import com.service.student.model.Student;
import com.service.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService service){
        studentService=service;
    }

    @GetMapping("/getStudentByRollNumber")
    public Student getStudentByRollNumber(@RequestParam Long rollNum){
        return studentService.getStudent(rollNum);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @DeleteMapping("/deleteStudentByRollNumber")
    public Student deleteStudentByRollNumber(@RequestParam Long rollNum){
        return studentService.deleteStudent(rollNum);
    }

    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

//    @PatchMapping("/addNewTask")
//    public void addNewTask(@RequestBody){
//
//    }
//
//
//
//
//    @PostMapping("/jsonImport")
//    public List<Teacher> jsonImport(@RequestBody List<Teacher> teachers){
//        return teacherService.jsonImport(teachers);
//    }

}
