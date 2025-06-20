package com.university.academic.controller;

import com.university.academic.model.Discipline;
import com.university.academic.service.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public ResponseEntity<List<Discipline>> getAllDisciplines() {
        return ResponseEntity.ok(disciplineService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
        return ResponseEntity.ok(disciplineService.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Discipline> getDisciplineByCode(@PathVariable String code) {
        return ResponseEntity.ok(disciplineService.findByCode(code));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Discipline>> getDisciplinesByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(disciplineService.findByCourseId(courseId));
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Discipline> createDiscipline(
            @Valid @RequestBody Discipline discipline,
            @PathVariable Long courseId) {
        return new ResponseEntity<>(disciplineService.create(discipline, courseId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(
            @PathVariable Long id,
            @Valid @RequestBody Discipline discipline) {
        return ResponseEntity.ok(disciplineService.update(id, discipline));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{disciplineId}/prerequisites/{prerequisiteId}")
    public ResponseEntity<Discipline> addPrerequisite(
            @PathVariable Long disciplineId,
            @PathVariable Long prerequisiteId) {
        return ResponseEntity.ok(disciplineService.addPrerequisite(disciplineId, prerequisiteId));
    }

    @DeleteMapping("/{disciplineId}/prerequisites/{prerequisiteId}")
    public ResponseEntity<Discipline> removePrerequisite(
            @PathVariable Long disciplineId,
            @PathVariable Long prerequisiteId) {
        return ResponseEntity.ok(disciplineService.removePrerequisite(disciplineId, prerequisiteId));
    }
}
