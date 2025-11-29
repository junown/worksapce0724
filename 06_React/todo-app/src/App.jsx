import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from "./pages/Home";
import TodoListPage from "./pages/TodoListPage";
import TodoDetail from "./pages/TodoDetail";
import CategoryPage from "./pages/CategoryPage";
import NotFound from "./pages/NotFound";
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import { TodoProvider } from './context/TodoContext';

function App() {
  return (
    <TodoProvider>
      <BrowserRouter>
      <nav style={{ padding: '20px', backgroundColor: '#f0f0f0', marginBottom: '20px' }}>
        <div style={{ display: 'flex', gap: '15px' }}>
          <Link to="/">TODO 리스트</Link>
          <Link to="/todos">할일 목록</Link>
          <span>|</span>
          <Link to="/category/work">업무(Work)</Link>
          <Link to="/category/personal">개인업무(Personal)</Link>
          <Link to="/category/study">공부(Study)</Link>
        </div>
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/todos" element={<TodoListPage />} />
        <Route path="/todos/:id" element={<TodoDetail />} />
        <Route path="/category/:category" element={<CategoryPage />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
     </BrowserRouter>
    </TodoProvider>
  )
}

export default App
