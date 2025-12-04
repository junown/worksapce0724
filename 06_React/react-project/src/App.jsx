import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import styled from 'styled-components'; 
import Header from './components/Header';
import Login from './pages/Login';
import Signup from './pages/Signup';
import Home from './pages/Home';
import MyPage from './pages/MyPage';
import CategoryList from './pages/CategoryList';
import { UserProvider } from './context/UserContext';
import { ProductProvider } from './context/ProductContext';
import ProductRegister from './pages/ProductRegister';
import ProductDetail from './pages/ProductDetail';
import SearchPage from './pages/SerachPage';
import NotFound from './pages/NotFound';

const MainContainer = styled.div`
  max-width: 1200px; 
  margin: 0 auto;   
  padding: 20px;    
  width: 100%;    
`;


function App() {
  return (
    <UserProvider>
      <ProductProvider>
      <BrowserRouter>
        <Header /> 
        <MainContainer>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/category/:categoryName" element={<CategoryList />} />
            <Route path="/register" element={<ProductRegister />} />
            <Route path="/product/:id" element={<ProductDetail />} />
            <Route path="/search/:keyword" element={<SearchPage />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </MainContainer>
      </BrowserRouter>
      </ProductProvider>
    </UserProvider>
  );
}

export default App;