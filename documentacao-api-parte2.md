# Documentação da API REST - Sistema de Controle Acadêmico (Continuação)

## Turmas (Continuação)

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

#### Criar uma nova turma para uma disciplina

```
POST /class-offerings/discipline/{disciplineId}
```

**Parâmetros:**

- `disciplineId`: ID da disciplina

**Corpo da Requisição:**

```json
{
  "year": 2025,
  "semester": 2,
  "schedule": "Segunda e Quarta, 16:00-18:00"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "year": 2025,
  "semester": 2,
  "schedule": "Segunda e Quarta, 16:00-18:00",
  "discipline": {
    "id": 1,
    "code": "CC101",
    "name": "Algoritmos e Estruturas de Dados"
  },
  "professor": null
}
```

#### Atualizar uma turma existente

```
PUT /class-offerings/{id}
```

**Parâmetros:**

- `id`: ID da turma

**Corpo da Requisição:**

```json
{
  "year": 2025,
  "semester": 2,
  "schedule": "Segunda e Quarta, 18:00-20:00"
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "year": 2025,
  "semester": 2,
  "schedule": "Segunda e Quarta, 18:00-20:00",
  "discipline": {
    "id": 1,
    "code": "CC101",
    "name": "Algoritmos e Estruturas de Dados"
  },
  "professor": null
}
```

#### Remover uma turma

```
DELETE /class-offerings/{id}
```

**Parâmetros:**

- `id`: ID da turma

**Resposta de Sucesso:**

Código de status 204 No Content (sem corpo de resposta)

#### Atribuir um professor a uma turma

```
PUT /class-offerings/{classOfferingId}/professor/{professorId}
```

**Parâmetros:**

- `classOfferingId`: ID da turma
- `professorId`: ID do professor

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "year": 2025,
  "semester": 2,
  "schedule": "Segunda e Quarta, 18:00-20:00",
  "discipline": {
    "id": 1,
    "code": "CC101",
    "name": "Algoritmos e Estruturas de Dados"
  },
  "professor": {
    "id": 3,
    "name": "Carlos Santos"
  }
}
```

#### Remover um professor de uma turma

```
DELETE /class-offerings/{classOfferingId}/professor
```

**Parâmetros:**

- `classOfferingId`: ID da turma

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "year": 2025,
  "semester": 2,
  "schedule": "Segunda e Quarta, 18:00-20:00",
  "discipline": {
    "id": 1,
    "code": "CC101",
    "name": "Algoritmos e Estruturas de Dados"
  },
  "professor": null
}
```

### Matrículas

#### Listar todas as matrículas

```
GET /enrollments
```

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "student": {
      "id": 1,
      "registrationNumber": "2025001",
      "name": "Ana Souza"
    },
    "classOffering": {
      "id": 1,
      "discipline": {
        "code": "CC101",
        "name": "Algoritmos e Estruturas de Dados"
      },
      "year": 2025,
      "semester": 1
    },
    "firstPartialGrade": 85.0,
    "secondPartialGrade": 90.0,
    "finalExamGrade": null,
    "attendancePercentage": 95,
    "approved": true
  },
  {
    "id": 2,
    "student": {
      "id": 2,
      "registrationNumber": "2025002",
      "name": "Pedro Costa"
    },
    "classOffering": {
      "id": 2,
      "discipline": {
        "code": "CC102",
        "name": "Programação Orientada a Objetos"
      },
      "year": 2025,
      "semester": 1
    },
    "firstPartialGrade": 65.0,
    "secondPartialGrade": 70.0,
    "finalExamGrade": null,
    "attendancePercentage": 85,
    "approved": null
  }
]
```

#### Obter uma matrícula pelo ID

```
GET /enrollments/{id}
```

**Parâmetros:**

- `id`: ID da matrícula

**Resposta de Sucesso:**

```json
{
  "id": 1,
  "student": {
    "id": 1,
    "registrationNumber": "2025001",
    "name": "Ana Souza"
  },
  "classOffering": {
    "id": 1,
    "discipline": {
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    },
    "year": 2025,
    "semester": 1
  },
  "firstPartialGrade": 85.0,
  "secondPartialGrade": 90.0,
  "finalExamGrade": null,
  "attendancePercentage": 95,
  "approved": true
}
```

#### Listar matrículas de um aluno

```
GET /enrollments/student/{studentId}
```

**Parâmetros:**

- `studentId`: ID do aluno

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "classOffering": {
      "id": 1,
      "discipline": {
        "code": "CC101",
        "name": "Algoritmos e Estruturas de Dados"
      },
      "year": 2025,
      "semester": 1
    },
    "firstPartialGrade": 85.0,
    "secondPartialGrade": 90.0,
    "finalExamGrade": null,
    "attendancePercentage": 95,
    "approved": true
  }
]
```

