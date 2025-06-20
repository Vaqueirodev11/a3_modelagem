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

  async getCourses() {
    return this.request('/courses');
  }

  async createCourse(course: any) {
    return this.request('/courses', {
      method: 'POST',
      body: JSON.stringify(course),
    });
  }

  async updateCourse(id: number, course: any) {
    return this.request(`/courses/${id}`, {
      method: 'PUT',
      body: JSON.stringify(course),
    });
  }

  async deleteCourse(id: number) {
    return this.request(`/courses/${id}`, {
      method: 'DELETE',
    });
  }

  async getDisciplines() {
    return this.request('/disciplines');
  }

  async createDiscipline(discipline: any) {
    return this.request('/disciplines', {
      method: 'POST',
      body: JSON.stringify(discipline),
    });
  }

  async updateDiscipline(id: number, discipline: any) {
    return this.request(`/disciplines/${id}`, {
      method: 'PUT',
      body: JSON.stringify(discipline),
    });
  }

  async deleteDiscipline(id: number) {
    return this.request(`/disciplines/${id}`, {
      method: 'DELETE',
    });
  }

  async getStudents() {
    return this.request('/students');
  }

  async createStudent(student: any) {
    return this.request('/students', {
      method: 'POST',
      body: JSON.stringify(student),
    });
  }

  async updateStudent(id: number, student: any) {
    return this.request(`/students/${id}`, {
      method: 'PUT',
      body: JSON.stringify(student),
    });
  }

  async deleteStudent(id: number) {
    return this.request(`/students/${id}`, {
      method: 'DELETE',
    });
  }

  async getProfessors() {
    return this.request('/professors');
  }

  async createProfessor(professor: any) {
    return this.request('/professors', {
      method: 'POST',
      body: JSON.stringify(professor),
    });
  }

  async updateProfessor(id: number, professor: any) {
    return this.request(`/professors/${id}`, {
      method: 'PUT',
      body: JSON.stringify(professor),
    });
  }

  async deleteProfessor(id: number) {
    return this.request(`/professors/${id}`, {
      method: 'DELETE',
    });
  }

  async getClassOfferings() {
    return this.request('/class-offerings');
  }

  async createClassOffering(classOffering: any) {
    return this.request('/class-offerings', {
      method: 'POST',
      body: JSON.stringify(classOffering),
    });
  }

  async updateClassOffering(id: number, classOffering: any) {
    return this.request(`/class-offerings/${id}`, {
      method: 'PUT',
      body: JSON.stringify(classOffering),
    });
  }

  async deleteClassOffering(id: number) {
    return this.request(`/class-offerings/${id}`, {
      method: 'DELETE',
    });
  }

  async getEnrollments() {
    return this.request('/enrollments');
  }

  async createEnrollment(enrollment: any) {
    return this.request('/enrollments', {
      method: 'POST',
      body: JSON.stringify(enrollment),
    });
  }

  async deleteEnrollment(id: number) {
    return this.request(`/enrollments/${id}`, {
      method: 'DELETE',
    });
  }

  async getEvaluations() {
    return this.request('/evaluations');
  }

  async createEvaluation(evaluation: any) {
    return this.request('/evaluations', {
      method: 'POST',
      body: JSON.stringify(evaluation),
    });
  }

  async updateEvaluation(id: number, evaluation: any) {
    return this.request(`/evaluations/${id}`, {
      method: 'PUT',
      body: JSON.stringify(evaluation),
    });
  }

  async getStudentEvaluations(studentId: number) {
    return this.request(`/students/${studentId}/evaluations`);
  }

  async getClassAttendanceReport(classId: number) {
    return this.request(`/reports/attendance/${classId}`);
  }

  async getStudentTranscript(studentId: number) {
    return this.request(`/reports/transcript/${studentId}`);
  }
}

export const apiService = new ApiService();
