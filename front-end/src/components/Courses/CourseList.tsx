import React, { useState, useEffect } from 'react';
import { Plus, Edit, Trash2 } from 'lucide-react';
import { Course, Professor } from '../../types';
import { apiService } from '../../services/api';
import Table from '../Common/Table';
import Modal from '../Common/Modal';
import CourseForm from './CourseForm';

const CourseList: React.FC = () => {
  const [courses, setCourses] = useState<Course[]>([]);
  const [professors, setProfessors] = useState<Professor[]>([]);
  const [loading, setLoading] = useState(true);
  const [modalOpen, setModalOpen] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState<Course | undefined>();

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      setLoading(true);
      const [coursesData, professorsData] = await Promise.all([
        apiService.getCourses(),
        apiService.getProfessors()
      ]);
      setCourses(coursesData);
      setProfessors(professorsData);
    } catch (error) {
      console.error('Erro ao carregar dados:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleCreate = () => {
    setSelectedCourse(undefined);
    setModalOpen(true);
  };

  const handleEdit = (course: Course) => {
    setSelectedCourse(course);
    setModalOpen(true);
  };

  const handleDelete = async (id: number) => {
    if (window.confirm('Tem certeza que deseja excluir este curso?')) {
      try {
        await apiService.deleteCourse(id);
        await loadData();
      } catch (error) {
        console.error('Erro ao excluir curso:', error);
      }
    }
  };

  const handleSave = async (courseData: any) => {
    try {
      if (selectedCourse) {
        await apiService.updateCourse(selectedCourse.id!, courseData);
      } else {
        await apiService.createCourse(courseData);
      }
      setModalOpen(false);
      await loadData();
    } catch (error) {
      console.error('Erro ao salvar curso:', error);
    }
  };

  const columns = [
    { key: 'code', label: 'Código', sortable: true },
    { key: 'description', label: 'Descrição', sortable: true },
    { 
      key: 'coordinatorId', 
      label: 'Coordenador', 
      render: (coordinatorId: number) => {
        const coordinator = professors.find(p => p.id === coordinatorId);
        return coordinator ? coordinator.name : 'N/A';
      }
    },
    {
      key: 'actions',
      label: 'Ações',
      render: (_, course: Course) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleEdit(course)}
            className="p-1 text-blue-600 hover:text-blue-800"
          >
            <Edit className="w-4 h-4" />
          </button>
          <button
            onClick={() => handleDelete(course.id!)}
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
        <h1 className="text-2xl font-bold text-gray-800">Cursos</h1>
        <button
          onClick={handleCreate}
          className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition-colors flex items-center space-x-2"
        >
          <Plus className="w-4 h-4" />
          <span>Novo Curso</span>
        </button>
      </div>

      <Table
        columns={columns}
        data={courses}
        loading={loading}
      />

      <Modal
        isOpen={modalOpen}
        onClose={() => setModalOpen(false)}
        title={selectedCourse ? 'Editar Curso' : 'Novo Curso'}
      >
        <CourseForm
          course={selectedCourse}
          professors={professors}
          onSave={handleSave}
          onCancel={() => setModalOpen(false)}
        />
      </Modal>
    </div>
  );
};

export default CourseList;