#### Listar matrículas de uma turma

```
GET /enrollments/class/{classOfferingId}
```

**Parâmetros:**

- `classOfferingId`: ID da turma

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "student": {
      "id": 1,
      "registrationNumber": "2025001",
      "name": "Ana Souza"
    },
    "firstPartialGrade": 85.0,
    "secondPartialGrade": 90.0,
    "finalExamGrade": null,
    "attendancePercentage": 95,
    "approved": true
  }
]
```

#### Matricular um aluno em uma turma

```
POST /enrollments/student/{studentId}/class/{classOfferingId}
```

**Parâmetros:**

- `studentId`: ID do aluno
- `classOfferingId`: ID da turma

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "student": {
    "id": 3,
    "registrationNumber": "2025003",
    "name": "Lucas Ferreira"
  },
  "classOffering": {
    "id": 1,
    "discipline": {
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    },
    "year": 2025,
    "semester": 1
  },
  "firstPartialGrade": null,
  "secondPartialGrade": null,
  "finalExamGrade": null,
  "attendancePercentage": null,
  "approved": null
}
```

#### Cancelar a matrícula de um aluno

```
DELETE /enrollments/{id}
```

**Parâmetros:**

- `id`: ID da matrícula

**Resposta de Sucesso:**

Código de status 204 No Content (sem corpo de resposta)

#### Atualizar as notas de uma matrícula

```
PUT /enrollments/{id}/grades
```

**Parâmetros:**

- `id`: ID da matrícula

**Corpo da Requisição:**

```json
{
  "firstPartialGrade": 75.0,
  "secondPartialGrade": 80.0,
  "finalExamGrade": null,
  "attendancePercentage": 90
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "student": {
    "id": 3,
    "registrationNumber": "2025003",
    "name": "Lucas Ferreira"
  },
  "classOffering": {
    "id": 1,
    "discipline": {
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    },
    "year": 2025,
    "semester": 1
  },
  "firstPartialGrade": 75.0,
  "secondPartialGrade": 80.0,
  "finalExamGrade": null,
  "attendancePercentage": 90,
  "approved": true
}
```

#### Obter o diário de classe de uma turma

```
GET /enrollments/class/{classOfferingId}/diary
```

**Parâmetros:**

- `classOfferingId`: ID da turma

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "student": {
      "id": 1,
      "registrationNumber": "2025001",
      "name": "Ana Souza"
    },
    "firstPartialGrade": 85.0,
    "secondPartialGrade": 90.0,
    "finalExamGrade": null,
    "attendancePercentage": 95,
    "approved": true
  },
  {
    "id": 3,
    "student": {
      "id": 3,
      "registrationNumber": "2025003",
      "name": "Lucas Ferreira"
    },
    "firstPartialGrade": 75.0,
    "secondPartialGrade": 80.0,
    "finalExamGrade": null,
    "attendancePercentage": 90,
    "approved": true
  }
]
```

#### Obter o histórico acadêmico de um aluno

```
GET /enrollments/student/{studentId}/academic-record
```

**Parâmetros:**

- `studentId`: ID do aluno

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "classOffering": {
      "id": 1,
      "discipline": {
        "code": "CC101",
        "name": "Algoritmos e Estruturas de Dados"
      },
      "year": 2025,
      "semester": 1
    },
    "firstPartialGrade": 85.0,
    "secondPartialGrade": 90.0,
    "finalExamGrade": null,
    "attendancePercentage": 95,
    "approved": true
  }
]
```

### Avaliações

#### Listar todas as avaliações

```
GET /evaluations
```

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "student": {
      "id": 1,
      "registrationNumber": "2025001",
      "name": "Ana Souza"
    },
    "classOffering": {
      "id": 1,
      "discipline": {
        "code": "CC101",
        "name": "Algoritmos e Estruturas de Dados"
      },
      "year": 2025,
      "semester": 1
    },
    "firstPartialGrade": 85.0,
    "secondPartialGrade": 90.0,
    "finalExamGrade": null,
    "attendancePercentage": 95,
    "approved": true
  },
  {
    "id": 2,
    "student": {
      "id": 2,
      "registrationNumber": "2025002",
      "name": "Pedro Costa"
    },
    "classOffering": {
      "id": 2,
      "discipline": {
        "code": "CC102",
        "name": "Programação Orientada a Objetos"
      },
      "year": 2025,
      "semester": 1
    },
    "firstPartialGrade": 65.0,
    "secondPartialGrade": 70.0,
    "finalExamGrade": null,
    "attendancePercentage": 85,
    "approved": null
  }
]
```

#### Listar avaliações de um aluno

```
GET /evaluations/student/{studentId}
```

**Parâmetros:**

- `studentId`: ID do aluno

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "classOffering": {
      "id": 1,
      "discipline": {
        "code": "CC101",
        "name": "Algoritmos e Estruturas de Dados"
      },
      "year": 2025,
      "semester": 1
    },
    "firstPartialGrade": 85.0,
    "secondPartialGrade": 90.0,
    "finalExamGrade": null,
    "attendancePercentage": 95,
    "approved": true
  }
]
```

