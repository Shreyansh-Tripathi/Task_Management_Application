package com.service.student.service;

import com.service.student.client.TaskClient;
import com.service.student.client.TeacherClient;
import com.service.student.model.Student;
import com.service.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

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
        if(student.getContact().isEmpty() || student.getName().isEmpty() || student.getEmail().isEmpty()){
            throw new RuntimeException("Input field(s) not provided");
        }
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long rollNum) {
        return studentRepository.findById(rollNum).orElseThrow(() -> new NoSuchElementException("Cannot find student with roll number: "+rollNum));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<HashMap<String,Object>> getStudentsOfTeacher(long empId) {
        List<HashMap<String,Object>> students=new ArrayList<>();
        for(Student student : studentRepository.getStudentsOfTeacher(empId)){
            HashMap<String,Object> map=student.studentDetailsAsMap();
            map.put("coordinator",teacherClient.readTeacherById(empId).name());
            students.add(map);
        }
        return students;
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
    public Long getCoordinator(Long rollNum) {
        return studentRepository.findCoordinator(rollNum);
    }

    @Override
    public void addTeacher(Long stuId, Long empId) {
        if(empId<=0){
            throw new RuntimeException("Cannot add invalid coordinator to student");
        }
        if(stuId<=0){
            throw new NoSuchElementException("Cannot find student");
        }
        studentRepository.updateTeacher(stuId, empId);
    }

    @Override
    public void deleteTeacherOfStudent(Long stuId) {
        studentRepository.updateTeacher(stuId, -1L);
    }

    @Override
    public void deleteTeachersWithId(Long empId) {
        if(empId<=0){
            throw new RuntimeException("Cannot DELETE invalid coordinator from student");
        }
        studentRepository.deleteTeachersWithId(empId);
    }

    @Override
    public List<Long> getTasksOfStudent(Long rollNum) {
        if(rollNum<=0){
            throw new NoSuchElementException("Cannot find student");
        }
        return taskClient.getTaskIdsOfStudent(rollNum);
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
