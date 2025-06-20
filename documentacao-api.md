# Documentação da API REST - Sistema de Controle Acadêmico

## Visão Geral

Este documento descreve em detalhes a API REST do Sistema de Controle Acadêmico da Universidade Master Educacional. A API segue os princípios RESTful e utiliza o formato JSON para comunicação.

## Base URL

Todas as URLs da API são relativas à base URL:

```
http://localhost:8080/api
```

## Autenticação

Atualmente, a API não implementa autenticação para facilitar o desenvolvimento e testes. Em um ambiente de produção, seria necessário implementar um mecanismo de autenticação como JWT (JSON Web Tokens).

## Formato de Resposta

Todas as respostas são retornadas no formato JSON. Em caso de sucesso, o código de status HTTP será 200 OK para operações de leitura, 201 Created para operações de criação, e 204 No Content para operações de exclusão.

Em caso de erro, a API retorna um objeto JSON com informações sobre o erro:

```json
{
  "timestamp": "2025-06-20T16:00:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Descrição detalhada do erro",
  "path": "/api/endpoint"
}
```

## Endpoints

### Cursos

#### Listar todos os cursos

```
GET /courses
```

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação",
    "coordinator": {
      "id": 1,
      "name": "João Silva",
      "email": "joao.silva@universidade.edu",
      "phone": "11999998888",
      "address": "Rua A, 123",
      "qualification": "DOCTORATE"
    }
  },
  {
    "id": 2,
    "code": "SI001",
    "name": "Sistemas de Informação",
    "coordinator": null
  }
]
```

#### Obter um curso pelo ID

```
GET /courses/{id}
```

**Parâmetros:**

- `id`: ID do curso

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "code": "CC001",
  "name": "Ciência da Computação",
  "coordinator": {
    "id": 1,
    "name": "João Silva",
    "email": "joao.silva@universidade.edu",
    "phone": "11999998888",
    "address": "Rua A, 123",
    "qualification": "DOCTORATE"
  }
}
```

#### Obter um curso pelo código

```
GET /courses/code/{code}
```

**Parâmetros:**

- `code`: Código do curso

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "code": "CC001",
  "name": "Ciência da Computação",
  "coordinator": {
    "id": 1,
    "name": "João Silva",
    "email": "joao.silva@universidade.edu",
    "phone": "11999998888",
    "address": "Rua A, 123",
    "qualification": "DOCTORATE"
  }
}
```

#### Criar um novo curso

```
POST /courses
```

**Corpo da Requisição:**

```json
{
  "code": "ENG001",
  "name": "Engenharia de Software"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "code": "ENG001",
  "name": "Engenharia de Software",
  "coordinator": null
}
```

#### Atualizar um curso existente

```
PUT /courses/{id}
```

**Parâmetros:**

- `id`: ID do curso

**Corpo da Requisição:**

```json
{
  "code": "ENG001",
  "name": "Engenharia de Software e Sistemas"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "code": "ENG001",
  "name": "Engenharia de Software e Sistemas",
  "coordinator": null
}
```

#### Remover um curso

```
DELETE /courses/{id}
```

**Parâmetros:**

- `id`: ID do curso

**Resposta de Sucesso:**

Código de status 204 No Content (sem corpo de resposta)

#### Atribuir um coordenador ao curso

```
PUT /courses/{courseId}/coordinator/{professorId}
```

**Parâmetros:**

- `courseId`: ID do curso
- `professorId`: ID do professor

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "code": "ENG001",
  "name": "Engenharia de Software e Sistemas",
  "coordinator": {
    "id": 2,
    "name": "Maria Oliveira",
    "email": "maria.oliveira@universidade.edu",
    "phone": "11999997777",
    "address": "Rua B, 456",
    "qualification": "DOCTORATE"
  }
}
```

### Disciplinas

#### Listar todas as disciplinas

