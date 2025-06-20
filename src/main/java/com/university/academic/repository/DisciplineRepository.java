package com.university.academic.repository;

import com.university.academic.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    Optional<Discipline> findByCode(String code);
    List<Discipline> findByCourseId(Long courseId);
    boolean existsByCode(String code);
}
