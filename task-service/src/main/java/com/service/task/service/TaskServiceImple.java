package com.service.task.service;

import com.service.task.model.Task;
import com.service.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImple implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task readtask(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("cannot find task with id :"+taskId));
    }

    @Override
    public Task deleteTask(Long taskId) {
        Task task = readtask(taskId);
        taskRepository.deleteById(taskId);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        Task newTask = readtask(task.getTaskId());
        if(task.getName()!=null)
            newTask.setName(task.getName());
        if(task.getDescription()!=null)
            newTask.setDescription(task.getDescription());

        return taskRepository.save(newTask);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Long> getAllStudents(Long taskId) {
        return taskRepository.findStudentsByTaskId(taskId);
    }

    @Override
    public void addNewStudents(Long taskId, List<Long> stuIds) {
        List<Long> studentIds=getAllStudents(taskId);
        studentIds.addAll(stuIds);
        taskRepository.addNewStudents(taskId,studentIds);
    }
}
