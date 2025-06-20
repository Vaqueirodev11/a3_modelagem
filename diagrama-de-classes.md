# Diagrama de Classes - Sistema de Controle Acadêmico

## Visão Geral

Este documento descreve o diagrama de classes do Sistema de Controle Acadêmico da Universidade Master Educacional, detalhando as entidades, seus atributos e relacionamentos.

## Entidades Principais

### Curso (Course)

Representa um curso oferecido pela universidade.

**Atributos:**
- id: Long - Identificador único
- code: String - Código do curso
- name: String - Nome do curso
- coordinator: Professor - Professor coordenador do curso

**Relacionamentos:**
- Um curso pode ter vários alunos (1:N com Student)
- Um curso pode ter várias disciplinas (1:N com Discipline)
- Um curso pode ter vários professores (N:M com Professor)
- Um curso tem um coordenador (1:1 com Professor)

### Disciplina (Discipline)

Representa uma disciplina que faz parte de um curso.

**Atributos:**
- id: Long - Identificador único
- code: String - Código da disciplina
- name: String - Nome da disciplina
- credits: Integer - Carga horária
- description: String - Ementa
- bibliography: String - Bibliografia

**Relacionamentos:**
- Uma disciplina pertence a um curso (N:1 com Course)
- Uma disciplina pode ter várias turmas (1:N com ClassOffering)
- Uma disciplina pode ter várias disciplinas como pré-requisitos (N:M com Discipline)
- Uma disciplina pode ser pré-requisito para várias outras disciplinas (N:M com Discipline)

### Professor (Professor)

Representa um professor da universidade.

**Atributos:**
- id: Long - Identificador único
- name: String - Nome do professor
- email: String - Email do professor
- phone: String - Telefone do professor
- address: String - Endereço do professor
- qualification: Qualification - Titulação (graduação, especialização, mestrado, doutorado)

**Relacionamentos:**
- Um professor pode estar vinculado a vários cursos (N:M com Course)
- Um professor pode ser coordenador de um curso (1:1 com Course)
- Um professor pode ministrar várias turmas (1:N com ClassOffering)

### Aluno (Student)

Representa um aluno matriculado na universidade.

**Atributos:**
- id: Long - Identificador único
- registrationNumber: String - Número de matrícula
- name: String - Nome do aluno
- email: String - Email do aluno
- phone: String - Telefone do aluno
- address: String - Endereço do aluno
- status: StudentStatus - Status do aluno (matriculado, trancado, formado, evadido)

**Relacionamentos:**
- Um aluno está vinculado a um curso (N:1 com Course)
- Um aluno pode ter várias matrículas em turmas (1:N com Enrollment)

### Turma (ClassOffering)

Representa uma oferta de disciplina em um determinado período.

**Atributos:**
- id: Long - Identificador único
- year: Integer - Ano
- semester: Integer - Semestre
- schedule: String - Horários de realização

**Relacionamentos:**
- Uma turma é de uma disciplina específica (N:1 com Discipline)
- Uma turma é ministrada por um professor (N:1 com Professor)
- Uma turma pode ter vários alunos matriculados (1:N com Enrollment)

### Matrícula (Enrollment)

Representa a matrícula de um aluno em uma turma.

**Atributos:**
- id: Long - Identificador único
- firstPartialGrade: Double - Nota da primeira avaliação parcial
- secondPartialGrade: Double - Nota da segunda avaliação parcial
- finalExamGrade: Double - Nota da prova final
- attendancePercentage: Integer - Percentual de frequência
- approved: Boolean - Status de aprovação

**Relacionamentos:**
- Uma matrícula é de um aluno específico (N:1 com Student)
- Uma matrícula é em uma turma específica (N:1 com ClassOffering)

## Enumerações

### Qualification

Representa a titulação de um professor.

**Valores:**
- GRADUATION (Graduação)
- SPECIALIZATION (Especialização)
- MASTERS (Mestrado)
- DOCTORATE (Doutorado)

### StudentStatus

Representa o status de um aluno.

**Valores:**
- ENROLLED (Matriculado)
- ON_LEAVE (Trancado)
- GRADUATED (Formado)
- DROPPED_OUT (Evadido)

## Regras de Negócio Implementadas

### Cálculo de Aprovação

A classe `Enrollment` implementa métodos para calcular a média parcial, a média final e determinar se o aluno foi aprovado:

```java
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
```

## Diagrama de Classes UML

O diagrama de classes completo está disponível no arquivo `class-diagram.puml` na raiz do projeto. Ele foi criado usando a notação PlantUML e pode ser visualizado com ferramentas compatíveis com PlantUML.

### Principais Relacionamentos

```
Course "1" -- "0..*" Discipline
Course "1" -- "0..*" Student
Course "0..*" -- "0..*" Professor
Course "0..1" -- "0..1" Professor (coordinator)

Discipline "0..*" -- "0..*" Discipline (prerequisites)
Discipline "1" -- "0..*" ClassOffering

Professor "1" -- "0..*" ClassOffering

Student "1" -- "0..*" Enrollment

ClassOffering "1" -- "0..*" Enrollment
```

## Implementação no Código

As classes do modelo são implementadas como entidades JPA, com anotações para mapeamento objeto-relacional. Exemplo da classe `Course`:

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "coordinator_id")
    private Professor coordinator;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discipline> disciplines = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<Professor> professors = new ArrayList<>();
}
```

Este diagrama de classes serve como base para a implementação do sistema, garantindo que todas as entidades e relacionamentos necessários para atender aos requisitos funcionais estejam devidamente modelados.