#### Criar uma nova avaliação

```
POST /evaluations
```

**Corpo da Requisição:**

```json
{
  "enrollmentId": 3,
  "firstPartialGrade": 75.0,
  "secondPartialGrade": 80.0,
  "finalExamGrade": null,
  "attendancePercentage": 90
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "student": {
    "id": 3,
    "registrationNumber": "2025003",
    "name": "Lucas Ferreira"
  },
  "classOffering": {
    "id": 1,
    "discipline": {
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    },
    "year": 2025,
    "semester": 1
  },
  "firstPartialGrade": 75.0,
  "secondPartialGrade": 80.0,
  "finalExamGrade": null,
  "attendancePercentage": 90,
  "approved": true
}
```

#### Atualizar uma avaliação existente

```
PUT /evaluations/{id}
```

**Parâmetros:**

- `id`: ID da avaliação (que é o mesmo ID da matrícula)

**Corpo da Requisição:**

```json
{
  "firstPartialGrade": 78.0,
  "secondPartialGrade": 82.0,
  "finalExamGrade": null,
  "attendancePercentage": 92
}
```

**Resposta de Sucesso:**

```json
{
  "id": 3,
  "student": {
    "id": 3,
    "registrationNumber": "2025003",
    "name": "Lucas Ferreira"
  },
  "classOffering": {
    "id": 1,
    "discipline": {
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados"
    },
    "year": 2025,
    "semester": 1
  },
  "firstPartialGrade": 78.0,
  "secondPartialGrade": 82.0,
  "finalExamGrade": null,
  "attendancePercentage": 92,
  "approved": true
}
```

### Relatórios

#### Relatório de frequência de uma turma

```
GET /reports/attendance/{classOfferingId}
```

**Parâmetros:**

- `classOfferingId`: ID da turma

**Resposta de Sucesso:**

```json
[
  {
    "id": 1,
    "student": {
      "id": 1,
      "registrationNumber": "2025001",
      "name": "Ana Souza"
    },
    "attendancePercentage": 95
  },
  {
    "id": 3,
    "student": {
      "id": 3,
      "registrationNumber": "2025003",
      "name": "Lucas Ferreira"
    },
    "attendancePercentage": 92
  }
]
```

#### Histórico escolar de um aluno

```
GET /reports/transcript/{studentId}
```

**Parâmetros:**

- `studentId`: ID do aluno

**Resposta de Sucesso:**

```json
[
  {
    "discipline": {
      "code": "CC101",
      "name": "Algoritmos e Estruturas de Dados",
      "credits": 60
    },
    "year": 2025,
    "semester": 1,
    "finalGrade": 87.5,
    "approved": true
  }
]
```

## Códigos de Status HTTP

A API utiliza os seguintes códigos de status HTTP:

- **200 OK**: A requisição foi bem-sucedida e o conteúdo solicitado está sendo retornado.
- **201 Created**: A requisição foi bem-sucedida e um novo recurso foi criado como resultado.
- **204 No Content**: A requisição foi bem-sucedida, mas não há conteúdo para retornar.
- **400 Bad Request**: A requisição não pôde ser processada devido a um erro do cliente (por exemplo, dados inválidos).
- **404 Not Found**: O recurso solicitado não foi encontrado.
- **409 Conflict**: A requisição não pôde ser concluída devido a um conflito com o estado atual do recurso.
- **500 Internal Server Error**: Ocorreu um erro no servidor ao processar a requisição.

## Tratamento de Erros

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

## Validação de Dados

A API utiliza validação de dados para garantir que os dados enviados pelo cliente estejam corretos. Em caso de erro de validação, a API retorna um objeto JSON com informações sobre os erros de validação:

```json
{
  "timestamp": "2025-06-20T16:00:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Erro de validação",
  "errors": [
    {
      "field": "name",
      "message": "O nome não pode estar em branco"
    },
    {
      "field": "email",
      "message": "O email deve ser um endereço de email válido"
    }
  ],
  "path": "/api/endpoint"
}
```

## Considerações Finais

Esta documentação descreve a API REST do Sistema de Controle Acadêmico da Universidade Master Educacional. A API foi projetada para ser simples e intuitiva, seguindo os princípios RESTful e utilizando o formato JSON para comunicação.

Para mais informações sobre o sistema, consulte o arquivo README.md na raiz do projeto.
