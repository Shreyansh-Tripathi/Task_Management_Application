package com.service.teacher.service;


import com.service.teacher.model.Teacher;
import com.service.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImple implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher readTeacher(String empId) {
        return teacherRepository.findById(empId).orElseThrow(() -> new RuntimeException("cannot find teacher with id :"+empId));
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher deleteTeacher(String empId) {
        Teacher teacher = teacherRepository.findById(empId).orElseThrow(() -> new RuntimeException("cannot find teacher with id :" + empId));
        teacherRepository.deleteById(empId);
        return teacher;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.findById(teacher.getEmployeeId()).map(
                newTeacher -> {
                    if(teacher.getName()!=null)
                        newTeacher.setName(teacher.getName());
                    if(teacher.getEmail()!=null)
                        newTeacher.setEmail(teacher.getEmail());
                    if(teacher.getContact()!=null)
                        newTeacher.setContact(teacher.getContact());
                    return teacherRepository.save(newTeacher);
                }
        ).orElseThrow(() -> new RuntimeException("teacher : " +teacher.getEmployeeId()+"not found"));
    }

    @Override
    public List<Teacher> jsonImport(List<Teacher> teachers) {
        return teacherRepository.saveAll(teachers);
    }
}
