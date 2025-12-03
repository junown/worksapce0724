import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { UserContext } from '../context/UserContext';
import { ButtonGroup, InfoRow, MyPageContainer, ProfileCard } from './MyPage.styled';

const MyPage = () => {
  const Navigate = useNavigate();
  const { user, updateUser, deleteUser } = useContext(UserContext);

  const [formData, setFormData] = useState(user || {});

  useEffect(() => {
    if(user) {
      setFormData(user);
    }
  },[user]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name] : e.target.value});
  }

  const handleSave = () => {
    if (!formData.name || !formData.address) return alert('이름과 주소는 필수입니다');

    updateUser(formData);
    alert('회원정보가 성공적으로 수정되었습니다');
  }

  const handleDelete = () => {
    if(!window.confirm('정말로 탈퇴하시겠습니까?')) return;

    const pwdInput = prompt('본인 확인을 위해 비밀번호를 입력해주세요');

    if(pwdInput){
      const success = deleteUser(user.id, pwdInput);
      if(success){
        alert('탈퇴가 완료되었습니다');
        Navigate('/')
      }else{
        alert('비밀번호가 일치하지않습니다');
      }
    }
  }

  if(!user) return null;
  return (
    <MyPageContainer>
      <ProfileCard>
        <h2>내 정보 수정</h2>

        <InfoRow>
          <label>아이디 (수정불가)</label>
          <input value={formData.id} disabled />
        </InfoRow>

        <InfoRow>
          <label>비밀번호</label>
          <input 
            type="text"
            name="pwd" 
            value={formData.pwd} 
            onChange={handleChange} 
            placeholder="변경할 비밀번호 입력"
          />
        </InfoRow>

        <InfoRow>
          <label>이름</label>
          <input name="name" value={formData.name} onChange={handleChange} />
        </InfoRow>

        <InfoRow>
          <label>주소</label>
          <input name="address" value={formData.address} onChange={handleChange} />
        </InfoRow>
        
        <InfoRow>
          <label>나이</label>
          <input type="number" name="age" value={formData.age} onChange={handleChange} />
        </InfoRow>

        <ButtonGroup>
          <button className="save-btn" onClick={handleSave}>정보 수정 내용 저장하기</button>
          <button className="delete-btn" onClick={handleDelete}>회원 탈퇴하기</button>
        </ButtonGroup>

      </ProfileCard>
    </MyPageContainer>
  );
}

export default MyPage;