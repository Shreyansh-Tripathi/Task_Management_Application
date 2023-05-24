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

    @Query(value = "update student set coordinator = :empId where roll_number = :rollNum",nativeQuery = true)
    public void updateTeacher(@Param("rollNum")Long rollNum, @Param("empId") Long empId);

    @Query(value = "update student set coordinator = -1 where coordinator = :empId",nativeQuery = true)
    public void deleteTeachersWithId(@Param("empId") Long empId);

    @Query(value = "select roll_number from student where coordinator = :empId",nativeQuery = true)
    public List<Long> getStudentsOfTeacher(@Param("empId") Long empId);
}
