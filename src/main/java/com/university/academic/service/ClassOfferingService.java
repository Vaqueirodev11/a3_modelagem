package com.university.academic.service;

import com.university.academic.model.ClassOffering;
import com.university.academic.model.Discipline;
import com.university.academic.model.Professor;
import com.university.academic.repository.ClassOfferingRepository;
import com.university.academic.repository.DisciplineRepository;
import com.university.academic.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassOfferingService {

    private final ClassOfferingRepository classOfferingRepository;
    private final DisciplineRepository disciplineRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public ClassOfferingService(
            ClassOfferingRepository classOfferingRepository,
            DisciplineRepository disciplineRepository,
            ProfessorRepository professorRepository) {
        this.classOfferingRepository = classOfferingRepository;
        this.disciplineRepository = disciplineRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional(readOnly = true)
    public List<ClassOffering> findAll() {
        return classOfferingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public ClassOffering findById(Long id) {
        return classOfferingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class offering not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<ClassOffering> findByDisciplineId(Long disciplineId) {
        return classOfferingRepository.findByDisciplineId(disciplineId);
    }

    @Transactional(readOnly = true)
    public List<ClassOffering> findByProfessorId(Long professorId) {
        return classOfferingRepository.findByProfessorId(professorId);
    }

    @Transactional(readOnly = true)
    public List<ClassOffering> findByYearAndSemester(Integer year, Integer semester) {
        return classOfferingRepository.findByYearAndSemester(year, semester);
    }

    @Transactional
    public ClassOffering create(ClassOffering classOffering, Long disciplineId) {
        Discipline discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new EntityNotFoundException("Discipline not found with id: " + disciplineId));
        
        classOffering.setDiscipline(discipline);
        return classOfferingRepository.save(classOffering);
    }

    @Transactional
    public ClassOffering update(Long id, ClassOffering classOfferingDetails) {
        ClassOffering classOffering = findById(id);
        
        classOffering.setYear(classOfferingDetails.getYear());
        classOffering.setSemester(classOfferingDetails.getSemester());
        classOffering.setWeekDays(classOfferingDetails.getWeekDays());
        classOffering.setSchedule(classOfferingDetails.getSchedule());
        
        return classOfferingRepository.save(classOffering);
    }

    @Transactional
    public void delete(Long id) {
        ClassOffering classOffering = findById(id);
        classOfferingRepository.delete(classOffering);
    }

    @Transactional
    public ClassOffering assignProfessor(Long classOfferingId, Long professorId) {
        ClassOffering classOffering = findById(classOfferingId);
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with id: " + professorId));
        
        // Check if professor is linked to the course of the discipline
        if (!professor.getCourses().contains(classOffering.getDiscipline().getCourse())) {
            throw new IllegalArgumentException("Professor is not linked to the course of this discipline");
        }
        
        classOffering.setProfessor(professor);
        return classOfferingRepository.save(classOffering);
    }

    @Transactional
    public ClassOffering removeProfessor(Long classOfferingId) {
        ClassOffering classOffering = findById(classOfferingId);
        classOffering.setProfessor(null);
        return classOfferingRepository.save(classOffering);
    }
}
