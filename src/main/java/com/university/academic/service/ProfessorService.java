package com.university.academic.service;

import com.university.academic.model.Course;
import com.university.academic.model.Professor;
import com.university.academic.repository.CourseRepository;
import com.university.academic.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, CourseRepository courseRepository) {
        this.professorRepository = professorRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Professor findById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Professor> findByCoursesId(Long courseId) {
        return professorRepository.findByCoursesId(courseId);
    }

    @Transactional
    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor update(Long id, Professor professorDetails) {
        Professor professor = findById(id);
        
        professor.setName(professorDetails.getName());
        professor.setAddress(professorDetails.getAddress());
        professor.setPhone(professorDetails.getPhone());
        professor.setMaxQualification(professorDetails.getMaxQualification());
        
        return professorRepository.save(professor);
    }

    @Transactional
    public void delete(Long id) {
        Professor professor = findById(id);
        professorRepository.delete(professor);
    }

    @Transactional
    public Professor assignToCourse(Long professorId, Long courseId) {
        Professor professor = findById(professorId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        
        professor.getCourses().add(course);
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor removeFromCourse(Long professorId, Long courseId) {
        Professor professor = findById(professorId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        
        professor.getCourses().remove(course);
        return professorRepository.save(professor);
    }
}
