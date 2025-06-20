package com.university.academic.repository;

import com.university.academic.model.ClassOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassOfferingRepository extends JpaRepository<ClassOffering, Long> {
    List<ClassOffering> findByDisciplineId(Long disciplineId);
    List<ClassOffering> findByProfessorId(Long professorId);
    List<ClassOffering> findByYearAndSemester(Integer year, Integer semester);
    List<ClassOffering> findByDisciplineIdAndYearAndSemester(Long disciplineId, Integer year, Integer semester);
}
