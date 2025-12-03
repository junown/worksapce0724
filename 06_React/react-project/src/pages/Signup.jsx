import React, { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../context/UserContext';
import { AuthContainer, AuthForm, InputGroup, FullButton } from './Auth.styled';

const Signup = () => {
    const { signup } = useContext(UserContext);
    
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        id: '',
        pwd: '',
        name: '',
        address: '',
        age: '' 
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = () => {
        if (!formData.id || !formData.pwd || !formData.name) {
            alert("필수 정보를 입력해주세요");
            return; 
        }

        const success = signup(formData);

        if (success) {
            alert('회원가입 성공');
            navigate('/login'); 
            
        } else {
            alert('이미 존재하는 아이디입니다');
        }
    };

    return (
        <AuthContainer>
            <AuthForm>
                <h2>회원가입</h2>
                <InputGroup>
                    <label>아이디</label>
                    <input name="id" value={formData.id} onChange={handleChange} />
                </InputGroup>
                <InputGroup>
                    <label>비밀번호</label>
                    <input type="password" name="pwd" value={formData.pwd} onChange={handleChange} />
                </InputGroup>
                <InputGroup>
                    <label>이름</label>
                    <input name="name" value={formData.name} onChange={handleChange} />
                </InputGroup>
                <InputGroup>
                    <label>주소</label>
                    <input name="address" value={formData.address} onChange={handleChange} />
                </InputGroup>
                <InputGroup>
                    <label>나이</label>
                    <input type="number" name="age" value={formData.age} onChange={handleChange} />
                </InputGroup>
                
                <FullButton onClick={handleSubmit}>가입하기</FullButton>
            </AuthForm>
        </AuthContainer>
    );
};

export default Signup;