```
GET /disciplines
```

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "code": "CC101",
    "name": "Algoritmos e Estruturas de Dados",
    "credits": 60,
    "description": "Estudo de algoritmos e estruturas de dados fundamentais",
    "bibliography": "Cormen, T. H. et al. Algoritmos: Teoria e Prática",
    "course": {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    },
    "prerequisites": []
  },
  {
    "id": 2,
    "code": "CC102",
    "name": "Programação Orientada a Objetos",
    "credits": 60,
    "description": "Conceitos e práticas de programação orientada a objetos",
    "bibliography": "Deitel, H. M. Java: Como Programar",
    "course": {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    },
    "prerequisites": [
      {
        "id": 1,
        "code": "CC101",
        "name": "Algoritmos e Estruturas de Dados"
      }
    ]
  }
]
```

#### Obter uma disciplina pelo ID

```
GET /disciplines/{id}
```

**Parâmetros:**

- `id`: ID da disciplina

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "code": "CC101",
  "name": "Algoritmos e Estruturas de Dados",
  "credits": 60,
  "description": "Estudo de algoritmos e estruturas de dados fundamentais",
  "bibliography": "Cormen, T. H. et al. Algoritmos: Teoria e Prática",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  },
  "prerequisites": []
}
```

#### Obter uma disciplina pelo código

```
GET /disciplines/code/{code}
```

**Parâmetros:**

- `code`: Código da disciplina

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "code": "CC101",
  "name": "Algoritmos e Estruturas de Dados",
  "credits": 60,
  "description": "Estudo de algoritmos e estruturas de dados fundamentais",
  "bibliography": "Cormen, T. H. et al. Algoritmos: Teoria e Prática",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  },
  "prerequisites": []
}
```

#### Listar disciplinas de um curso

```
GET /disciplines/course/{courseId}
```

**Parâmetros:**

- `courseId`: ID do curso

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "code": "CC101",
    "name": "Algoritmos e Estruturas de Dados",
    "credits": 60,
    "description": "Estudo de algoritmos e estruturas de dados fundamentais",
    "bibliography": "Cormen, T. H. et al. Algoritmos: Teoria e Prática",
    "course": {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    },
    "prerequisites": []
  },
  {
    "id": 2,
    "code": "CC102",
    "name": "Programação Orientada a Objetos",
    "credits": 60,
    "description": "Conceitos e práticas de programação orientada a objetos",
    "bibliography": "Deitel, H. M. Java: Como Programar",
    "course": {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    },
    "prerequisites": [
      {
        "id": 1,
        "code": "CC101",
        "name": "Algoritmos e Estruturas de Dados"
      }
    ]
  }
]
```

#### Criar uma nova disciplina em um curso

```
POST /disciplines/course/{courseId}
```

**Parâmetros:**

- `courseId`: ID do curso

**Corpo da Requisição:**

```json
{
  "code": "CC103",
  "name": "Banco de Dados",
  "credits": 60,
  "description": "Conceitos e práticas de bancos de dados relacionais",
  "bibliography": "Elmasri, R.; Navathe, S. B. Sistemas de Banco de Dados"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "code": "CC103",
  "name": "Banco de Dados",
  "credits": 60,
  "description": "Conceitos e práticas de bancos de dados relacionais",
  "bibliography": "Elmasri, R.; Navathe, S. B. Sistemas de Banco de Dados",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  },
  "prerequisites": []
}
```

#### Atualizar uma disciplina existente

```
PUT /disciplines/{id}
```

**Parâmetros:**

- `id`: ID da disciplina

**Corpo da Requisição:**

```json
{
  "code": "CC103",
  "name": "Banco de Dados I",
  "credits": 60,
  "description": "Conceitos e práticas de bancos de dados relacionais",
  "bibliography": "Elmasri, R.; Navathe, S. B. Sistemas de Banco de Dados"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "code": "CC103",
  "name": "Banco de Dados I",
  "credits": 60,
  "description": "Conceitos e práticas de bancos de dados relacionais",
  "bibliography": "Elmasri, R.; Navathe, S. B. Sistemas de Banco de Dados",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  },
  "prerequisites": []
}
```

#### Remover uma disciplina

```
DELETE /disciplines/{id}
```

**Parâmetros:**

- `id`: ID da disciplina

**Resposta de Sucesso:**

Código de status 204 No Content (sem corpo de resposta)

#### Adicionar um pré-requisito

