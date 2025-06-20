import React, { useState } from 'react';
import Sidebar from './components/Layout/Sidebar';
import Header from './components/Layout/Header';
import Dashboard from './components/Dashboard/Dashboard';
import CourseList from './components/Courses/CourseList';
import StudentList from './components/Students/StudentList';

function App() {
  const [activeTab, setActiveTab] = useState('dashboard');
  
  const user = {
    name: 'Maria Silva',
    role: 'SECRETARY',
    email: 'maria.silva@university.edu'
  };

  const renderContent = () => {
    switch (activeTab) {
      case 'dashboard':
        return <Dashboard />;
      case 'courses':
        return <CourseList />;
      case 'students':
        return <StudentList />;
      default:
        return <Dashboard />;
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Sidebar 
        activeTab={activeTab} 
        onTabChange={setActiveTab} 
        userRole={user.role}
      />
      
      <div className="ml-64">
        <Header user={user} />
        
        <main className="pt-20 p-6">
          {renderContent()}
        </main>
      </div>
    </div>
  );
}

export default App;