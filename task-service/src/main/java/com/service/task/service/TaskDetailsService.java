package com.service.task.service;

import com.service.task.model.TaskDetails;

import java.util.List;

public interface TaskDetailsService {

    TaskDetails createTask(TaskDetails taskDetails);

    public TaskDetails getTaskById(Long taskId);

    public TaskDetails deleteTaskDetails(Long taskId);

    public TaskDetails updateTask(TaskDetails taskDetails);

    public List<TaskDetails> getAllTasks();

    public List<Long> getTasksOfTeacher(Long empId);
}