```
PUT /disciplines/{disciplineId}/prerequisites/{prerequisiteId}
```

**Parâmetros:**

- `disciplineId`: ID da disciplina
- `prerequisiteId`: ID da disciplina pré-requisito

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "code": "CC103",
  "name": "Banco de Dados I",
  "credits": 60,
  "description": "Conceitos e práticas de bancos de dados relacionais",
  "bibliography": "Elmasri, R.; Navathe, S. B. Sistemas de Banco de Dados",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  },
  "prerequisites": [
    {
      "id": 1,
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    }
  ]
}
```

#### Remover um pré-requisito

```
DELETE /disciplines/{disciplineId}/prerequisites/{prerequisiteId}
```

**Parâmetros:**

- `disciplineId`: ID da disciplina
- `prerequisiteId`: ID da disciplina pré-requisito

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "code": "CC103",
  "name": "Banco de Dados I",
  "credits": 60,
  "description": "Conceitos e práticas de bancos de dados relacionais",
  "bibliography": "Elmasri, R.; Navathe, S. B. Sistemas de Banco de Dados",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  },
  "prerequisites": []
}
```

### Professores

#### Listar todos os professores

```
GET /professors
```

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao.silva@universidade.edu",
    "phone": "11999998888",
    "address": "Rua A, 123",
    "qualification": "DOCTORATE",
    "courses": [
      {
        "id": 1,
        "code": "CC001",
        "name": "Ciência da Computação"
      }
    ]
  },
  {
    "id": 2,
    "name": "Maria Oliveira",
    "email": "maria.oliveira@universidade.edu",
    "phone": "11999997777",
    "address": "Rua B, 456",
    "qualification": "DOCTORATE",
    "courses": [
      {
        "id": 1,
        "code": "CC001",
        "name": "Ciência da Computação"
      },
      {
        "id": 2,
        "code": "SI001",
        "name": "Sistemas de Informação"
      }
    ]
  }
]
```

#### Obter um professor pelo ID

```
GET /professors/{id}
```

**Parâmetros:**

- `id`: ID do professor

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao.silva@universidade.edu",
  "phone": "11999998888",
  "address": "Rua A, 123",
  "qualification": "DOCTORATE",
  "courses": [
    {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    }
  ]
}
```

#### Listar professores de um curso

```
GET /professors/course/{courseId}
```

**Parâmetros:**

- `courseId`: ID do curso

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao.silva@universidade.edu",
    "phone": "11999998888",
    "address": "Rua A, 123",
    "qualification": "DOCTORATE"
  },
  {
    "id": 2,
    "name": "Maria Oliveira",
    "email": "maria.oliveira@universidade.edu",
    "phone": "11999997777",
    "address": "Rua B, 456",
    "qualification": "DOCTORATE"
  }
]
```

#### Criar um novo professor

```
POST /professors
```

**Corpo da Requisição:**

```json
{
  "name": "Carlos Santos",
  "email": "carlos.santos@universidade.edu",
  "phone": "11999996666",
  "address": "Rua C, 789",
  "qualification": "MASTERS"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "name": "Carlos Santos",
  "email": "carlos.santos@universidade.edu",
  "phone": "11999996666",
  "address": "Rua C, 789",
  "qualification": "MASTERS",
  "courses": []
}
```

#### Atualizar um professor existente

```
PUT /professors/{id}
```

**Parâmetros:**

- `id`: ID do professor

**Corpo da Requisição:**

```json
{
  "name": "Carlos Santos",
  "email": "carlos.santos@universidade.edu",
  "phone": "11999996666",
  "address": "Rua C, 789",
  "qualification": "DOCTORATE"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "name": "Carlos Santos",
  "email": "carlos.santos@universidade.edu",
  "phone": "11999996666",
  "address": "Rua C, 789",
  "qualification": "DOCTORATE",
  "courses": []
}
```

#### Remover um professor

```
DELETE /professors/{id}
```

**Parâmetros:**

- `id`: ID do professor

**Resposta de Sucesso:**

Código de status 204 No Content (sem corpo de resposta)

#### Atribuir um professor a um curso

```
PUT /professors/{professorId}/courses/{courseId}
```

**Parâmetros:**

- `professorId`: ID do professor
- `courseId`: ID do curso

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "name": "Carlos Santos",
  "email": "carlos.santos@universidade.edu",
  "phone": "11999996666",
  "address": "Rua C, 789",
  "qualification": "DOCTORATE",
  "courses": [
    {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    }
  ]
}
```

