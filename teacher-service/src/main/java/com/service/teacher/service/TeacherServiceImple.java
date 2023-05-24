package com.service.teacher.service;


import com.service.teacher.client.StudentClient;
import com.service.teacher.client.TaskClient;
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

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private TaskClient taskClient;

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
        studentClient.removeTeacherWithId(empId);
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
    public List<Long> getStudentsOfTeacher(Long empId) {
        return studentClient.getStudentsOfTeacher(empId);
    }


    //to import dummy json data
    @Override
    public List<Teacher> jsonImport(List<Teacher> teachers) {
        return teacherRepository.saveAll(teachers);
    }
}
