package com.service.task.repository;

import com.service.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "update task set student_ids = :studentIds where task_id = :taskId",nativeQuery = true)
    public void addNewStudent(@Param("taskId")Long taskId, @Param("stuIds") List<Long> stuIds);

    @Query(value = "select student_ids from task where task_id = :taskId", nativeQuery = true)
    public List<Long> findStudentsByTaskId (@Param("empId")Long taskId);
}
