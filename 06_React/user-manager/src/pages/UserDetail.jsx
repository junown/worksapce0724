import React, { useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { UserContext } from '../context/UserContext';

const Container = styled.div`
  padding: 40px;
  max-width: 500px;
  margin: 0;
  border: 1px solid #ddd;
  border-radius: 10px;
  text-align: center;
`;

const InfoText = styled.p`
  margin: 10px 0;
  color: #555;
`;

const ButtonGroup = styled.div`
  margin-top: 30px;
  display: flex;
  justify-content: center;
  gap: 10px;
`;

const Button = styled.button`
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  font-weight: bold;
  
  background-color: ${props => props.color === 'red' ? '#ff4d4d' : '#ddd'};
  color: ${props => props.color === 'red' ? 'white' : 'black'};
`;

const UserDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { users, deleteUser } = useContext(UserContext);
  const user = users.find(u => u.id === Number(id));

  if (!user) {
    return (
      <Container>
        <h3>존재하지 않는 유저입니다.</h3>
        <Button onClick={() => navigate('/')}>목록으로 돌아가기</Button>
      </Container>
    );
  }

  const handleDelete = () => {
    if (window.confirm("정말 이 유저를 삭제하시겠습니까?")) {
      deleteUser(user.id);
      navigate('/');
    }
  };

  return (
    <Container>
      <h1>👤 {user.name}님의 정보</h1>
      <hr style={{ margin: '20px 0', border: '0', borderTop: '1px solid #eee' }} />
      
      <InfoText>나이: {user.age}세</InfoText>
      <InfoText>
        현재 상태: 
        <span style={{ color: user.status === 'online' ? 'green' : 'red', fontWeight: 'bold' }}>
           {user.status === 'online' ? ' 온라인' : ' 오프라인'}
        </span>
      </InfoText>

      <ButtonGroup>
        <Button onClick={() => navigate(-1)}>뒤로 가기</Button>
        <Button color="red" onClick={handleDelete}>삭제 하기</Button>
      </ButtonGroup>
    </Container>
  );
};

export default UserDetail;