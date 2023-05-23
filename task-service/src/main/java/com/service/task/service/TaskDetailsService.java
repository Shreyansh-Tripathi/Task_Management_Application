package com.service.task.service;

import com.service.task.model.TaskDetails;

import java.util.List;

public interface TaskDetailsService {
    public TaskDetails createTask(TaskDetails taskDetails);

    public TaskDetails readtask(Long taskId);

    public TaskDetails deleteTask(Long taskId);

    public TaskDetails updateTask(TaskDetails taskDetails);

    public List<TaskDetails> getAllTasks();

    public List<Long> getAllStudents(Long taskId);

    public void addNewStudents(Long taskId, List<Long> stuIds);

    public void deleteStudent(Long taskId, Long stuId);
}
