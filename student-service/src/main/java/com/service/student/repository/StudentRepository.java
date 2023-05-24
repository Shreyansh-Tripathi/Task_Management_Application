package com.service.student.repository;

import com.service.student.model.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @EntityGraph(attributePaths = {"coordinator"})
    public Long findByCoordinator (@Param("coordinator") Long coordinator);

    @Query(value = "select task_id from task_assigned where student_roll_num = :stuId", nativeQuery = true)
    public List<Long> findTasksByStudentId (@Param("stuId")Long stuId);

    @Query(value = "update student set coordinator = :empId where student_id = :stuId",nativeQuery = true)
    public void updateTeacher(@Param("stuId")Long stuId, @Param("empId") Long empId);
}
