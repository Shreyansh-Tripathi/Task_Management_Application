package com.service.task.service;

import com.service.task.enums.StatusType;
import com.service.task.model.TaskAssigned;
import com.service.task.model.TaskDetails;
import com.service.task.repository.TaskAssignedRepository;
import com.service.task.repository.TaskDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImpleTest {

    @Mock
    private TaskAssignedRepository taskAssignedRepository;
    @Mock
    private TaskDetailsRepository taskDetailsRepository;
    private TaskServiceImple taskService;

    @BeforeEach
    void setUp() {
        taskService=new TaskServiceImple(taskDetailsRepository,taskAssignedRepository);
    }

    @Test
    void createTask() {
        TaskDetails task = new TaskDetails(1,"Task", "Do this task",1L);
        when(taskDetailsRepository.save(task)).thenReturn(task);

        taskService.createTask(task);
        verify(taskDetailsRepository).save(task);
    }

    @Test
    void getTaskById() {
        TaskDetails task = new TaskDetails(1,"Task", "Do this task",1L);
        when(taskDetailsRepository.findById(task.getTaskId())).thenReturn(Optional.of(task));

        taskService.getTaskById(task.getTaskId());
        verify(taskDetailsRepository).findById(task.getTaskId());
    }

    @Test
    void deleteTaskDetails() {
        TaskDetails task = new TaskDetails(1,"Task", "Do this task",1L);
        when(taskDetailsRepository.findById(task.getTaskId())).thenReturn(Optional.of(task));


        taskService.deleteTaskDetails(task.getTaskId());
        verify(taskDetailsRepository).deleteById(task.getTaskId());
    }

    @Test
    void updateTask() {
        TaskDetails task = new TaskDetails(1,"Task", "Do this task",1L);
        when(taskDetailsRepository.save(task)).thenReturn(task);

        taskService.updateTask(task);
        verify(taskDetailsRepository).save(task);
    }

    @Test
    void getAllTasks() {
        taskService.getAllTasks();
        verify(taskDetailsRepository).findAll();
    }

    @Test
    void getTasksOfTeacher() {
        taskService.getTasksOfTeacher(1L);
        verify(taskDetailsRepository).getTasksOfTeacher(1L);
    }

    @Test
    void getTaskIdsOfTeacher() {
        taskService.getTaskIdsOfTeacher(1L);
        verify(taskDetailsRepository).getTaskIdsOfTeacher(1L);
    }

    @Test
    void getStudentsByTaskId() {
        taskService.getStudentsByTaskId(1L);
        verify(taskAssignedRepository).getStudentsOfTask(1L);
    }

    @Test
    void getTaskIdsOfStudent() {
        taskService.getTaskIdsOfStudent(1L);
        verify(taskAssignedRepository).getTasksOfStudent(1L);
    }

    @Test
    void getTasksStatus() {
        taskService.getTasksStatus();
        verify(taskAssignedRepository).findAll();
    }

    @Test
    void deleteStudentFromTask() {
        taskService.deleteStudentFromTask(1L,1L);
        verify(taskAssignedRepository).deleteStudentFromTask(1L,1L);
    }

    @Test
    void deleteTaskById() {
        taskService.deleteTaskById(1L);
        verify(taskAssignedRepository).deleteTaskById(1L);
    }

    @Test
    void deleteAllStudentTasks() {
        taskService.deleteAllStudentTasks(1L);
        verify(taskAssignedRepository).deleteAllStudentTasks(1L);
    }

    @Test
    void addStudentToTask() {
        TaskAssigned task=new TaskAssigned();
        task.setTaskId(1L);
        task.setStudentRollNum(1L);
        task.setStatus(StatusType.DUE);
        when(taskAssignedRepository.save(task)).thenReturn(task);

        taskAssignedRepository.save(task);
        assertThat(taskService.addStudentToTask(1L,1L)).isEqualTo("Success");
    }

    @Test
    void checkTaskStatus() {
        taskService.checkTaskStatus(1L,1L);
        verify(taskAssignedRepository).checkTaskStatus(1L,1L);
    }

    @Test
    void changeTaskStatus() {
        taskService.changeTaskStatus(1L,1L,StatusType.DUE);
        verify(taskAssignedRepository).changeTaskStatus(StatusType.DUE.name(), 1L,1L);
    }

    @Test
    void addManyStudentsToTask() {
        TaskAssigned task=new TaskAssigned();
        task.setTaskId(1L);
        task.setStudentRollNum(2L);
        task.setStatus(StatusType.DUE);
        when(taskAssignedRepository.save(task)).thenReturn(task);

        taskAssignedRepository.save(task);

        List<Long> rolls=new ArrayList<>(Arrays.asList(1L,2L));

        assertThat(taskService.addManyStudentsToTask(1L,rolls)).isEqualTo("Success");
    }
}