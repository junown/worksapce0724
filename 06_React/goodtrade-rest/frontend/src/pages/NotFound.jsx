import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 60vh;
  text-align: center;
`;

const ErrorCode = styled.h1`
  font-size: 80px;
  font-weight: 900;
  color: #ff6f61;
  margin: 0;
`;

const Message = styled.p`
  font-size: 20px;
  color: #555;
  margin: 20px 0 40px;
`;

const HomeButton = styled.button`
  padding: 15px 30px;
  background-color: #333;
  color: white;
  border: none;
  border-radius: 30px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  transition: background 0.2s;

  &:hover {
    background-color: #555;
  }
`;

const NotFound = () => {
  const navigate = useNavigate();

  return (
    <Container>
      <ErrorCode>404</ErrorCode>
      <h2>페이지를 찾을 수 없습니다.</h2>
      <Message>
        요청하신 페이지가 사라졌거나,<br />
        잘못된 경로로 접근하셨습니다.
      </Message>
      
      {/* 홈으로 이동 버튼 */}
      <HomeButton onClick={() => navigate('/')}>
        홈으로 돌아가기
      </HomeButton>
    </Container>
  );
};

export default NotFound;