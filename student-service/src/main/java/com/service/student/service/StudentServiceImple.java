package com.service.student.service;

import com.service.student.client.TaskClient;
import com.service.student.client.TeacherClient;
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

    @Autowired
    private TeacherClient teacherClient;

    @Autowired
    private TaskClient taskClient;

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
    public List<Long> getStudentsOfTeacher(long empId) {
        return studentRepository.getStudentsOfTeacher(empId);
    }

    @Override
    public Student deleteStudent(Long rollNum) {
        Student student=getStudent(rollNum);
        taskClient.deleteAllStudentTasks(rollNum);
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
    public Long getCoordinator(Long empId) {
        return studentRepository.findByCoordinator(empId);
    }

    @Override
    public void addTeacher(Long stuId, Long empId) {
        studentRepository.updateTeacher(stuId, empId);
    }

    @Override
    public void deleteTeacherOfStudent(Long stuId) {
        studentRepository.updateTeacher(stuId, -1L);
    }

    @Override
    public void deleteTeachersWithId(Long empId) {
        studentRepository.deleteTeachersWithId(empId);
    }

    @Override
    public List<Student> jsonImport(List<Student> students) {
        students.forEach(
                student -> {
                    student.setCoordinator((long)2);
                }
        );
        return studentRepository.saveAll(students);
    }
}
