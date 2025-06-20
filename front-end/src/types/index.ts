export interface Course {
  id?: number;
  code: string;
  description: string;
  coordinatorId: number;
  coordinator?: Professor;
}

export interface Subject {
  id?: number;
  code: string;
  description: string;
  workload: number;
  semester: number;
  bibliography: string;
  prerequisites: string;
  courseId: number;
  course?: Course;
}

export interface Student {
  id?: number;
  registrationNumber: string;
  name: string;
  address: string;
  phone: string;
  courseId: number;
  course?: Course;
  status: 'ENROLLED' | 'LOCKED' | 'GRADUATED' | 'EVADED';
}

export interface Professor {
  id?: number;
  name: string;
  address: string;
  phone: string;
  qualification: 'GRADUATION' | 'SPECIALIZATION' | 'MASTERS' | 'DOCTORATE';
  courses: number[];
}

export interface ClassSchedule {
  id?: number;
  subjectId: number;
  subject?: Subject;
  professorId: number;
  professor?: Professor;
  year: number;
  semester: number;
  daysOfWeek: string[];
  schedule: string;
}

export interface Enrollment {
  id?: number;
  studentId: number;
  student?: Student;
  classScheduleId: number;
  classSchedule?: ClassSchedule;
}

export interface Evaluation {
  id?: number;
  enrollmentId: number;
  enrollment?: Enrollment;
  partialGrade1?: number;
  partialGrade2?: number;
  finalExamGrade?: number;
  frequency: number;
  status: 'APPROVED' | 'FAILED' | 'FINAL_EXAM';
}

export interface User {
  id: number;
  name: string;
  role: 'SECRETARY' | 'COORDINATOR' | 'PROFESSOR' | 'STUDENT';
  email: string;
}