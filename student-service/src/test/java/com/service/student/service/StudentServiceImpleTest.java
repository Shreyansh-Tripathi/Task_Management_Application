package com.service.student.service;

import com.service.student.model.Student;
import com.service.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImpleTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentServiceImple studentService;

    @BeforeEach
    void setUp() {
        this.studentService=new StudentServiceImple(studentRepository);
    }

    @Test
    void getStudent() {
        Student student=new Student(11,"Sanju","sanju@sjs.com","9876567890",2L);
        when(studentRepository.findById(student.getRollNumber())).thenReturn(Optional.of(student));

        studentService.getStudent(student.getRollNumber());
        verify(studentRepository).findById(student.getRollNumber());
    }

    @Test
    void getAllStudents() {
        studentService.getAllStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void updateStudent() {
        Student student=new Student(11,"Danny","Danny@sjs.com","9876967890",2L);
        when(studentRepository.save(student)).thenReturn(student);

        studentService.updateStudent(student);
        verify(studentRepository).save(student);
    }

    @Test
    void addTeacher() {
        Long roll=1L;
        Long empId=1L;

        assertThat(studentService.addTeacher(roll, empId)).isEqualTo("Success");
    }

    @Test
    void deleteTeacherOfStudent() {
        Student student=new Student(1L,"Sanju","sanju@sjs.com","9876567890",2L);
//        when(studentRepository.save(student)).thenReturn(student);
//
//        studentRepository.save(student);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        assertThat(studentService.deleteTeacherOfStudent(1L)).isEqualTo("Success");
    }

    @Test
    void deleteTeachersWithId() {
        Long id=1L;

        assertThat(studentService.deleteTeachersWithId(id)).isEqualTo("Success");
    }
}