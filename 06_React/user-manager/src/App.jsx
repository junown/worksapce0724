import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import UserList from './pages/UserList'
import UserDetail from './pages/UserDetail'
import UserRegistration from './pages/UserRegistration'
import NotFound from './pages/NotFound'

//Provider 불러오기
import { UserProvider } from './context/UserContext'

function App() {



  return (
    <UserProvider>
      <BrowserRouter>
      <nav>
        <Link to="/">유저 목록 페이지</Link>
        <Link to="/user">유저등록페이지</Link>
      </nav>
        <Routes>
          <Route path='/' element={<UserList />}/>
          <Route path='/user/:id' element={<UserDetail />}/>
          <Route path='/user' element={<UserRegistration />}/>
          <Route path='*' element={<NotFound />}/>
        </Routes>
      </BrowserRouter>
    </UserProvider>
  )
}

export default App
