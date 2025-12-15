import styled from 'styled-components';

export const ListContainer = styled.div`
  padding-bottom: 50px;
`;

export const Title = styled.h2`
  text-align: center;
  margin-bottom: 20px;
  color: #333;
`;

export const FilterBar = styled.div`
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
  padding-bottom: 15px;
  border-bottom: 1px solid #eee;
`;

export const FilterBtn = styled.button`
  padding: 8px 16px;
  border-radius: 20px;
  border: 1px solid #ddd;
  font-size: 14px;
  cursor: pointer;
  white-space: nowrap;
  transition: all 0.2s;

  background-color: ${props => props.$active ? '#333' : '#fff'};
  color: ${props => props.$active ? '#fff' : '#333'};
  border-color: ${props => props.$active ? '#333' : '#ddd'};
  font-weight: ${props => props.$active ? 'bold' : 'normal'};

  &:hover {
    background-color: ${props => props.$active ? '#333' : '#f8f9fa'};
  }
`;

export const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 20px;
`;

export const ProductCard = styled.div`
  border: 1px solid #eee;
  border-radius: 10px;
  overflow: hidden;
  cursor: pointer;
  transition: transform 0.2s;
  background: #fff;

  &:hover { transform: translateY(-5px); box-shadow: 0 5px 15px rgba(0,0,0,0.1); }

  img { width: 100%; height: 200px; object-fit: cover; background: #f8f9fa; }
  
  .info { padding: 15px; }
  
  .status { 
    font-size: 11px; padding: 4px 8px; border-radius: 4px; 
    background: #eee; color: #555; display: inline-block; margin-bottom: 8px;
    
    &.판매중 { background: #e6fcf5; color: #0ca678; }
    &.예약중 { background: #fff3bf; color: #f08c00; }
    &.판매완료 { background: #f1f3f5; color: #868e96; text-decoration: line-through; }
  }

  h4 { margin: 0 0 5px; font-size: 16px; font-weight: normal; }
  .price { font-weight: bold; font-size: 18px; color: #333; }
`;