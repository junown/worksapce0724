import { useState } from 'react'
import './App.css'
import { Route, BrowserRouter as Router } from 'react-router-dom'

function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
      <Routes>
        <Route path='/' element>
      </Routes>
    </Router>
  )
}

export default App