#### Remover um professor de um curso

```
DELETE /professors/{professorId}/courses/{courseId}
```

**Parâmetros:**

- `professorId`: ID do professor
- `courseId`: ID do curso

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "name": "Carlos Santos",
  "email": "carlos.santos@universidade.edu",
  "phone": "11999996666",
  "address": "Rua C, 789",
  "qualification": "DOCTORATE",
  "courses": []
}
```

### Alunos

#### Listar todos os alunos

```
GET /students
```

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "registrationNumber": "2025001",
    "name": "Ana Souza",
    "email": "ana.souza@aluno.universidade.edu",
    "phone": "11999995555",
    "address": "Rua D, 101",
    "status": "ENROLLED",
    "course": {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    }
  },
  {
    "id": 2,
    "registrationNumber": "2025002",
    "name": "Pedro Costa",
    "email": "pedro.costa@aluno.universidade.edu",
    "phone": "11999994444",
    "address": "Rua E, 202",
    "status": "ENROLLED",
    "course": {
      "id": 2,
      "code": "SI001",
      "name": "Sistemas de Informação"
    }
  }
]
```

#### Obter um aluno pelo ID

```
GET /students/{id}
```

**Parâmetros:**

- `id`: ID do aluno

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "registrationNumber": "2025001",
  "name": "Ana Souza",
  "email": "ana.souza@aluno.universidade.edu",
  "phone": "11999995555",
  "address": "Rua D, 101",
  "status": "ENROLLED",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  }
}
```

#### Obter um aluno pelo número de matrícula

```
GET /students/registration/{registrationNumber}
```

**Parâmetros:**

- `registrationNumber`: Número de matrícula do aluno

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "registrationNumber": "2025001",
  "name": "Ana Souza",
  "email": "ana.souza@aluno.universidade.edu",
  "phone": "11999995555",
  "address": "Rua D, 101",
  "status": "ENROLLED",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  }
}
```

#### Listar alunos de um curso

```
GET /students/course/{courseId}
```

**Parâmetros:**

- `courseId`: ID do curso

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "registrationNumber": "2025001",
    "name": "Ana Souza",
    "email": "ana.souza@aluno.universidade.edu",
    "phone": "11999995555",
    "address": "Rua D, 101",
    "status": "ENROLLED"
  }
]
```

#### Listar alunos por status

```
GET /students/status/{status}
```

**Parâmetros:**

- `status`: Status do aluno (ENROLLED, ON_LEAVE, GRADUATED, DROPPED_OUT)

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "registrationNumber": "2025001",
    "name": "Ana Souza",
    "email": "ana.souza@aluno.universidade.edu",
    "phone": "11999995555",
    "address": "Rua D, 101",
    "status": "ENROLLED",
    "course": {
      "id": 1,
      "code": "CC001",
      "name": "Ciência da Computação"
    }
  },
  {
    "id": 2,
    "registrationNumber": "2025002",
    "name": "Pedro Costa",
    "email": "pedro.costa@aluno.universidade.edu",
    "phone": "11999994444",
    "address": "Rua E, 202",
    "status": "ENROLLED",
    "course": {
      "id": 2,
      "code": "SI001",
      "name": "Sistemas de Informação"
    }
  }
]
```

#### Criar um novo aluno em um curso

```
POST /students/course/{courseId}
```

**Parâmetros:**

- `courseId`: ID do curso

**Corpo da Requisição:**

```json
{
  "name": "Lucas Ferreira",
  "email": "lucas.ferreira@aluno.universidade.edu",
  "phone": "11999993333",
  "address": "Rua F, 303"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "registrationNumber": "2025003",
  "name": "Lucas Ferreira",
  "email": "lucas.ferreira@aluno.universidade.edu",
  "phone": "11999993333",
  "address": "Rua F, 303",
  "status": "ENROLLED",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  }
}
```

