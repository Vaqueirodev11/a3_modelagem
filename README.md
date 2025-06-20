# Sistema de Controle Acadêmico da Universidade Master Educacional

## Visão Geral

Este sistema foi desenvolvido para gerenciar o controle acadêmico da Universidade Master Educacional, permitindo o cadastro e gerenciamento de cursos, disciplinas, professores, alunos, turmas, matrículas e avaliações.

## Estrutura do Projeto

O projeto está dividido em duas partes principais:

1. **Back-end**: Desenvolvido com Java Spring Boot
2. **Front-end**: Desenvolvido com React, TypeScript e Tailwind CSS

### Tecnologias Utilizadas

#### Back-end
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (banco de dados em memória)
- Maven

#### Front-end
- React 18
- TypeScript
- Vite
- Tailwind CSS
- React Router

## Documentação do Back-end

### Estrutura de Pacotes

- **com.university.academic**: Pacote raiz da aplicação
  - **.config**: Configurações da aplicação (CORS, carregamento de dados)
  - **.controller**: Controladores REST para as APIs
  - **.dto**: Objetos de transferência de dados
  - **.enums**: Enumerações utilizadas no sistema
  - **.exception**: Tratamento de exceções
  - **.model**: Entidades JPA
  - **.repository**: Interfaces de repositório para acesso aos dados
  - **.service**: Serviços de negócio

### Modelos de Dados

#### Course (Curso)
- **id**: Identificador único
- **code**: Código do curso
- **name**: Nome do curso
- **coordinator**: Professor coordenador do curso
- **disciplines**: Lista de disciplinas do curso

#### Discipline (Disciplina)
- **id**: Identificador único
- **code**: Código da disciplina
- **name**: Nome da disciplina
- **credits**: Carga horária
- **description**: Ementa
- **bibliography**: Bibliografia
- **course**: Curso ao qual a disciplina pertence
- **prerequisites**: Lista de disciplinas pré-requisitos

#### Professor (Professor)
- **id**: Identificador único
- **name**: Nome do professor
- **email**: Email do professor
- **phone**: Telefone do professor
- **address**: Endereço do professor
- **qualification**: Titulação (graduação, especialização, mestrado, doutorado)
- **courses**: Cursos aos quais o professor está vinculado

#### Student (Aluno)
- **id**: Identificador único
- **registrationNumber**: Número de matrícula
- **name**: Nome do aluno
- **email**: Email do aluno
- **phone**: Telefone do aluno
- **address**: Endereço do aluno
- **course**: Curso ao qual o aluno está vinculado
- **status**: Status do aluno (matriculado, trancado, formado, evadido)

#### ClassOffering (Turma)
- **id**: Identificador único
- **discipline**: Disciplina da turma
- **professor**: Professor responsável
- **year**: Ano
- **semester**: Semestre
- **schedule**: Horários de realização
- **enrollments**: Lista de matrículas

#### Enrollment (Matrícula)
- **id**: Identificador único
- **student**: Aluno
- **classOffering**: Turma
- **firstPartialGrade**: Nota da primeira avaliação parcial
- **secondPartialGrade**: Nota da segunda avaliação parcial
- **finalExamGrade**: Nota da prova final
- **attendancePercentage**: Percentual de frequência
- **approved**: Status de aprovação

### APIs REST

#### Cursos
- `GET /api/courses`: Lista todos os cursos
- `GET /api/courses/{id}`: Obtém um curso pelo ID
- `GET /api/courses/code/{code}`: Obtém um curso pelo código
- `POST /api/courses`: Cria um novo curso
- `PUT /api/courses/{id}`: Atualiza um curso existente
- `DELETE /api/courses/{id}`: Remove um curso
- `PUT /api/courses/{courseId}/coordinator/{professorId}`: Atribui um coordenador ao curso

#### Disciplinas
- `GET /api/disciplines`: Lista todas as disciplinas
- `GET /api/disciplines/{id}`: Obtém uma disciplina pelo ID
- `GET /api/disciplines/code/{code}`: Obtém uma disciplina pelo código
- `GET /api/disciplines/course/{courseId}`: Lista disciplinas de um curso
- `POST /api/disciplines/course/{courseId}`: Cria uma nova disciplina em um curso
- `PUT /api/disciplines/{id}`: Atualiza uma disciplina existente
- `DELETE /api/disciplines/{id}`: Remove uma disciplina
- `PUT /api/disciplines/{disciplineId}/prerequisites/{prerequisiteId}`: Adiciona um pré-requisito
- `DELETE /api/disciplines/{disciplineId}/prerequisites/{prerequisiteId}`: Remove um pré-requisito

