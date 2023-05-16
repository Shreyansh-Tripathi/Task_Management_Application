package com.service.student.service;

import com.service.student.model.Student;
import com.service.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
