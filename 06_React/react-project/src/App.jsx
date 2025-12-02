import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import styled from 'styled-components'; 
import { DataProvider } from './context/DataContext';

import Header from './components/Header';
import Login from './pages/Login';
import Signup from './pages/Signup';

const MainContainer = styled.div`
  max-width: 1200px; 
  margin: 0 auto;   
  padding: 20px;    
  width: 100%;    
`;

const Home = () => <div style={{textAlign: 'center', marginTop: '50px'}}><h2>홈 화면 (상품 리스트)</h2></div>;
const MyPage = () => <div>마이페이지</div>;
const CategoryList = () => <div>카테고리 페이지</div>;
const NotFound = () => <div>404 Not Found</div>;

function App() {
  return (
    <DataProvider>
      <BrowserRouter>
        <Header /> 
        <MainContainer>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/category/:categoryName" element={<CategoryList />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </MainContainer>

      </BrowserRouter>
    </DataProvider>
  );
}

export default App;