package com.university.academic.config;

import com.university.academic.enums.Qualification;
import com.university.academic.enums.StudentStatus;
import com.university.academic.model.*;
import com.university.academic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataLoader {

    @Bean
    @Profile("dev")
    public CommandLineRunner loadData(
            CourseRepository courseRepository,
            ProfessorRepository professorRepository,
            DisciplineRepository disciplineRepository,
            StudentRepository studentRepository,
            ClassOfferingRepository classOfferingRepository,
            EnrollmentRepository enrollmentRepository) {
        
        return args -> {
            // Create professors
            Professor professor1 = new Professor();
            professor1.setName("João Silva");
            professor1.setAddress("Rua A, 123");
            professor1.setPhone("(11) 98765-4321");
            professor1.setMaxQualification(Qualification.DOCTORATE);
            professorRepository.save(professor1);

            Professor professor2 = new Professor();
            professor2.setName("Maria Oliveira");
            professor2.setAddress("Rua B, 456");
            professor2.setPhone("(11) 91234-5678");
            professor2.setMaxQualification(Qualification.MASTERS);
            professorRepository.save(professor2);

            // Create courses
            Course course1 = new Course();
            course1.setCode("CC001");
            course1.setDescription("Ciência da Computação");
            course1.setCoordinator(professor1);
            courseRepository.save(course1);

            Course course2 = new Course();
            course2.setCode("SI001");
            course2.setDescription("Sistemas de Informação");
            course2.setCoordinator(professor2);
            courseRepository.save(course2);

            // Link professors to courses
            professor1.getCourses().add(course1);
            professor1.getCourses().add(course2);
            professor2.getCourses().add(course2);
            professorRepository.save(professor1);
            professorRepository.save(professor2);

            // Create disciplines
            Discipline discipline1 = new Discipline();
            discipline1.setCode("ALG001");
            discipline1.setDescription("Algoritmos e Estruturas de Dados");
            discipline1.setWorkloadHours(80);
            discipline1.setSyllabus("Introdução a algoritmos, estruturas de dados básicas, complexidade...");
            discipline1.setBibliography("Cormen, T. H. et al. Introduction to Algorithms...");
            discipline1.setCourse(course1);
            disciplineRepository.save(discipline1);

            Discipline discipline2 = new Discipline();
            discipline2.setCode("BD001");
            discipline2.setDescription("Banco de Dados");
            discipline2.setWorkloadHours(60);
            discipline2.setSyllabus("Modelagem de dados, SQL, normalização...");
            discipline2.setBibliography("Elmasri, R.; Navathe, S. B. Sistemas de Banco de Dados...");
            discipline2.setCourse(course1);
            disciplineRepository.save(discipline2);

            Discipline discipline3 = new Discipline();
            discipline3.setCode("SI001");
            discipline3.setDescription("Sistemas de Informação");
            discipline3.setWorkloadHours(60);
            discipline3.setSyllabus("Conceitos de sistemas de informação, tipos de sistemas...");
            discipline3.setBibliography("Laudon, K. C.; Laudon, J. P. Sistemas de Informação Gerenciais...");
            discipline3.setCourse(course2);
            disciplineRepository.save(discipline3);

            // Set prerequisites
            discipline2.getPrerequisites().add(discipline1);
            disciplineRepository.save(discipline2);

            // Create students
            Student student1 = new Student();
            student1.setRegistrationNumber("2023001");
            student1.setName("Pedro Santos");
            student1.setAddress("Rua C, 789");
            student1.setPhone("(11) 97777-8888");
            student1.setCourse(course1);
            student1.setStatus(StudentStatus.ENROLLED);
            studentRepository.save(student1);

            Student student2 = new Student();
            student2.setRegistrationNumber("2023002");
            student2.setName("Ana Souza");
            student2.setAddress("Rua D, 101");
            student2.setPhone("(11) 96666-7777");
            student2.setCourse(course2);
            student2.setStatus(StudentStatus.ENROLLED);
            studentRepository.save(student2);

            // Create class offerings
            ClassOffering classOffering1 = new ClassOffering();
            classOffering1.setYear(2023);
            classOffering1.setSemester(1);
            classOffering1.setWeekDays("Segunda, Quarta");
            classOffering1.setSchedule("19:00 - 22:30");
            classOffering1.setDiscipline(discipline1);
            classOffering1.setProfessor(professor1);
            classOfferingRepository.save(classOffering1);

            ClassOffering classOffering2 = new ClassOffering();
            classOffering2.setYear(2023);
            classOffering2.setSemester(1);
            classOffering2.setWeekDays("Terça, Quinta");
            classOffering2.setSchedule("19:00 - 22:30");
            classOffering2.setDiscipline(discipline3);
            classOffering2.setProfessor(professor2);
            classOfferingRepository.save(classOffering2);

            // Enroll students
            Enrollment enrollment1 = new Enrollment();
            enrollment1.setStudent(student1);
            enrollment1.setClassOffering(classOffering1);
            enrollment1.setFirstPartialGrade(85.0);
            enrollment1.setSecondPartialGrade(90.0);
            enrollment1.setAttendancePercentage(95);
            enrollmentRepository.save(enrollment1);

            Enrollment enrollment2 = new Enrollment();
            enrollment2.setStudent(student2);
            enrollment2.setClassOffering(classOffering2);
            enrollment2.setFirstPartialGrade(75.0);
            enrollment2.setSecondPartialGrade(65.0);
            enrollment2.setAttendancePercentage(80);
            enrollmentRepository.save(enrollment2);

            System.out.println("Sample data loaded successfully!");
        };
    }
}
