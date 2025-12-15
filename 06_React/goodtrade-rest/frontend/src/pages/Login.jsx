import React, { useContext, useState } from 'react'
import { UserContext } from '../context/UserContext'
import { useNavigate } from 'react-router-dom';
import { AuthContainer, AuthForm, FullButton, InputGroup } from './Auth.styled';
import { Link } from 'react-router-dom';



const Login = () => {
    const navigate = useNavigate();
    const { login } = useContext(UserContext);

    const [id, setId] = useState('');
    const [pwd, setPwd] = useState('');

    const [isLoginSuccess, setIsLoginSuccess] = useState(false);

    const handleLogin = async () => {
        const success = await login(id, pwd);

        if(success){
            setIsLoginSuccess(true);
        }else{
            alert('아이디 또는 비밀번호가 일치하지 않습니다');
        }
    };

    if(isLoginSuccess){
            navigate('/'); 
    }
  return (
    <AuthContainer>
      <AuthForm>
        <h2>로그인</h2>
        <InputGroup>
          <label>아이디</label>
          <input 
            value={id} 
            onChange={(e) => setId(e.target.value)} 
            placeholder="아이디를 입력하세요" 
          />
        </InputGroup>
        <InputGroup>
          <label>비밀번호</label>
          <input 
            type="password" 
            value={pwd} 
            onChange={(e) => setPwd(e.target.value)} 
            placeholder="비밀번호를 입력하세요" 
            onKeyDown={(e) => e.key === 'Enter' && handleLogin()}
          />
        </InputGroup>
        <FullButton onClick={handleLogin}>로그인</FullButton>
        
        <div style={{marginTop: '10px', textAlign: 'center', fontSize: '14px'}}>
            아직 계정이 없으신가요? 
            <Link to="/signup" style={{color: '#ff6f61', marginLeft: '5px', textDecoration: 'none', fontWeight: 'bold'}}>
               회원가입
            </Link>
        </div>
      </AuthForm>
    </AuthContainer>
  )
}

export default Login