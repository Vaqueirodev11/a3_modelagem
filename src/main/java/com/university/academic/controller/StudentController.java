package com.university.academic.controller;

import com.university.academic.enums.StudentStatus;
import com.university.academic.model.Student;
import com.university.academic.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @GetMapping("/registration/{registrationNumber}")
    public ResponseEntity<Student> getStudentByRegistrationNumber(@PathVariable String registrationNumber) {
        return ResponseEntity.ok(studentService.findByRegistrationNumber(registrationNumber));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.findByCourseId(courseId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Student>> getStudentsByStatus(@PathVariable StudentStatus status) {
        return ResponseEntity.ok(studentService.findByStatus(status));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student,
            @PathVariable Long courseId) {
        return new ResponseEntity<>(studentService.create(student, courseId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        return ResponseEntity.ok(studentService.update(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Student> updateStudentStatus(
            @PathVariable Long id,
            @PathVariable StudentStatus status) {
        return ResponseEntity.ok(studentService.updateStatus(id, status));
    }

    @PutMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<Student> changeStudentCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.changeCourse(studentId, courseId));
    }
}
