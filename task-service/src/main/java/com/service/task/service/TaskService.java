package com.service.task.service;

import com.service.task.model.Task;

import java.util.List;

public interface TaskService {
    public Task createTask(Task task);

    public Task readtask(Long taskId);

    public Task deleteTask(Long taskId);

    public Task updateTask(Task task);

    public List<Task> getAllTasks();

    public List<Long> getAllStudents(Long taskId);

    public void addNewStudent(Long taskId, Long stuId);
}
