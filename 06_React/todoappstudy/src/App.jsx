import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import AppRoutes from './routes/AppRoutes'
import { TodoProvider } from './context/TodoContext'

function App() {

  return (
    <TodoProvider>
      <AppRoutes/>
    </TodoProvider>
  )
}

export default App
