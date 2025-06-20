package com.university.academic.controller;

import com.university.academic.model.Enrollment;
import com.university.academic.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public ReportController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/attendance/{classOfferingId}")
    public ResponseEntity<List<Enrollment>> getClassAttendanceReport(@PathVariable Long classOfferingId) {
        return ResponseEntity.ok(enrollmentService.getClassDiary(classOfferingId));
    }

    @GetMapping("/transcript/{studentId}")
    public ResponseEntity<List<Enrollment>> getStudentTranscript(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getStudentAcademicRecord(studentId));
    }
}
