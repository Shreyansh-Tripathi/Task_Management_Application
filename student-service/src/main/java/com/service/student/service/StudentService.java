package com.service.student.service;

import com.service.student.model.Student;

import java.util.List;

public interface StudentService {
    public Student createStudent(Student student);

    public Student getStudent(Long rollNum);

    public List<Student> getAllStudents();

    public List<Student> getStudentsOfTeacher(long empId);

    public Student deleteStudent(Long rollNum);

    public Student updateStudent(Student student);

    public Long getCoordinator(Long empId);

    public void addTeacher(Long stuId,Long empId);

    public void deleteTeacherOfStudent(Long stuId);

    public void deleteTeachersWithId(Long empId);

    public List<Long> getTasksOfStudent(Long rollNum);

    List<Student> jsonImport(List<Student> teachers);
}
