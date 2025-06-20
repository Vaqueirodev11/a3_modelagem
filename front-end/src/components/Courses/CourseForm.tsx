import React, { useState, useEffect } from 'react';
import { Course, Professor } from '../../types';

interface CourseFormProps {
  course?: Course;
  professors: Professor[];
  onSave: (data: any) => void;
  onCancel: () => void;
}

const CourseForm: React.FC<CourseFormProps> = ({ course, professors, onSave, onCancel }) => {
  const [formData, setFormData] = useState({
    code: '',
    description: '',
    coordinatorId: ''
  });

  const [errors, setErrors] = useState<{[key: string]: string}>({});

  useEffect(() => {
    if (course) {
      setFormData({
        code: course.code,
        description: course.description,
        coordinatorId: course.coordinatorId.toString()
      });
    }
  }, [course]);

  const validate = () => {
    const newErrors: {[key: string]: string} = {};

    if (!formData.code.trim()) {
      newErrors.code = 'Código é obrigatório';
    }

    if (!formData.description.trim()) {
      newErrors.description = 'Descrição é obrigatória';
    }

    if (!formData.coordinatorId) {
      newErrors.coordinatorId = 'Coordenador é obrigatório';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    
    if (validate()) {
      onSave({
        code: formData.code,
        description: formData.description,
        coordinatorId: parseInt(formData.coordinatorId)
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
          Código *
        </label>
        <input
          type="text"
          name="code"
          value={formData.code}
          onChange={handleChange}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.code ? 'border-red-500' : 'border-gray-300'
          }`}
          placeholder="Ex: ENG001"
        />
        {errors.code && <p className="text-red-500 text-sm mt-1">{errors.code}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Descrição *
        </label>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
          rows={3}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.description ? 'border-red-500' : 'border-gray-300'
          }`}
          placeholder="Descrição do curso"
        />
        {errors.description && <p className="text-red-500 text-sm mt-1">{errors.description}</p>}
      </div>

      <div>
        <label className="block text-sm font-medium text-gray-700 mb-1">
          Coordenador *
        </label>
        <select
          name="coordinatorId"
          value={formData.coordinatorId}
          onChange={handleChange}
          className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500 ${
            errors.coordinatorId ? 'border-red-500' : 'border-gray-300'
          }`}
        >
          <option value="">Selecione um coordenador</option>
          {professors.map(professor => (
            <option key={professor.id} value={professor.id}>
              {professor.name}
            </option>
          ))}
        </select>
        {errors.coordinatorId && <p className="text-red-500 text-sm mt-1">{errors.coordinatorId}</p>}
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

export default CourseForm;