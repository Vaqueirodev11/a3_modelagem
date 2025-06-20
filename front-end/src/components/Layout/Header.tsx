import React from 'react';
import { User, LogOut, Bell } from 'lucide-react';

interface HeaderProps {
  user: {
    name: string;
    role: string;
    email: string;
  };
}

const Header: React.FC<HeaderProps> = ({ user }) => {
  const getRoleName = (role: string) => {
    const roles = {
      SECRETARY: 'Secretária',
      COORDINATOR: 'Coordenador',
      PROFESSOR: 'Professor',
      STUDENT: 'Aluno'
    };
    return roles[role as keyof typeof roles] || role;
  };

  return (
    <header className="bg-white shadow-sm border-b border-gray-200 fixed top-0 right-0 left-64 z-20">
      <div className="flex items-center justify-between px-6 py-4">
        <div className="flex items-center space-x-4">
          <h2 className="text-lg font-semibold text-gray-800">
            Bem-vindo, {user.name}!
          </h2>
        </div>
        
        <div className="flex items-center space-x-4">
          <button className="p-2 text-gray-400 hover:text-gray-600 relative">
            <Bell className="w-5 h-5" />
            <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">
              3
            </span>
          </button>
          
          <div className="flex items-center space-x-3 bg-gray-50 rounded-lg px-3 py-2">
            <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center">
              <User className="w-4 h-4 text-blue-600" />
            </div>
            <div className="text-sm">
              <p className="font-medium text-gray-800">{user.name}</p>
              <p className="text-gray-500">{getRoleName(user.role)}</p>
            </div>
          </div>
          
          <button className="p-2 text-gray-400 hover:text-red-500 transition-colors">
            <LogOut className="w-5 h-5" />
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;