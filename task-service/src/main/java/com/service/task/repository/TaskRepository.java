package com.service.task.repository;

import com.service.task.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Transactional
    @Query(value = "update task set student_ids = :stuIds where task_id = :taskId",nativeQuery = true)
    public void updateStudents(@Param("taskId")Long taskId, @Param("stuIds") List<Long> stuIds);

    @Modifying
    @Transactional
    @Query(value = "select student_ids from task where task_id = :taskId", nativeQuery = true)
    public List<Long> findStudentsByTaskId (@Param("taskId")Long taskId);
}
