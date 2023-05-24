package com.service.task.service;

import java.util.List;

public interface TaskAssignedService {

    public List<Long> getStudentsByTaskId(Long taskId);

    public List<Long> getTasksByStudentRoll(Long rollNum);

    public void deleteStudentFromTask(Long rollNumber, Long taskId);

    public void deleteTaskById(Long taskId);

    public void deleteAllStudentTasks(Long rollNum);

    public void addStudentTask(Long taskId, Long rollNum);

    public void addManyStudentsTasks(Long taskId, List<Long> rollNums);
}
