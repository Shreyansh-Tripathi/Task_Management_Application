package com.service.student.service;

import com.service.student.model.Student;
import com.service.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImple implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long rollNum) {
        return studentRepository.findById(rollNum).orElseThrow(() -> new RuntimeException("cannot find student with roll number: "+rollNum));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student deleteStudent(Long rollNum) {
        Student student=getStudent(rollNum);
        studentRepository.deleteById(rollNum);
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        Student newStudent = getStudent(student.getRollNumber());
        if(student.getName()!=null)
            newStudent.setName(student.getName());
        if(student.getEmail()!=null)
            newStudent.setEmail(student.getEmail());
        if(student.getContact()!=null)
            newStudent.setContact(student.getContact());
        return studentRepository.save(newStudent);
    }

    @Override
    public List<Long> getTasks(Long rollNum) {
        return studentRepository.findTasksByStudentId(rollNum);
    }

    @Override
    public Long getCoordinator(Long empId) {
        return studentRepository.findByCoordinator(empId);
    }

    @Override
    public void addNewTask(Long stuId,Long taskId) {
        List<Long> tasks=getTasks(stuId);
        tasks.add(taskId);
        studentRepository.addNewTask(stuId,tasks);
    }

    @Override
    public void deleteTask(Long stuId, Long taskId) {
        List<Long> tasks=getTasks(stuId);
        tasks.remove(taskId);
        studentRepository.addNewTask(stuId,tasks);
    }

    @Override
    public void addTeacher(Long stuId, Long empId) {
        studentRepository.updateTeacher(stuId, empId);
    }

    @Override
    public void deleteTeacher(Long stuId) {
        studentRepository.updateTeacher(stuId, -1L);
    }

    @Override
    public List<Student> jsonImport(List<Student> students) {
        students.forEach(
                student -> {
                    student.setTaskIds(new ArrayList<Long>());
                    student.setCoordinator((long)2);
                }
        );
        return studentRepository.saveAll(students);
    }
}
