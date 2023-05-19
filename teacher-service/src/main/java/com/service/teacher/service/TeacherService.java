package com.service.teacher.service;

import com.service.teacher.model.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher createTeacher(Teacher teacher);

    public Teacher readTeacher(Long empId);

    public List<Teacher> getAllTeachers();

    public Teacher deleteTeacher(Long empId);

    public Teacher updateTeacher(Teacher teacher);

    public List<Long> getStudents(Long empId);

    public List<Long> getTasks(Long empId);

    public void addTask(Long empId, Long taskId);

    public void deleteTask(Long empId, Long taskId);

    public void addStudent(Long empId, Long studentId);

    public void removeStudent(Long empId, Long studentId);

    public void addManyStudents(Long empId, List<Long> stuIds);

    List<Teacher> jsonImport(List<Teacher> teachers);
}
