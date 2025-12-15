import React, { useContext, useState } from 'react';
import { UserContext } from '../context/UserContext';

import { 
  HeaderWrapper, 
  TopBar, 
  Logo, 
  SearchInput, 
  AuthGroup, 
  LinkButton, 
  ActionButton, 
  CategoryNav, 
  CategoryLink 
} from './Header.styled';
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const { user, logout } = useContext(UserContext);
  const navigate = useNavigate();

  const [text, setText] = useState('');

  const handleKeyDown = (e) => {
    if(e.key === 'Enter'){
      navigate(`/search/${text}`);
    }
  }
  return (
    <HeaderWrapper>
      <TopBar>
        <Logo to="/">
          <img src="https://cdn-icons-png.flaticon.com/512/2331/2331970.png" />
          굿거래
        </Logo>

        <SearchInput 
          type="text" 
          placeholder="상품명 입력"
          value={text}
          onChange={(e) => setText(e.target.value)}
          onKeyDown={handleKeyDown}
          />

        <AuthGroup>
          {user ? (
            <>
              <LinkButton to="/register" $primary="true" style={{marginRight: '15px'}}>
               + 판매하기
              </LinkButton>
              <span>{user.name}님</span>
              <LinkButton to="/mypage">마이페이지</LinkButton>
              <ActionButton onClick={logout}>로그아웃</ActionButton>
            </>
          ) : (
            <>
              <LinkButton to="/login">로그인</LinkButton>
              <LinkButton to="/signup" $primary="true">회원가입</LinkButton>
            </>
          )}
        </AuthGroup>
      </TopBar>

      <CategoryNav>
        <CategoryLink to="/category/패션">패션</CategoryLink>
        <CategoryLink to="/category/전자기기">전자기기</CategoryLink>
        <CategoryLink to="/category/뷰티">뷰티</CategoryLink>
      </CategoryNav>
    </HeaderWrapper>
  );
};

export default Header;