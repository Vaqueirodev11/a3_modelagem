package com.university.academic.repository;

import com.university.academic.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByCoursesId(Long courseId);
    List<Professor> findByName(String name);
}
