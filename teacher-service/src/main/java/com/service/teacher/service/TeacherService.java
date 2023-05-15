package com.service.teacher.service;

import com.service.teacher.model.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher createTeacher(Teacher teacher);

    public Teacher readTeacher(String empId);

    public List<Teacher> getAllTeachers();

    public Teacher deleteTeacher(String empId);

    public Teacher updateTeacher(Teacher teacher);

    List<Teacher> jsonImport(List<Teacher> teachers);
}
