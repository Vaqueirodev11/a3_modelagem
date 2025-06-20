package com.university.academic.controller;

import com.university.academic.model.Professor;
import com.university.academic.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> getAllProfessors() {
        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.findById(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Professor>> getProfessorsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(professorService.findByCoursesId(courseId));
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@Valid @RequestBody Professor professor) {
        return new ResponseEntity<>(professorService.create(professor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable Long id, @Valid @RequestBody Professor professor) {
        return ResponseEntity.ok(professorService.update(id, professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{professorId}/courses/{courseId}")
    public ResponseEntity<Professor> assignToCourse(
            @PathVariable Long professorId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(professorService.assignToCourse(professorId, courseId));
    }

    @DeleteMapping("/{professorId}/courses/{courseId}")
    public ResponseEntity<Professor> removeFromCourse(
            @PathVariable Long professorId,
            @PathVariable Long courseId) {
        return ResponseEntity.ok(professorService.removeFromCourse(professorId, courseId));
    }
}
