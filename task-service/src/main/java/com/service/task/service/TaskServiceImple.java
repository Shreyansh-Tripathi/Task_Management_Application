package com.service.task.service;

import com.service.task.client.StudentClient;
import com.service.task.client.TeacherClient;
import com.service.task.model.TaskAssigned;
import com.service.task.model.TaskDetails;
import com.service.task.repository.TaskAssignedRepository;
import com.service.task.repository.TaskDetailsRepository;
import com.service.task.request.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImple implements TaskDetailsService, TaskAssignedService {

    @Autowired
    private TaskDetailsRepository taskDetailsRepository;

    @Autowired
    private TaskAssignedRepository taskAssignedRepository;

    @Autowired
    private TeacherClient teacherClient;

    @Autowired
    private StudentClient studentClient;

    @Override
    public TaskDetails createTask(TaskDetails taskDetails) {
        return taskDetailsRepository.save(taskDetails);
    }

    @Override
    public TaskDetails getTaskById(Long taskId) {
        return taskDetailsRepository.findById(taskId).orElseThrow(() -> new RuntimeException("cannot find task with id :"+taskId));
    }

    @Override
    public TaskDetails deleteTaskDetails(Long taskId) {
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

    @Override
    public List<TaskDetails> getTasksOfTeacher(Long empId) {
        return taskDetailsRepository.getTasksOfTeacher(empId);
    }

    @Override
    public List<Long> getTaskIdsOfTeacher(Long empId) {
        return taskDetailsRepository.getTaskIdsOfTeacher(empId);
    }

    @Override
    public List<Long> getStudentsByTaskId(Long taskId) {
        return taskAssignedRepository.getStudentsOfTask(taskId);
    }

    @Override
    public List<TaskDetails> getTasksOfStudent(Long rollNum) {
        List<Long> taskIds= taskAssignedRepository.getTasksOfStudent(rollNum);
        List<TaskDetails> tasks= new ArrayList<>();
        for(long id : taskIds){
            TaskDetails taskDetails=getTaskById(id);
            tasks.add(taskDetails);
        }
        return tasks;
    }

    @Override
    public List<Long> getTaskIdsOfStudent(Long rollNum) {
        return taskAssignedRepository.getTasksOfStudent(rollNum);
    }

    @Override
    public void deleteStudentFromTask(Long rollNumber, Long taskId) {
        taskAssignedRepository.deleteStudentFromTask(taskId,rollNumber);
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskAssignedRepository.deleteTaskById(taskId);
    }

    @Override
    public void deleteAllStudentTasks(Long rollNum) {
        taskAssignedRepository.deleteAllStudentTasks(rollNum);
    }

    @Override
    public void addStudentToTask(Long taskId, Long rollNum) {
        TaskAssigned task=new TaskAssigned(taskId,rollNum);
        taskAssignedRepository.save(task);
    }

    @Override
    public void addManyStudentsToTask(Long taskId, List<Long> rollNums) {
        for(long rollNum : rollNums){
            TaskAssigned task=new TaskAssigned(taskId,rollNum);
            taskAssignedRepository.save(task);
        }
    }
}
