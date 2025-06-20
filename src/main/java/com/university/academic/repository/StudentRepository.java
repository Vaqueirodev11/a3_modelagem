package com.university.academic.repository;

import com.university.academic.enums.StudentStatus;
import com.university.academic.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByRegistrationNumber(String registrationNumber);
    List<Student> findByCourseId(Long courseId);
    List<Student> findByStatus(StudentStatus status);
    boolean existsByRegistrationNumber(String registrationNumber);
}
