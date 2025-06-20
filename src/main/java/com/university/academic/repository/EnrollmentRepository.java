package com.university.academic.repository;

import com.university.academic.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByClassOfferingId(Long classOfferingId);
    Optional<Enrollment> findByStudentIdAndClassOfferingId(Long studentId, Long classOfferingId);
    boolean existsByStudentIdAndClassOfferingId(Long studentId, Long classOfferingId);
    List<Enrollment> findByClassOfferingDisciplineCourseId(Long courseId);
}
