import { useState } from 'react'
import './App.css'
import UseStateTest from './components/useState/UseStateTest'
import Signup from './components/useState/Signup'
import LandingPage from './components/useState/LandingPage'
import UseRefTest from './components/useRef/UseRefTest'
import UseRefScroll from './components/useRef/UseRefScroll'
import UseMemoTest from './components/useMemo/UseMemoTest'
import UseCallbackTest from './components/useCallback/UseCallbackTest'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      {/* <UseStateTest /> */}
      {/* <Signup /> */}
      {/* <LandingPage /> */}
      {/* <UseRefTest /> */}
      {/* <UseRefScroll /> */}
      {/* <UseMemoTest /> */}
      {/* <UseCallbackTest /> */}
      {/* <UseContextTest /> */}
      {/* <MyInfo /> */}
      {/* <ToggleBox /> */}
      <UserProvider>
        <Header />
      </UserProvider>
    </>
  )
}

export default App
