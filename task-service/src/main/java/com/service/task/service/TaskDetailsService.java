package com.service.task.service;

import com.service.task.model.TaskDetails;

import java.util.List;

public interface TaskDetailsService {

    TaskDetails createTask(TaskDetails taskDetails, List<Long> studentRollNums);

    public TaskDetails getTaskById(Long taskId);

    public TaskDetails deleteTask(Long taskId);

    public TaskDetails updateTask(TaskDetails taskDetails);

    public List<TaskDetails> getAllTasks();
}
