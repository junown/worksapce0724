import React, { Children, createContext, use, useEffect, useState } from 'react'

export const TodoContext = createContext();

export const TodoProvider = ({children}) => {
  const [todos, setTodos] = useState(() => {
    const savedTodos = localStorage.getItem('todos');
    return savedTodos ? JSON.parse(savedTodos) : [
        { id: 1, text: '리액트 공부하기', category: 'study', completed: false, createdAt: today },
        { id: 2, text: '여행가기', category: 'personal', completed: true, createdAt: today },
        { id: 3, text: '세미 프로젝트', category: 'work', completed: true, createdAt: today },
        { id: 4, text: '파이널 프로젝트', category: 'work', completed: false, createdAt: today },
    ];
  });
    
    useEffect(() => {
        localStorage.setItem('todos', JSON.stringify(todos));
    }, [todos]);

    const addTodo = (text, category) => {
        setTodos([
          ...todos,
          { id: Date.now(),
            text,
            category,
            completed: false,
            createdAt: new Date().toISOString()
         }
        ]);
      };
  
    const deleteTodo = (id) => {
        setTodos(todos.filter(todo => todo.id !== id));
    }

    const toggleTodo = (id) => {
        setTodos(todos.map(todo => 
          todo.id === id ? { ...todo, completed: !todo.completed } : todo
        ));
      };

      const updateTodo = (id, newText, newCategory) => {
        setTodos(todos.map(todo =>
          todo.id === id ? { ...todo, text: newText, category: newCategory } : todo
        ));
      };

    return (
    <TodoContext.Provider value={{todos, addTodo, deleteTodo, toggleTodo, updateTodo}}>
        {children}
    </TodoContext.Provider>
  )
}

export default TodoContext