package com.service.task.service;

import com.service.task.model.TaskDetails;
import com.service.task.repository.TaskDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImple implements TaskDetailsService, TaskAssignedService {

    @Autowired
    private TaskDetailsRepository taskDetailsRepository;

    @Override
    public TaskDetails createTask(TaskDetails taskDetails) {
        return taskDetailsRepository.save(taskDetails);
    }

    @Override
    public TaskDetails getTaskById(Long taskId) {
        return taskDetailsRepository.findById(taskId).orElseThrow(() -> new RuntimeException("cannot find task with id :"+taskId));
    }

    @Override
    public TaskDetails deleteTask(Long taskId) {
        TaskDetails taskDetails = getTaskById(taskId);
        taskDetailsRepository.deleteById(taskId);
        return taskDetails;
    }

    @Override
    public TaskDetails updateTask(TaskDetails taskDetails) {
        TaskDetails newTaskDetails = getTaskById(taskDetails.getTaskId());
        if(taskDetails.getName()!=null)
            newTaskDetails.setName(taskDetails.getName());
        if(taskDetails.getDescription()!=null)
            newTaskDetails.setDescription(taskDetails.getDescription());

        return taskDetailsRepository.save(newTaskDetails);
    }

    @Override
    public List<TaskDetails> getAllTasks() {
        return taskDetailsRepository.findAll();
    }

//    @Override
//    public List<Long> getAllStudents(Long taskId) {
//        return taskDetailsRepository.findStudentsByTaskId(taskId);
//    }

//    @Override
//    public void addNewStudents(Long taskId, List<Long> stuIds) {
//        List<Long> studentIds=getAllStudents(taskId);
//        studentIds.addAll(stuIds);
//        taskDetailsRepository.updateStudents(taskId,studentIds);
//    }
//
//    @Override
//    public void deleteStudent(Long taskId, Long stuId) {
//        List<Long> studentIds=getAllStudents(taskId);
//        studentIds.remove(stuId);
//        taskDetailsRepository.updateStudents(taskId,studentIds);
//    }

    @Override
    public List<Long> getStudentsByTaskId(Long taskId) {
        return null;
    }

    @Override
    public List<Long> getTasksByStudentRoll(Long rollNum) {
        return null;
    }

    @Override
    public void deleteStudentAssigned(Long rollNumber, Long taskId) {

    }

    @Override
    public void deleteTaskById(Long taskId) {

    }

    @Override
    public void deleteAllStudentTasks(Long rollNum) {

    }

    @Override
    public void addNewStudent(Long taskId, Long rollNum) {

    }

    @Override
    public void addManyStudents(Long taskId, List<Long> rollNums) {

    }
}
