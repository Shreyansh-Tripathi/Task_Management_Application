package com.service.teacher.repository;

import com.service.teacher.model.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

//    either use entity graph or native query
    @EntityGraph(attributePaths = {"student_ids"})
    public List<Long> findByEmployeeId (@Param("empId") Long empId);

    @Query(value = "select task_ids from teacher where employee_id = :empId", nativeQuery = true)
    public List<Long> findTasksByEmpId (@Param("empId")Long empId);

    @Query(value = "update teacher set student_ids = :stuIds where employee_id = :empId",nativeQuery = true)
    public void addNewStudent(@Param("empId")Long empId, @Param("stuIds")List<Long> stuIds);

    @Query(value = "update teacher set task_ids = :taskIds where employee_id = :empId",nativeQuery = true)
    public void addNewTask(@Param("empId")Long empId, @Param("taskIds") List<Long> taskIds);

    @Query(value = "update teacher set task_ids = :taskIds where employee_id = :empId",nativeQuery = true)
    public void deleteTask(@Param("empId")Long empId, @Param("taskIds") List<Long> taskIds);
}
