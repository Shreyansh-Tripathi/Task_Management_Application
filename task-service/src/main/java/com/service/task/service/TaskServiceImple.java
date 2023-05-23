package com.service.task.service;

import com.service.task.model.TaskDetails;
import com.service.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImple implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskDetails createTask(TaskDetails taskDetails) {
        return taskRepository.save(taskDetails);
    }

    @Override
    public TaskDetails readtask(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("cannot find task with id :"+taskId));
    }

    @Override
    public TaskDetails deleteTask(Long taskId) {
        TaskDetails taskDetails = readtask(taskId);
        taskRepository.deleteById(taskId);
        return taskDetails;
    }

    @Override
    public TaskDetails updateTask(TaskDetails taskDetails) {
        TaskDetails newTaskDetails = readtask(taskDetails.getTaskId());
        if(taskDetails.getName()!=null)
            newTaskDetails.setName(taskDetails.getName());
        if(taskDetails.getDescription()!=null)
            newTaskDetails.setDescription(taskDetails.getDescription());
        newTaskDetails.setStudentIds(taskDetails.getStudentIds());

        return taskRepository.save(newTaskDetails);
    }

    @Override
    public List<TaskDetails> getAllTasks() {
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
        taskRepository.updateStudents(taskId,studentIds);
    }

    @Override
    public void deleteStudent(Long taskId, Long stuId) {
        List<Long> studentIds=getAllStudents(taskId);
        studentIds.remove(stuId);
        taskRepository.updateStudents(taskId,studentIds);
    }
}
