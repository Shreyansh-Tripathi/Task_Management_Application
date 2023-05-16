package com.service.student.service;

import com.service.student.model.Student;

import java.util.List;

public interface StudentService {
    public Student createStudent(Student student);

    public Student getStudent(Long rollNum);

    public List<Student> getAllStudents();

    public Student deleteStudent(Long rollNum);

    public Student updateStudent(Student student);

    public List<Long> getTasks(Long rollNum);

    public Long getCoordinator(Long empId);
}
