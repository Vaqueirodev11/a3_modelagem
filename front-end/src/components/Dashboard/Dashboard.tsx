import React from 'react';
import { Users, BookOpen, GraduationCap, Calendar, TrendingUp, Award, AlertCircle, CheckCircle } from 'lucide-react';

const Dashboard: React.FC = () => {
  const stats = [
    { label: 'Total de Alunos', value: '1,247', icon: Users, color: 'bg-blue-500', change: '+12%' },
    { label: 'Cursos Ativos', value: '18', icon: BookOpen, color: 'bg-emerald-500', change: '+2' },
    { label: 'Professores', value: '94', icon: GraduationCap, color: 'bg-purple-500', change: '+5%' },
    { label: 'Turmas Ativas', value: '156', icon: Calendar, color: 'bg-orange-500', change: '+8%' },
  ];

  const recentActivities = [
    { type: 'enrollment', message: 'Novo aluno matriculado: João Silva', time: '2 horas atrás' },
    { type: 'grade', message: 'Notas lançadas para Matemática I', time: '4 horas atrás' },
    { type: 'schedule', message: 'Horário atualizado para Física II', time: '6 horas atrás' },
    { type: 'professor', message: 'Professor Carlos Santos adicionado', time: '1 dia atrás' },
  ];

  const approvalStats = [
    { subject: 'Matemática I', approved: 85, failed: 15, inExam: 12 },
    { subject: 'Física II', approved: 78, failed: 22, inExam: 18 },
    { subject: 'Química Geral', approved: 92, failed: 8, inExam: 5 },
    { subject: 'Programação I', approved: 88, failed: 12, inExam: 8 },
  ];

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold text-gray-800">Dashboard</h1>
        <div className="text-sm text-gray-500">
          Último acesso: Hoje, 14:30
        </div>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat, index) => {
          const Icon = stat.icon;
          return (
            <div key={index} className="bg-white rounded-lg shadow p-6">
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-sm font-medium text-gray-600">{stat.label}</p>
                  <p className="text-2xl font-bold text-gray-900 mt-1">{stat.value}</p>
                  <p className="text-sm text-emerald-600 mt-1">{stat.change}</p>
                </div>
                <div className={`${stat.color} p-3 rounded-full`}>
                  <Icon className="w-6 h-6 text-white" />
                </div>
              </div>
            </div>
          );
        })}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white rounded-lg shadow">
          <div className="p-6 border-b border-gray-200">
            <h3 className="text-lg font-semibold text-gray-800">Atividades Recentes</h3>
          </div>
          <div className="p-6">
            <div className="space-y-4">
              {recentActivities.map((activity, index) => (
                <div key={index} className="flex items-start space-x-3">
                  <div className="w-2 h-2 bg-blue-500 rounded-full mt-2"></div>
                  <div className="flex-1">
                    <p className="text-sm text-gray-800">{activity.message}</p>
                    <p className="text-xs text-gray-500 mt-1">{activity.time}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        <div className="bg-white rounded-lg shadow">
          <div className="p-6 border-b border-gray-200">
            <h3 className="text-lg font-semibold text-gray-800">Estatísticas de Aprovação</h3>
          </div>
          <div className="p-6">
            <div className="space-y-4">
              {approvalStats.map((stat, index) => (
                <div key={index} className="space-y-2">
                  <div className="flex items-center justify-between">
                    <span className="text-sm font-medium text-gray-700">{stat.subject}</span>
                    <span className="text-xs text-gray-500">{stat.approved + stat.failed + stat.inExam} alunos</span>
                  </div>
                  <div className="flex h-2 bg-gray-200 rounded-full overflow-hidden">
                    <div 
                      className="bg-emerald-500" 
                      style={{ width: `${(stat.approved / (stat.approved + stat.failed + stat.inExam)) * 100}%` }}
                    ></div>
                    <div 
                      className="bg-yellow-500" 
                      style={{ width: `${(stat.inExam / (stat.approved + stat.failed + stat.inExam)) * 100}%` }}
                    ></div>
                    <div 
                      className="bg-red-500" 
                      style={{ width: `${(stat.failed / (stat.approved + stat.failed + stat.inExam)) * 100}%` }}
                    ></div>
                  </div>
                  <div className="flex items-center space-x-4 text-xs">
                    <div className="flex items-center space-x-1">
                      <div className="w-2 h-2 bg-emerald-500 rounded-full"></div>
                      <span className="text-gray-600">Aprovados: {stat.approved}%</span>
                    </div>
                    <div className="flex items-center space-x-1">
                      <div className="w-2 h-2 bg-yellow-500 rounded-full"></div>
                      <span className="text-gray-600">Prova Final: {stat.inExam}%</span>
                    </div>
                    <div className="flex items-center space-x-1">
                      <div className="w-2 h-2 bg-red-500 rounded-full"></div>
                      <span className="text-gray-600">Reprovados: {stat.failed}%</span>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>

      <div className="bg-white rounded-lg shadow">
        <div className="p-6 border-b border-gray-200">
          <h3 className="text-lg font-semibold text-gray-800">Alertas do Sistema</h3>
        </div>
        <div className="p-6">
          <div className="space-y-3">
            <div className="flex items-center space-x-3 p-3 bg-yellow-50 rounded-lg">
              <AlertCircle className="w-5 h-5 text-yellow-600" />
              <div>
                <p className="text-sm font-medium text-yellow-800">5 alunos com frequência abaixo de 75%</p>
                <p className="text-xs text-yellow-600">Revisar situação acadêmica</p>
              </div>
            </div>
            <div className="flex items-center space-x-3 p-3 bg-blue-50 rounded-lg">
              <CheckCircle className="w-5 h-5 text-blue-600" />
              <div>
                <p className="text-sm font-medium text-blue-800">Período de matrícula encerra em 3 dias</p>
                <p className="text-xs text-blue-600">Lembrar coordenadores</p>
              </div>
            </div>
            <div className="flex items-center space-x-3 p-3 bg-emerald-50 rounded-lg">
              <TrendingUp className="w-5 h-5 text-emerald-600" />
              <div>
                <p className="text-sm font-medium text-emerald-800">Taxa de aprovação aumentou 8% este semestre</p>
                <p className="text-xs text-emerald-600">Excelente progresso!</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;