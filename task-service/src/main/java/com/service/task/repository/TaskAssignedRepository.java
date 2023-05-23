package com.service.task.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskAssignedRepository {

    @Query(value = "select student_roll_num from task_assigned where task_id = :taskId",nativeQuery = true)
    public List<Long> getStudentsOfTask(@Param("taskId") Long taskId);

    @Query(value = "select task_id from task_assigned where student_roll_num = :rollNum",nativeQuery = true)
    public List<Long> getTasksOfStudent(@Param("rollNum") Long rollNum);

}
