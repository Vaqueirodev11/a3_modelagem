package com.university.academic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    private Integer workloadHours;

    @Column(columnDefinition = "TEXT")
    private String syllabus;

    @Column(columnDefinition = "TEXT")
    private String bibliography;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "discipline")
    private Set<ClassOffering> classOfferings = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "discipline_prerequisite",
        joinColumns = @JoinColumn(name = "discipline_id"),
        inverseJoinColumns = @JoinColumn(name = "prerequisite_id")
    )
    private Set<Discipline> prerequisites = new HashSet<>();

    @ManyToMany(mappedBy = "prerequisites")
    private Set<Discipline> isPrerequisiteFor = new HashSet<>();
}