#### Professores
- `GET /api/professors`: Lista todos os professores
- `GET /api/professors/{id}`: Obtém um professor pelo ID
- `GET /api/professors/course/{courseId}`: Lista professores de um curso
- `POST /api/professors`: Cria um novo professor
- `PUT /api/professors/{id}`: Atualiza um professor existente
- `DELETE /api/professors/{id}`: Remove um professor
- `PUT /api/professors/{professorId}/courses/{courseId}`: Atribui um professor a um curso
- `DELETE /api/professors/{professorId}/courses/{courseId}`: Remove um professor de um curso

#### Alunos
- `GET /api/students`: Lista todos os alunos
- `GET /api/students/{id}`: Obtém um aluno pelo ID
- `GET /api/students/registration/{registrationNumber}`: Obtém um aluno pelo número de matrícula
- `GET /api/students/course/{courseId}`: Lista alunos de um curso
- `GET /api/students/status/{status}`: Lista alunos por status
- `POST /api/students/course/{courseId}`: Cria um novo aluno em um curso
- `PUT /api/students/{id}`: Atualiza um aluno existente
- `DELETE /api/students/{id}`: Remove um aluno
- `PUT /api/students/{id}/status/{status}`: Atualiza o status de um aluno
- `PUT /api/students/{studentId}/course/{courseId}`: Transfere um aluno para outro curso

#### Turmas
- `GET /api/class-offerings`: Lista todas as turmas
- `GET /api/class-offerings/{id}`: Obtém uma turma pelo ID
- `GET /api/class-offerings/discipline/{disciplineId}`: Lista turmas de uma disciplina
- `GET /api/class-offerings/professor/{professorId}`: Lista turmas de um professor
- `GET /api/class-offerings/semester`: Lista turmas por ano e semestre
- `POST /api/class-offerings/discipline/{disciplineId}`: Cria uma nova turma para uma disciplina
- `PUT /api/class-offerings/{id}`: Atualiza uma turma existente
- `DELETE /api/class-offerings/{id}`: Remove uma turma
- `PUT /api/class-offerings/{classOfferingId}/professor/{professorId}`: Atribui um professor a uma turma
- `DELETE /api/class-offerings/{classOfferingId}/professor`: Remove um professor de uma turma

#### Matrículas
- `GET /api/enrollments`: Lista todas as matrículas
- `GET /api/enrollments/{id}`: Obtém uma matrícula pelo ID
- `GET /api/enrollments/student/{studentId}`: Lista matrículas de um aluno
- `GET /api/enrollments/class/{classOfferingId}`: Lista matrículas de uma turma
- `POST /api/enrollments/student/{studentId}/class/{classOfferingId}`: Matricula um aluno em uma turma
- `DELETE /api/enrollments/{id}`: Cancela a matrícula de um aluno
- `PUT /api/enrollments/{id}/grades`: Atualiza as notas de uma matrícula
- `GET /api/enrollments/class/{classOfferingId}/diary`: Obtém o diário de classe de uma turma
- `GET /api/enrollments/student/{studentId}/academic-record`: Obtém o histórico acadêmico de um aluno

#### Avaliações
- `GET /api/evaluations`: Lista todas as avaliações
- `GET /api/evaluations/student/{studentId}`: Lista avaliações de um aluno
- `POST /api/evaluations`: Cria uma nova avaliação
- `PUT /api/evaluations/{id}`: Atualiza uma avaliação existente

#### Relatórios
- `GET /api/reports/attendance/{classOfferingId}`: Relatório de frequência de uma turma
- `GET /api/reports/transcript/{studentId}`: Histórico escolar de um aluno

### Configuração do Banco de Dados

O sistema utiliza o banco de dados H2 em memória para desenvolvimento e testes. A configuração está no arquivo `application.properties`:

```properties
# Configuração do Banco de Dados H2
spring.datasource.url=jdbc:h2:mem:academicdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Habilitar Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuração JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuração do Servidor
server.port=8080

# Perfil Ativo
spring.profiles.active=dev
```

## Documentação do Front-end

### Estrutura de Diretórios

- **src**: Código-fonte da aplicação
  - **components**: Componentes React
    - **Common**: Componentes comuns (Modal, Table, etc.)
    - **Courses**: Componentes relacionados a cursos
    - **Dashboard**: Componentes do painel principal
    - **Layout**: Componentes de layout (Header, Sidebar)
    - **Students**: Componentes relacionados a alunos
  - **services**: Serviços para comunicação com a API
  - **types**: Definições de tipos TypeScript
  - **App.tsx**: Componente principal da aplicação
  - **main.tsx**: Ponto de entrada da aplicação

