package com.service.teacher.service;

import com.service.teacher.model.Teacher;
import com.service.teacher.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImpleTest {

    @Mock
    private TeacherRepository teacherRepository;

    private TeacherServiceImple teacherService;

    @BeforeEach
    void setUp() {
        this.teacherService = new TeacherServiceImple(teacherRepository);
    }

    @Test
    void readTeacher() {
        Teacher teacher=new Teacher(11,"Reni","reni@sjs.com","9819201022");
        when(teacherRepository.findById(teacher.getEmployeeId())).thenReturn(Optional.of(teacher));

        teacherService.readTeacher(teacher.getEmployeeId());
        verify(teacherRepository).findById(teacher.getEmployeeId());
    }

    @Test
    void getAllTeachers() {
        teacherService.getAllTeachers();
        verify(teacherRepository).findAll();
    }

    @Test
    void updateTeacher() {
        Teacher teacher=new Teacher(11,"Danny","danny@sjs.com","9819211022");
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        teacherService.updateTeacher(teacher);
        verify(teacherRepository).save(teacher);
    }

}