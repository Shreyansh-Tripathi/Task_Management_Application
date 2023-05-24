package com.service.student.controller;

import com.service.student.client.TaskClient;
import com.service.student.client.TeacherClient;
import com.service.student.model.Student;
import com.service.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

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

    @PostMapping("/addNewTask")
    public void addNewTask(@RequestParam Long rollNum,@RequestParam Long taskId){
        studentService.addNewTask(rollNum,taskId);
    }

    @PatchMapping("/deleteTask")
    public void deleteTask(@RequestParam Long rollNum,@RequestParam Long taskId){
        studentService.deleteTask(rollNum,taskId);
    }

    @PatchMapping("/addTeacher")
    public void addTeacher(@RequestParam Long rollNum,@RequestParam Long empId){
        studentService.addTeacher(rollNum,empId);
    }

    @PatchMapping("/removeTeacher")
    public void removeTeacher(@RequestParam Long rollNum){
        studentService.deleteTeacher(rollNum);
    }

    @PostMapping("/jsonImport")
    public List<Student> jsonImport(@RequestBody List<Student> students){
        return studentService.jsonImport(students);
    }
}
