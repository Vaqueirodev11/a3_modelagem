package com.university.academic.service;

import com.university.academic.model.Course;
import com.university.academic.model.Discipline;
import com.university.academic.repository.CourseRepository;
import com.university.academic.repository.DisciplineRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public DisciplineService(DisciplineRepository disciplineRepository, CourseRepository courseRepository) {
        this.disciplineRepository = disciplineRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Discipline findById(Long id) {
        return disciplineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discipline not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Discipline findByCode(String code) {
        return disciplineRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Discipline not found with code: " + code));
    }

    @Transactional(readOnly = true)
    public List<Discipline> findByCourseId(Long courseId) {
        return disciplineRepository.findByCourseId(courseId);
    }

    @Transactional
    public Discipline create(Discipline discipline, Long courseId) {
        if (disciplineRepository.existsByCode(discipline.getCode())) {
            throw new IllegalArgumentException("Discipline with code " + discipline.getCode() + " already exists");
        }
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        
        discipline.setCourse(course);
        return disciplineRepository.save(discipline);
    }

    @Transactional
    public Discipline update(Long id, Discipline disciplineDetails) {
        Discipline discipline = findById(id);
        
        if (!discipline.getCode().equals(disciplineDetails.getCode()) && 
            disciplineRepository.existsByCode(disciplineDetails.getCode())) {
            throw new IllegalArgumentException("Discipline with code " + disciplineDetails.getCode() + " already exists");
        }
        
        discipline.setCode(disciplineDetails.getCode());
        discipline.setDescription(disciplineDetails.getDescription());
        discipline.setWorkloadHours(disciplineDetails.getWorkloadHours());
        discipline.setSyllabus(disciplineDetails.getSyllabus());
        discipline.setBibliography(disciplineDetails.getBibliography());
        
        return disciplineRepository.save(discipline);
    }

    @Transactional
    public void delete(Long id) {
        Discipline discipline = findById(id);
        disciplineRepository.delete(discipline);
    }

    @Transactional
    public Discipline addPrerequisite(Long disciplineId, Long prerequisiteId) {
        Discipline discipline = findById(disciplineId);
        Discipline prerequisite = findById(prerequisiteId);
        
        if (discipline.equals(prerequisite)) {
            throw new IllegalArgumentException("A discipline cannot be a prerequisite of itself");
        }
        
        discipline.getPrerequisites().add(prerequisite);
        return disciplineRepository.save(discipline);
    }

    @Transactional
    public Discipline removePrerequisite(Long disciplineId, Long prerequisiteId) {
        Discipline discipline = findById(disciplineId);
        Discipline prerequisite = findById(prerequisiteId);
        
        discipline.getPrerequisites().remove(prerequisite);
        return disciplineRepository.save(discipline);
    }
}
