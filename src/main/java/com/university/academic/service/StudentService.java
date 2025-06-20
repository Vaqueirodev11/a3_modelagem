package com.university.academic.service;

import com.university.academic.enums.StudentStatus;
import com.university.academic.model.Course;
import com.university.academic.model.Student;
import com.university.academic.repository.CourseRepository;
import com.university.academic.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Student findByRegistrationNumber(String registrationNumber) {
        return studentRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with registration number: " + registrationNumber));
    }

    @Transactional(readOnly = true)
    public List<Student> findByCourseId(Long courseId) {
        return studentRepository.findByCourseId(courseId);
    }

    @Transactional(readOnly = true)
    public List<Student> findByStatus(StudentStatus status) {
        return studentRepository.findByStatus(status);
    }

    @Transactional
    public Student create(Student student, Long courseId) {
        if (studentRepository.existsByRegistrationNumber(student.getRegistrationNumber())) {
            throw new IllegalArgumentException("Student with registration number " + student.getRegistrationNumber() + " already exists");
        }
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        
        student.setCourse(course);
        student.setStatus(StudentStatus.ENROLLED);
        return studentRepository.save(student);
    }

    @Transactional
    public Student update(Long id, Student studentDetails) {
        Student student = findById(id);
        
        if (!student.getRegistrationNumber().equals(studentDetails.getRegistrationNumber()) && 
            studentRepository.existsByRegistrationNumber(studentDetails.getRegistrationNumber())) {
            throw new IllegalArgumentException("Student with registration number " + studentDetails.getRegistrationNumber() + " already exists");
        }
        
        student.setRegistrationNumber(studentDetails.getRegistrationNumber());
        student.setName(studentDetails.getName());
        student.setAddress(studentDetails.getAddress());
        student.setPhone(studentDetails.getPhone());
        
        return studentRepository.save(student);
    }

    @Transactional
    public void delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }

    @Transactional
    public Student updateStatus(Long id, StudentStatus status) {
        Student student = findById(id);
        student.setStatus(status);
        return studentRepository.save(student);
    }

    @Transactional
    public Student changeCourse(Long studentId, Long courseId) {
        Student student = findById(studentId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
        
        student.setCourse(course);
        return studentRepository.save(student);
    }
}
