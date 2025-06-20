package com.university.academic.service;

import com.university.academic.model.ClassOffering;
import com.university.academic.model.Enrollment;
import com.university.academic.model.Student;
import com.university.academic.repository.ClassOfferingRepository;
import com.university.academic.repository.EnrollmentRepository;
import com.university.academic.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final ClassOfferingRepository classOfferingRepository;

    @Autowired
    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            ClassOfferingRepository classOfferingRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.classOfferingRepository = classOfferingRepository;
    }

    @Transactional(readOnly = true)
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> findByClassOfferingId(Long classOfferingId) {
        return enrollmentRepository.findByClassOfferingId(classOfferingId);
    }

    @Transactional
    public Enrollment enrollStudent(Long studentId, Long classOfferingId) {
        if (enrollmentRepository.existsByStudentIdAndClassOfferingId(studentId, classOfferingId)) {
            throw new IllegalArgumentException("Student is already enrolled in this class");
        }
        
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        
        ClassOffering classOffering = classOfferingRepository.findById(classOfferingId)
                .orElseThrow(() -> new EntityNotFoundException("Class offering not found with id: " + classOfferingId));
        
        // Check if student is enrolled in the course of the discipline
        if (!student.getCourse().equals(classOffering.getDiscipline().getCourse())) {
            throw new IllegalArgumentException("Student is not enrolled in the course of this discipline");
        }
        
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setClassOffering(classOffering);
        
        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unenrollStudent(Long enrollmentId) {
        Enrollment enrollment = findById(enrollmentId);
        enrollmentRepository.delete(enrollment);
    }

    @Transactional
    public Enrollment updateGrades(Long enrollmentId, Double firstPartialGrade, Double secondPartialGrade, Double finalExamGrade, Integer attendancePercentage) {
        Enrollment enrollment = findById(enrollmentId);
        
        enrollment.setFirstPartialGrade(firstPartialGrade);
        enrollment.setSecondPartialGrade(secondPartialGrade);
        enrollment.setFinalExamGrade(finalExamGrade);
        enrollment.setAttendancePercentage(attendancePercentage);
        
        // The approval status will be automatically updated by the @PreUpdate method in the Enrollment entity
        
        return enrollmentRepository.save(enrollment);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> getClassDiary(Long classOfferingId) {
        return enrollmentRepository.findByClassOfferingId(classOfferingId);
    }

    @Transactional(readOnly = true)
    public List<Enrollment> getStudentAcademicRecord(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}
