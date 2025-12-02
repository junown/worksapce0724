import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const HeaderWrapper = styled.header`
  display: flex;
  flex-direction: column;
  border-bottom: 2px solid #eee;
  background-color: #fff;
  position: sticky;
  top: 0;
  z-index: 100;
`;

export const TopBar = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px;
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
  box-sizing: border-box;
`;

export const Logo = styled(Link)`
  font-size: 24px;
  font-weight: 900;
  text-decoration: none;
  color: #ff6f61;
  display: flex;
  align-items: center;
  gap: 10px;

  img {
    width: 35px;
    height: 35px;
    border-radius: 5px;
  }
`;

export const SearchInput = styled.input`
  width: 400px;
  padding: 12px 20px;
  border: 1px solid #ddd;
  border-radius: 25px;
  background-color: #f9f9f9;
  outline: none;
  transition: border 0.3s;

  &:focus {
    border-color: #ff6f61;
    background-color: #fff;
  }
`;

export const AuthGroup = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;

  span {
    font-size: 14px;
    font-weight: bold;
    color: #333;
    margin-right: 5px;
  }
`;

export const LinkButton = styled(Link)`
  padding: 8px 16px;
  border-radius: 6px;
  font-size: 13px;
  font-weight: bold;
  text-decoration: none;
  cursor: pointer;
  
  background-color: ${props => props.$primary ? '#ff6f61' : '#fff'};
  color: ${props => props.$primary ? '#fff' : '#333'};
  border: 1px solid ${props => props.$primary ? '#ff6f61' : '#ddd'};

  &:hover {
    opacity: 0.9;
    background-color: ${props => props.$primary ? '#e85a4f' : '#f0f0f0'};
  }
`;

export const ActionButton = styled.button`
  padding: 8px 16px;
  border-radius: 6px;
  font-size: 13px;
  font-weight: bold;
  background-color: #fff;
  color: #333;
  border: 1px solid #ddd;
  cursor: pointer;

  &:hover {
    background-color: #f0f0f0;
  }
`;

export const CategoryNav = styled.nav`
  border-top: 1px solid #f0f0f0;
  display: flex;
  justify-content: center;
  gap: 40px;
  padding: 15px 0;
  background-color: #fff;
`;

export const CategoryLink = styled(Link)`
  text-decoration: none;
  color: #333;
  font-size: 16px;
  font-weight: 600;
  padding-bottom: 5px;
  
  &:hover {
    color: #ff6f61;
    border-bottom: 2px solid #ff6f61;
  }
`;