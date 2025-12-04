import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import { UserContext } from '../context/UserContext';

import { RegisterContainer, Title, RegisterForm, InputGroup, SubmitButton } from './ProductRegister.styled';

const ProductRegister = () => {
    const { addProduct } = useContext(ProductContext);
    const { user } = useContext(UserContext);
    const navigate = useNavigate();

    const [input, setInput] = useState({
        name: '',
        price: '',
        category: '전자기기',
        description: '',
        imageUrl: ''
    });

    const handleChange = (e) => {
        setInput({...input, [e.target.name] : e.target.value});
    };

    const handleSubmit = () =>{
    if (!input.name || !input.price || !input.description) {
      return alert('내용을 모두 입력해주세요.');
    }

        addProduct({
            name: input.name,
            price: Number(input.price),
            category: input.category,
            description: input.description,
            seller: user.id,
            images: input.imageUrl ? [input.imageUrl] : []
        });

        alert('상품이 성공적으로 등록되었습니다');
        navigate('/');
    }

    if(!user) {
        return <div>로그인이 필요합니다.</div>;
    }
  return (
    <RegisterContainer>
      <Title>내 물건 팔기</Title>
      
      <RegisterForm>
        <InputGroup>
          <label>대표 이미지 URL</label>
          <input 
            name="imageUrl" 
            value={input.imageUrl} 
            onChange={handleChange} 
            placeholder="이미지 주소를 복사해서 붙여넣으세요 (https://...)" 
          />
          {input.imageUrl && (
            <div style={{ marginTop: '10px' }}>
                <span style={{ fontSize: '12px', color: '#666' }}>미리보기:</span>
                <br />
                <img 
                    src={input.imageUrl} 
                    alt="미리보기" 
                    style={{ width: '150px', height: '150px', objectFit: 'cover', borderRadius: '8px', marginTop: '5px' }} 
                    onError={(e) => { e.target.src = 'https://via.placeholder.com/150?text=No+Image'; }}
                />
            </div>
          )}
        </InputGroup>

        <InputGroup>
          <label>상품명</label>
          <input 
            name="name" 
            value={input.name} 
            onChange={handleChange} 
            placeholder="상품 제목을 입력하세요" 
          />
        </InputGroup>

        <InputGroup>
          <label>가격</label>
          <input 
            type="number" 
            name="price" 
            value={input.price} 
            onChange={handleChange} 
            placeholder="가격을 입력하세요 (숫자만)" 
          />
        </InputGroup>

        <InputGroup>
          <label>카테고리</label>
          <select name="category" value={input.category} onChange={handleChange}>
            <option value="전자기기">전자기기</option>
            <option value="패션">패션</option>
            <option value="뷰티">뷰티</option>
          </select>
        </InputGroup>

        <InputGroup>
          <label>상품 설명</label>
          <textarea 
            name="description" 
            value={input.description} 
            onChange={handleChange} 
            placeholder="구매 시기, 브랜드, 사용감 등을 적어주세요." 
          />
        </InputGroup>

        <SubmitButton onClick={handleSubmit}>등록하기</SubmitButton>
      </RegisterForm>
    </RegisterContainer>
  )
}

export default ProductRegister