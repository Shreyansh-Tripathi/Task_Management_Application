package com.service.task.service;

import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskAssignedService {

    public List<Long> getStudentsByTaskId(Long taskId);

    public List<Long> getTasksByStudentRoll(Long rollNum);

    public void deleteStudentAssigned(Long rollNumber, Long taskId);

    public void deleteTaskById(Long taskId);

    public void deleteAllStudentTasks(Long rollNum);

    public void addNewStudent(Long taskId, Long rollNum);

    public void addManyStudents(Long taskId, List<Long> rollNums);
}
