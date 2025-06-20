import React from 'react';
import { 
  BookOpen, 
  Users, 
  GraduationCap, 
  Calendar, 
  ClipboardList, 
  BarChart3, 
  Home,
  Settings
} from 'lucide-react';

interface SidebarProps {
  activeTab: string;
  onTabChange: (tab: string) => void;
  userRole: string;
}

const Sidebar: React.FC<SidebarProps> = ({ activeTab, onTabChange, userRole }) => {
  const menuItems = [
    { id: 'dashboard', label: 'Dashboard', icon: Home, roles: ['SECRETARY', 'COORDINATOR', 'PROFESSOR'] },
    { id: 'courses', label: 'Cursos', icon: BookOpen, roles: ['SECRETARY'] },
    { id: 'subjects', label: 'Disciplinas', icon: BookOpen, roles: ['SECRETARY'] },
    { id: 'students', label: 'Alunos', icon: Users, roles: ['SECRETARY'] },
    { id: 'professors', label: 'Professores', icon: GraduationCap, roles: ['SECRETARY'] },
    { id: 'schedules', label: 'Horários', icon: Calendar, roles: ['SECRETARY', 'COORDINATOR'] },
    { id: 'enrollments', label: 'Matrículas', icon: ClipboardList, roles: ['SECRETARY'] },
    { id: 'evaluations', label: 'Avaliações', icon: BarChart3, roles: ['PROFESSOR', 'SECRETARY'] },
    { id: 'reports', label: 'Relatórios', icon: BarChart3, roles: ['SECRETARY'] },
    { id: 'student-portal', label: 'Portal do Aluno', icon: Users, roles: ['STUDENT'] },
  ];

  const filteredItems = menuItems.filter(item => item.roles.includes(userRole));

  return (
    <div className="bg-white shadow-lg h-full w-64 fixed left-0 top-0 z-30">
      <div className="p-6 border-b border-gray-200">
        <h1 className="text-xl font-bold text-gray-800">Sistema Acadêmico</h1>
        <p className="text-sm text-gray-600 mt-1">Master Educacional</p>
      </div>
      
      <nav className="mt-6">
        {filteredItems.map((item) => {
          const Icon = item.icon;
          return (
            <button
              key={item.id}
              onClick={() => onTabChange(item.id)}
              className={`w-full flex items-center px-6 py-3 text-left transition-colors ${
                activeTab === item.id
                  ? 'bg-blue-50 text-blue-600 border-r-2 border-blue-600'
                  : 'text-gray-600 hover:bg-gray-50 hover:text-gray-800'
              }`}
            >
              <Icon className="w-5 h-5 mr-3" />
              {item.label}
            </button>
          );
        })}
      </nav>
    </div>
  );
};

export default Sidebar;