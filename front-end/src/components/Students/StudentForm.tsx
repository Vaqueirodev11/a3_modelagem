import React, { useState, useEffect } from 'react';
import { Student, Course } from '../../types';

interface StudentFormProps {
  student?: Student;
  courses: Course[];
  onSave: (data: any) => void;
  onCancel: () => void;
}

const StudentForm: React.FC<StudentFormProps> = ({ student, courses, onSave, onCancel }) => {
  const [formData, setFormData] = useState({
    registrationNumber: '',
    name: '',
    address: '',
    phone: '',
    courseId: '',
    status: 'ENROLLED'
  });

  const [errors, setErrors] = useState<{[key: string]: string}>({});

  useEffect(() => {
    if (student) {
      setFormData({
        registrationNumber: student.registrationNumber,
        name: student.name,
        address: student.address,
        phone: student.phone,
        courseId: student.courseId.toString(),
        status: student.status
      });
    }
  }, [student]);

  const validate = () => {
    const newErrors: {[key: string]: string} = {};

    if (!formData.registrationNumber.trim()) {
      newErrors.registrationNumber = 'Número de matrícula é obrigatório';
    }

    if (!formData.name.trim()) {
      newErrors.name = 'Nome é obrigatório';
    }

    if (!formData.address.trim()) {
      newErrors.address = 'Endereço é obrigatório';
    }

    if (!formData.phone.trim()) {
      newErrors.phone = 'Telefone é obrigatório';
    }

    if (!formData.courseId) {
      newErrors.courseId = 'Curso é obrigatório';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (validate()) {
      onSave({
        registrationNumber: formData.registrationNumber,
        name: formData.name,
        address: formData.address,
        phone: formData.phone,
        courseId: parseInt(formData.courseId),
        status: formData.status
      });
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
    
    if (errors[name]) {
      setErrors(prev => ({
        ...prev,
        [name]: ''
      }));
    }
  };

  return (
    <form onSubmit={handleSubmit} className="space-y-4">
      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Número de Matrícula *
        </label>
        <input
          type="text"
          name="registrationNumber"
          value={formData.registrationNumber}
          onChange={handleChange}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.registrationNumber ? 'border-red-500' : 'border-gray-300'
          }`}
          placeholder="Ex: 2024001"
        />
        {errors.registrationNumber && <p className="text-red-500 text-sm mt-1">{errors.registrationNumber}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Nome Completo *
        </label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.name ? 'border-red-500' : 'border-gray-300'
          }`}
          placeholder="Nome completo do aluno"
        />
        {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Endereço *
        </label>
        <textarea
          name="address"
          value={formData.address}
          onChange={handleChange}
          rows={2}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.address ? 'border-red-500' : 'border-gray-300'
          }`}
          placeholder="Endereço completo"
        />
        {errors.address && <p className="text-red-500 text-sm mt-1">{errors.address}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Telefone *
        </label>
        <input
          type="tel"
          name="phone"
          value={formData.phone}
          onChange={handleChange}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.phone ? 'border-red-500' : 'border-gray-300'
          }`}
          placeholder="(11) 99999-9999"
        />
        {errors.phone && <p className="text-red-500 text-sm mt-1">{errors.phone}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Curso *
        </label>
        <select
          name="courseId"
          value={formData.courseId}
          onChange={handleChange}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.courseId ? 'border-red-500' : 'border-gray-300'
          }`}
        >
          <option value="">Selecione um curso</option>
          {courses.map(course => (
            <option key={course.id} value={course.id}>
              {course.description}
            </option>
          ))}
        </select>
        {errors.courseId && <p className="text-red-500 text-sm mt-1">{errors.courseId}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Status
        </label>
        <select
          name="status"
          value={formData.status}
          onChange={handleChange}
          className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
        >
          <option value="ENROLLED">Matriculado</option>
          <option value="LOCKED">Trancado</option>
          <option value="GRADUATED">Formado</option>
          <option value="EVADED">Evadido</option>
        </select>
      </div>

      <div className="flex space-x-3 pt-4">
        <button
          type="submit"
          className="flex-1 bg-blue-600 text-white py-2 px-4 rounded-lg hover:bg-blue-700 transition-colors"
        >
          Salvar
        </button>
        <button
          type="button"
          onClick={onCancel}
          className="flex-1 bg-gray-300 text-gray-700 py-2 px-4 rounded-lg hover:bg-gray-400 transition-colors"
        >
          Cancelar
        </button>
      </div>
    </form>
  );
};

export default StudentForm;