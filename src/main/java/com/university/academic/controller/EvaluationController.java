package com.university.academic.controller;

import com.university.academic.model.Enrollment;
import com.university.academic.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EvaluationController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEvaluations() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getStudentEvaluations(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.findByStudentId(studentId));
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEvaluation(@RequestBody Map<String, Object> evaluationData) {
        Long enrollmentId = Long.valueOf(evaluationData.get("enrollmentId").toString());
        Double firstPartialGrade = evaluationData.containsKey("firstPartialGrade") ? 
            Double.valueOf(evaluationData.get("firstPartialGrade").toString()) : null;
        Double secondPartialGrade = evaluationData.containsKey("secondPartialGrade") ? 
            Double.valueOf(evaluationData.get("secondPartialGrade").toString()) : null;
        Double finalExamGrade = evaluationData.containsKey("finalExamGrade") ? 
            Double.valueOf(evaluationData.get("finalExamGrade").toString()) : null;
        Integer attendancePercentage = evaluationData.containsKey("attendancePercentage") ? 
            Integer.valueOf(evaluationData.get("attendancePercentage").toString()) : null;
        
        return new ResponseEntity<>(
            enrollmentService.updateGrades(
                enrollmentId, 
                firstPartialGrade, 
                secondPartialGrade, 
                finalExamGrade, 
                attendancePercentage
            ), 
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEvaluation(
            @PathVariable Long id,
            @RequestBody Map<String, Object> evaluationData) {
        Double firstPartialGrade = evaluationData.containsKey("firstPartialGrade") ? 
            Double.valueOf(evaluationData.get("firstPartialGrade").toString()) : null;
        Double secondPartialGrade = evaluationData.containsKey("secondPartialGrade") ? 
            Double.valueOf(evaluationData.get("secondPartialGrade").toString()) : null;
        Double finalExamGrade = evaluationData.containsKey("finalExamGrade") ? 
            Double.valueOf(evaluationData.get("finalExamGrade").toString()) : null;
        Integer attendancePercentage = evaluationData.containsKey("attendancePercentage") ? 
            Integer.valueOf(evaluationData.get("attendancePercentage").toString()) : null;
        
        return ResponseEntity.ok(
            enrollmentService.updateGrades(
                id, 
                firstPartialGrade, 
                secondPartialGrade, 
                finalExamGrade, 
                attendancePercentage
            )
        );
    }
}
