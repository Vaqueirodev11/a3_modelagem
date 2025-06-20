package com.university.academic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"coordinator", "students", "disciplines", "professors"})
@EqualsAndHashCode(exclude = {"coordinator", "students", "disciplines", "professors"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Professor coordinator;

    @OneToMany(mappedBy = "course")
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Discipline> disciplines = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<Professor> professors = new HashSet<>();
}
