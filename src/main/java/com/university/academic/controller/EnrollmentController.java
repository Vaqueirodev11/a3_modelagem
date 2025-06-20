package com.university.academic.controller;

import com.university.academic.model.Enrollment;
import com.university.academic.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.findById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.findByStudentId(studentId));
    }

    @GetMapping("/class/{classOfferingId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByClassOffering(@PathVariable Long classOfferingId) {
        return ResponseEntity.ok(enrollmentService.findByClassOfferingId(classOfferingId));
    }

    @PostMapping("/student/{studentId}/class/{classOfferingId}")
    public ResponseEntity<Enrollment> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long classOfferingId) {
        return new ResponseEntity<>(enrollmentService.enrollStudent(studentId, classOfferingId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> unenrollStudent(@PathVariable Long id) {
        enrollmentService.unenrollStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/grades")
    public ResponseEntity<Enrollment> updateGrades(
            @PathVariable Long id,
            @RequestParam(required = false) Double firstPartialGrade,
            @RequestParam(required = false) Double secondPartialGrade,
            @RequestParam(required = false) Double finalExamGrade,
            @RequestParam(required = false) Integer attendancePercentage) {
        return ResponseEntity.ok(enrollmentService.updateGrades(id, firstPartialGrade, secondPartialGrade, finalExamGrade, attendancePercentage));
    }

    @GetMapping("/class/{classOfferingId}/diary")
    public ResponseEntity<List<Enrollment>> getClassDiary(@PathVariable Long classOfferingId) {
        return ResponseEntity.ok(enrollmentService.getClassDiary(classOfferingId));
    }

    @GetMapping("/student/{studentId}/academic-record")
    public ResponseEntity<List<Enrollment>> getStudentAcademicRecord(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getStudentAcademicRecord(studentId));
    }
}
