import { useState } from 'react'
import './App.css'
import JavaScript from './components/JavaScript'
import Style from './components/Style'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {/* <JavaScript/> */}
      <Style />
    </>
  )
}

export default App