### Serviço de API

O serviço de API (`api.ts`) é responsável por fazer a comunicação com o back-end. Ele define métodos para cada operação disponível na API REST:

```typescript
const API_BASE_URL = 'http://localhost:8080/api';

class ApiService {
  private async request<T>(endpoint: string, options?: RequestInit): Promise<T> {
    const response = await fetch(`${API_BASE_URL}${endpoint}`, {
      headers: {
        'Content-Type': 'application/json',
        ...options?.headers,
      },
      ...options,
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    return response.json();
  }

  // Métodos para cursos
  async getCourses() { ... }
  async createCourse(course: any) { ... }
  async updateCourse(id: number, course: any) { ... }
  async deleteCourse(id: number) { ... }

  // Métodos para disciplinas
  async getDisciplines() { ... }
  async createDiscipline(discipline: any) { ... }
  async updateDiscipline(id: number, discipline: any) { ... }
  async deleteDiscipline(id: number) { ... }

  // Métodos para alunos
  async getStudents() { ... }
  async createStudent(student: any) { ... }
  async updateStudent(id: number, student: any) { ... }
  async deleteStudent(id: number) { ... }

  // Métodos para professores
  async getProfessors() { ... }
  async createProfessor(professor: any) { ... }
  async updateProfessor(id: number, professor: any) { ... }
  async deleteProfessor(id: number) { ... }

  // Métodos para turmas
  async getClassOfferings() { ... }
  async createClassOffering(classOffering: any) { ... }
  async updateClassOffering(id: number, classOffering: any) { ... }
  async deleteClassOffering(id: number) { ... }

  // Métodos para matrículas
  async getEnrollments() { ... }
  async createEnrollment(enrollment: any) { ... }
  async deleteEnrollment(id: number) { ... }

  // Métodos para avaliações
  async getEvaluations() { ... }
  async createEvaluation(evaluation: any) { ... }
  async updateEvaluation(id: number, evaluation: any) { ... }
  async getStudentEvaluations(studentId: number) { ... }

  // Métodos para relatórios
  async getClassAttendanceReport(classId: number) { ... }
  async getStudentTranscript(studentId: number) { ... }
}

export const apiService = new ApiService();
```

### Componentes Principais

#### CourseList
Lista todos os cursos cadastrados, permitindo adicionar, editar e excluir cursos.

#### CourseForm
Formulário para adicionar ou editar um curso.

#### StudentList
Lista todos os alunos cadastrados, permitindo adicionar, editar e excluir alunos.

#### StudentForm
Formulário para adicionar ou editar um aluno.

#### Dashboard
Painel principal que exibe informações resumidas sobre cursos, disciplinas, professores e alunos.

#### Header
Cabeçalho da aplicação com o título e opções de navegação.

#### Sidebar
Barra lateral com links para as diferentes seções da aplicação.

## Como Executar o Projeto

### Requisitos

- Java 17 ou superior
- Node.js 16 ou superior
- npm ou yarn

### Passos para Execução

1. **Clone o repositório**:
   ```
   git clone <url-do-repositorio>
   cd a3_modelagem
   ```

2. **Execute o back-end**:
   ```
   ./mvnw spring-boot:run
   ```
   O servidor será iniciado na porta 8080.

3. **Execute o front-end**:
   ```
   cd front-end
   npm install
   npm run dev
   ```
   A aplicação será iniciada na porta 5173.

4. **Acesse a aplicação**:
   Abra o navegador e acesse `http://localhost:5173`

## Regras de Negócio

### Aprovação de Alunos

- Para ser aprovado, o aluno deve ter frequência mínima de 75%.
- Além disso, para aprovação sem prova final, a média das notas parciais deve ser maior ou igual a 70.
- Para reprovação direta, esta média deve ser menor que 30.
- Médias entre 30 (inclusive) e 70 (exclusive) colocam o aluno em prova final.
- Se a média da prova final com a média anterior for menor que 50, o aluno está reprovado, caso contrário, aprovado.

### Matrícula de Alunos

- Um aluno só pode ser matriculado em uma disciplina se tiver sido aprovado em todos os pré-requisitos.
- Um aluno não pode ser matriculado em turmas com horários conflitantes.

### Alocação de Professores

- Um professor só pode ser alocado a turmas de disciplinas de cursos aos quais ele está vinculado.
- Um professor não pode ser alocado a turmas com horários conflitantes.

## Diagrama de Classes

O diagrama de classes do sistema está disponível no arquivo `class-diagram.puml` na raiz do projeto. Ele representa a estrutura das entidades e seus relacionamentos conforme o estudo de caso.
