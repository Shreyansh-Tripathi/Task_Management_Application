package com.service.teacher.service;


import com.service.teacher.model.Teacher;
import com.service.teacher.repository.TeacherRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.core.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherServiceImple implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public WebClient taskWebClient, studentWebClient;

    @PostConstruct
    public void init(){
        taskWebClient= WebClient.builder()
                .baseUrl("http://localhost:8083/tasks")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        studentWebClient=WebClient.builder()
                .baseUrl("http://localhost:8082/students")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacher.setTaskIds(new ArrayList<Long>());
        teacher.setStudentIds(new ArrayList<Long>());
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher readTeacher(Long empId) {
        return teacherRepository.findById(empId).orElseThrow(() -> new RuntimeException("cannot find teacher with id :"+empId));
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher deleteTeacher(Long empId) {
        Teacher teacher = readTeacher(empId);
        List<Long> students = getStudents(empId);
        for(long id : students){
            studentWebClient.delete().uri("/removeTeacher?rollNum="+id);
        }
        teacherRepository.deleteById(empId);
        return teacher;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        Teacher newTeacher=readTeacher(teacher.getEmployeeId());
        if(teacher.getName()!=null)
            newTeacher.setName(teacher.getName());
        if(teacher.getEmail()!=null)
            newTeacher.setEmail(teacher.getEmail());
        if(teacher.getContact()!=null)
            newTeacher.setContact(teacher.getContact());
        return teacherRepository.save(newTeacher);
    }

    @Override
    public List<Long> getStudents(Long empId) {
        return teacherRepository.findByEmployeeId(empId);
    }

    @Override
    public List<Long> getTasks(Long empId) {
        return teacherRepository.findTasksByEmpId(empId);
    }

    @Override
    public void addTask(Long empId, Long taskId) {
        List<Long> tasks=getTasks(empId);
        tasks.add(taskId);
        teacherRepository.addNewTask(empId,tasks);
    }

    @Override
    public void deleteTask(Long empId, Long taskId) {
        List<Long> tasks=getTasks(empId);
        tasks.remove(taskId);
        teacherRepository.addNewTask(empId,tasks);
    }

    @Override
    public void addStudent(Long empId, Long studentId) {
        List<Long> students=getStudents(empId);
        System.out.println(students);
        students.add(studentId);
        System.out.println(students);
        teacherRepository.updateStudents(empId,students);
    }

    @Override
    public void removeStudent(Long empId, Long studentId) {
        List<Long> students=getStudents(empId);
        students.remove(studentId);
        teacherRepository.updateStudents(empId,students);
    }

    @Override
    public void addManyStudents(Long empId, List<Long> stuIds) {
        List<Long> students=getStudents(empId);
        System.out.println(students);
        students.addAll(stuIds);
        System.out.println(students);
        Teacher teacher=readTeacher(empId);
        teacher.setStudentIds(students);
        teacherRepository.save(teacher);
        System.out.println(students);
    }

    @Override
    public List<Teacher> jsonImport(List<Teacher> teachers) {
        teachers.forEach(
                teacher -> {
                    teacher.setTaskIds(new ArrayList<Long>());
                    teacher.setStudentIds(new ArrayList<Long>());
                }
        );
        return teacherRepository.saveAll(teachers);
    }
}
