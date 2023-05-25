package com.service.task.service;

import com.service.task.model.TaskDetails;
import org.springframework.scheduling.config.Task;

import java.util.List;

public interface TaskAssignedService {

    public List<Long> getStudentsByTaskId(Long taskId);

    public List<TaskDetails> getTasksOfStudent(Long rollNum);

    public List<Long> getTaskIdsOfStudent(Long rollNum);

    public void deleteStudentFromTask(Long rollNumber, Long taskId);

    public void deleteTaskById(Long taskId);

    public void deleteAllStudentTasks(Long rollNum);

    public void addStudentToTask(Long taskId, Long rollNum);

    public void addManyStudentsToTask(Long taskId, List<Long> rollNums);
}
