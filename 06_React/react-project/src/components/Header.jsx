import React, { useContext } from 'react';
import { DataContext } from '../context/DataContext';

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

const Header = () => {
  const { user, logout } = useContext(DataContext);

  return (
    <HeaderWrapper>
      <TopBar>
        <Logo to="/">
          <img src="https://cdn-icons-png.flaticon.com/512/2331/2331970.png" />
          굿거래
        </Logo>

        <SearchInput type="text" placeholder="상품명 입력" />

        <AuthGroup>
          {user ? (
            <>
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
        <CategoryLink to="/category/fashion">패션</CategoryLink>
        <CategoryLink to="/category/electronics">전자기기</CategoryLink>
        <CategoryLink to="/category/beauty">뷰티</CategoryLink>
      </CategoryNav>
    </HeaderWrapper>
  );
};

export default Header;