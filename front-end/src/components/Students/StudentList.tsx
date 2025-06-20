import React, { useState, useEffect } from 'react';
import { Plus, Edit, Trash2, UserCheck, UserX, GraduationCap, Lock } from 'lucide-react';
import { Student, Course } from '../../types';
import { apiService } from '../../services/api';
import Table from '../Common/Table';
import Modal from '../Common/Modal';
import StudentForm from './StudentForm';

const StudentList: React.FC = () => {
  const [students, setStudents] = useState<Student[]>([]);
  const [courses, setCourses] = useState<Course[]>([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedStudent, setSelectedStudent] = useState<Student | undefined>();

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      setLoading(true);
      const [studentsData, coursesData] = await Promise.all([
        apiService.getStudents(),
        apiService.getCourses()
      ]);
      setStudents(studentsData);
      setCourses(coursesData);
    } catch (error) {
      console.error('Erro ao carregar dados:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleCreate = () => {
    setSelectedStudent(undefined);
    setModalOpen(true);
  };

  const handleEdit = (student: Student) => {
    setSelectedStudent(student);
    setModalOpen(true);
  };

  const handleDelete = async (id: number) => {
    if (window.confirm('Tem certeza que deseja excluir este aluno?')) {
      try {
        await apiService.deleteStudent(id);
        await loadData();
      } catch (error) {
        console.error('Erro ao excluir aluno:', error);
      }
    }
  };

  const handleSave = async (studentData: any) => {
    try {
      if (selectedStudent) {
        await apiService.updateStudent(selectedStudent.id!, studentData);
      } else {
        await apiService.createStudent(studentData);
      }
      setModalOpen(false);
      await loadData();
    } catch (error) {
      console.error('Erro ao salvar aluno:', error);
    }
  };

  const getStatusIcon = (status: string) => {
    switch (status) {
      case 'ENROLLED':
        return <UserCheck className="w-4 h-4 text-green-600" />;
      case 'GRADUATED':
        return <GraduationCap className="w-4 h-4 text-blue-600" />;
      case 'LOCKED':
        return <Lock className="w-4 h-4 text-yellow-600" />;
      case 'EVADED':
        return <UserX className="w-4 h-4 text-red-600" />;
      default:
        return null;
    }
  };

  const getStatusText = (status: string) => {
    const statusMap = {
      ENROLLED: 'Matriculado',
      GRADUATED: 'Formado',
      LOCKED: 'Trancado',
      EVADED: 'Evadido'
    };
    return statusMap[status as keyof typeof statusMap] || status;
  };

  const columns = [
    { key: 'registrationNumber', label: 'Matrícula', sortable: true },
    { key: 'name', label: 'Nome', sortable: true },
    { key: 'phone', label: 'Telefone' },
    { 
      key: 'courseId', 
      label: 'Curso', 
      render: (courseId: number) => {
        const course = courses.find(c => c.id === courseId);
        return course ? course.description : 'N/A';
      }
    },
    {
      key: 'status',
      label: 'Status',
      render: (status: string) => (
        <div className="flex items-center space-x-2">
          {getStatusIcon(status)}
          <span className={`text-sm ${
            status === 'ENROLLED' ? 'text-green-600' :
            status === 'GRADUATED' ? 'text-blue-600' :
            status === 'LOCKED' ? 'text-yellow-600' :
            'text-red-600'
          }`}>
            {getStatusText(status)}
          </span>
        </div>
      )
    },
    {
      key: 'actions',
      label: 'Ações',
      render: (_, student: Student) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleEdit(student)}
            className="p-1 text-blue-600 hover:text-blue-800"
          >
            <Edit className="w-4 h-4" />
          </button>
          <button
            onClick={() => handleDelete(student.id!)}
            className="p-1 text-red-600 hover:text-red-800"
          >
            <Trash2 className="w-4 h-4" />
          </button>
        </div>
      )
    }
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold text-gray-800">Alunos</h1>
        <button
          onClick={handleCreate}
          className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors flex items-center space-x-2"
        >
          <Plus className="w-4 h-4" />
          <span>Novo Aluno</span>
        </button>
      </div>

      <Table
        columns={columns}
        data={students}
        loading={loading}
      />

      <Modal
        isOpen={modalOpen}
        onClose={() => setModalOpen(false)}
        title={selectedStudent ? 'Editar Aluno' : 'Novo Aluno'}
      >
        <StudentForm
          student={selectedStudent}
          courses={courses}
          onSave={handleSave}
          onCancel={() => setModalOpen(false)}
        />
      </Modal>
    </div>
  );
};

export default StudentList;