import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { UserContext } from '../context/UserContext';
import { ButtonGroup, InfoRow, MyPageContainer, ProfileCard } from './MyPage.styled';

const MyPage = () => {
  const Navigate = useNavigate();
  const { user, updateUser, deleteUser } = useContext(UserContext);

  const [formData, setFormData] = useState({
    id: user?.id || null,
    userId: user?.userId || '',
    pwd: '',
    name: user?.name || '',
    address: user?.address || '',
    age: user?.age || 0
  });

  useEffect(() => {
    if(user) {
      setFormData({
        id: user.id,
        userId: user.userId || '',
        pwd: '', // 비밀번호는 수정 시에만 입력
        name: user.name || '',
        address: user.address || '',
        age: user.age || 0
      });
    }
  },[user]);

  const handleChange = (e) => {
    const value = e.target.type === 'number' ? parseInt(e.target.value) || 0 : e.target.value;
    setFormData({ ...formData, [e.target.name] : value});
  }

  const handleSave = async () => {
    if (!formData.name || !formData.address) {
      alert('이름과 주소는 필수입니다');
      return;
    }

    const result = await updateUser(formData);
    if (result && result.success) {
      alert(result.message || '회원정보가 성공적으로 수정되었습니다');
    } else {
      alert(result?.message || '회원정보 수정에 실패했습니다');
    }
  }

  const handleDelete = async () => {
    if(!window.confirm('정말로 탈퇴하시겠습니까?')) return;

    const pwdInput = prompt('본인 확인을 위해 비밀번호를 입력해주세요');

    if(pwdInput){
      const success = await deleteUser(user.id, pwdInput);
      if(success){
        alert('탈퇴가 완료되었습니다');
        Navigate('/')
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
          <input value={formData.userId || formData.id} disabled />
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