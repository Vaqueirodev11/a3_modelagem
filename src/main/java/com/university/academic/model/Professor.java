package com.university.academic.model;

import com.university.academic.enums.Qualification;
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
@ToString(exclude = {"courses", "classesTeaching", "coordinatedCourses"})
@EqualsAndHashCode(exclude = {"courses", "classesTeaching", "coordinatedCourses"})
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Qualification maxQualification;

    @ManyToMany
    @JoinTable(
        name = "professor_course",
        joinColumns = @JoinColumn(name = "professor_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "professor")
    private Set<ClassOffering> classesTeaching = new HashSet<>();

    @OneToMany(mappedBy = "coordinator")
    private Set<Course> coordinatedCourses = new HashSet<>();
}
