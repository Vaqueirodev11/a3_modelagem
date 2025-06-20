package com.university.academic.controller;

import com.university.academic.model.ClassOffering;
import com.university.academic.service.ClassOfferingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-offerings")
public class ClassOfferingController {

    private final ClassOfferingService classOfferingService;

    @Autowired
    public ClassOfferingController(ClassOfferingService classOfferingService) {
        this.classOfferingService = classOfferingService;
    }

    @GetMapping
    public ResponseEntity<List<ClassOffering>> getAllClassOfferings() {
        return ResponseEntity.ok(classOfferingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassOffering> getClassOfferingById(@PathVariable Long id) {
        return ResponseEntity.ok(classOfferingService.findById(id));
    }

    @GetMapping("/discipline/{disciplineId}")
    public ResponseEntity<List<ClassOffering>> getClassOfferingsByDiscipline(@PathVariable Long disciplineId) {
        return ResponseEntity.ok(classOfferingService.findByDisciplineId(disciplineId));
    }

    @GetMapping("/professor/{professorId}")
    public ResponseEntity<List<ClassOffering>> getClassOfferingsByProfessor(@PathVariable Long professorId) {
        return ResponseEntity.ok(classOfferingService.findByProfessorId(professorId));
    }

    @GetMapping("/semester")
    public ResponseEntity<List<ClassOffering>> getClassOfferingsByYearAndSemester(
            @RequestParam Integer year,
            @RequestParam Integer semester) {
        return ResponseEntity.ok(classOfferingService.findByYearAndSemester(year, semester));
    }

    @PostMapping("/discipline/{disciplineId}")
    public ResponseEntity<ClassOffering> createClassOffering(
            @Valid @RequestBody ClassOffering classOffering,
            @PathVariable Long disciplineId) {
        return new ResponseEntity<>(classOfferingService.create(classOffering, disciplineId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassOffering> updateClassOffering(
            @PathVariable Long id,
            @Valid @RequestBody ClassOffering classOffering) {
        return ResponseEntity.ok(classOfferingService.update(id, classOffering));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassOffering(@PathVariable Long id) {
        classOfferingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{classOfferingId}/professor/{professorId}")
    public ResponseEntity<ClassOffering> assignProfessor(
            @PathVariable Long classOfferingId,
            @PathVariable Long professorId) {
        return ResponseEntity.ok(classOfferingService.assignProfessor(classOfferingId, professorId));
    }

    @DeleteMapping("/{classOfferingId}/professor")
    public ResponseEntity<ClassOffering> removeProfessor(@PathVariable Long classOfferingId) {
        return ResponseEntity.ok(classOfferingService.removeProfessor(classOfferingId));
    }
}