#### Atualizar um aluno existente

```
PUT /students/{id}
```

**Parâmetros:**

- `id`: ID do aluno

**Corpo da Requisição:**

```json
{
  "name": "Lucas Ferreira",
  "email": "lucas.ferreira@aluno.universidade.edu",
  "phone": "11999992222",
  "address": "Rua F, 303"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "registrationNumber": "2025003",
  "name": "Lucas Ferreira",
  "email": "lucas.ferreira@aluno.universidade.edu",
  "phone": "11999992222",
  "address": "Rua F, 303",
  "status": "ENROLLED",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  }
}
```

#### Remover um aluno

```
DELETE /students/{id}
```

**Parâmetros:**

- `id`: ID do aluno

**Resposta de Sucesso:**

Código de status 204 No Content (sem corpo de resposta)

#### Atualizar o status de um aluno

```
PUT /students/{id}/status/{status}
```

**Parâmetros:**

- `id`: ID do aluno
- `status`: Novo status do aluno (ENROLLED, ON_LEAVE, GRADUATED, DROPPED_OUT)

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "registrationNumber": "2025003",
  "name": "Lucas Ferreira",
  "email": "lucas.ferreira@aluno.universidade.edu",
  "phone": "11999992222",
  "address": "Rua F, 303",
  "status": "ON_LEAVE",
  "course": {
    "id": 1,
    "code": "CC001",
    "name": "Ciência da Computação"
  }
}
```

#### Transferir um aluno para outro curso

```
PUT /students/{studentId}/course/{courseId}
```

**Parâmetros:**

- `studentId`: ID do aluno
- `courseId`: ID do novo curso

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "registrationNumber": "2025003",
  "name": "Lucas Ferreira",
  "email": "lucas.ferreira@aluno.universidade.edu",
  "phone": "11999992222",
  "address": "Rua F, 303",
  "status": "ON_LEAVE",
  "course": {
    "id": 2,
    "code": "SI001",
    "name": "Sistemas de Informação"
  }
}
```

### Turmas

#### Listar todas as turmas

```
GET /class-offerings
```

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "year": 2025,
    "semester": 1,
    "schedule": "Segunda e Quarta, 14:00-16:00",
    "discipline": {
      "id": 1,
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    },
    "professor": {
      "id": 1,
      "name": "João Silva"
    }
  },
  {
    "id": 2,
    "year": 2025,
    "semester": 1,
    "schedule": "Terça e Quinta, 10:00-12:00",
    "discipline": {
      "id": 2,
      "code": "CC102",
      "name": "Programação Orientada a Objetos"
    },
    "professor": {
      "id": 2,
      "name": "Maria Oliveira"
    }
  }
]
```

#### Obter uma turma pelo ID

```
GET /class-offerings/{id}
```

**Parâmetros:**

- `id`: ID da turma

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "year": 2025,
  "semester": 1,
  "schedule": "Segunda e Quarta, 14:00-16:00",
  "discipline": {
    "id": 1,
    "code": "CC101",
    "name": "Algoritmos e Estruturas de Dados"
  },
  "professor": {
    "id": 1,
    "name": "João Silva"
  }
}
```

#### Listar turmas de uma disciplina

```
GET /class-offerings/discipline/{disciplineId}
```

**Parâmetros:**

- `disciplineId`: ID da disciplina

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "year": 2025,
    "semester": 1,
    "schedule": "Segunda e Quarta, 14:00-16:00",
    "professor": {
      "id": 1,
      "name": "João Silva"
    }
  }
]
```

#### Listar turmas de um professor

```
GET /class-offerings/professor/{professorId}
```

**Parâmetros:**

- `professorId`: ID do professor

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "year": 2025,
    "semester": 1,
    "schedule": "Segunda e Quarta, 14:00-16:00",
    "discipline": {
      "id": 1,
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    }
  }
]
```

#### Listar turmas por ano e semestre

```
GET /class-offerings/semester?year={year}&semester={semester}
```

**Parâmetros:**

- `year`: Ano
- `semester`: Semestre

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "year": 2025,
    "semester":
