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

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Course findByCode(String code) {
        return courseRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with code: " + code));
    }

    @Transactional
    public Course create(Course course) {
        if (courseRepository.existsByCode(course.getCode())) {
            throw new IllegalArgumentException("Course with code " + course.getCode() + " already exists");
        }
        return courseRepository.save(course);
    }

    @Transactional
    public Course update(Long id, Course courseDetails) {
        Course course = findById(id);
        
        if (!course.getCode().equals(courseDetails.getCode()) && courseRepository.existsByCode(courseDetails.getCode())) {
            throw new IllegalArgumentException("Course with code " + courseDetails.getCode() + " already exists");
        }
        
        course.setCode(courseDetails.getCode());
        course.setDescription(courseDetails.getDescription());
        
        return courseRepository.save(course);
    }

    @Transactional
    public void delete(Long id) {
        Course course = findById(id);
        courseRepository.delete(course);
    }

    @Transactional
    public Course assignCoordinator(Long courseId, Long professorId) {
        Course course = findById(courseId);
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with id: " + professorId));
        
        course.setCoordinator(professor);
        return courseRepository.save(course);
    }
}
