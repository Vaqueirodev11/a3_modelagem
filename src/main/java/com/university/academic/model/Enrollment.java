package com.university.academic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_offering_id", nullable = false)
    private ClassOffering classOffering;

    private Double firstPartialGrade;

    private Double secondPartialGrade;

    private Double finalExamGrade;

    private Integer attendancePercentage;

    private Boolean approved;

    @Transient
    public Double calculatePartialAverage() {
        if (firstPartialGrade == null || secondPartialGrade == null) {
            return null;
        }
        return (firstPartialGrade + secondPartialGrade) / 2.0;
    }

    @Transient
    public Double calculateFinalAverage() {
        Double partialAverage = calculatePartialAverage();
        if (partialAverage == null) {
            return null;
        }

        if (partialAverage >= 70.0) {
            return partialAverage;
        } else if (partialAverage < 30.0) {
            return partialAverage;
        } else if (finalExamGrade != null) {
            return (partialAverage + finalExamGrade) / 2.0;
        }

        return null;
    }

    @Transient
    public Boolean isApproved() {
        if (attendancePercentage == null || attendancePercentage < 75) {
            return false;
        }

        Double partialAverage = calculatePartialAverage();
        if (partialAverage == null) {
            return null;
        }

        if (partialAverage >= 70.0) {
            return true;
        } else if (partialAverage < 30.0) {
            return false;
        } else {
            Double finalAverage = calculateFinalAverage();
            return finalAverage != null && finalAverage >= 50.0;
        }
    }

    @PrePersist
    @PreUpdate
    private void updateApprovalStatus() {
        Boolean approvalStatus = isApproved();
        if (approvalStatus != null) {
            this.approved = approvalStatus;
        }
    }
}
