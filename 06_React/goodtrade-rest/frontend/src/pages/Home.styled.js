import styled from 'styled-components';

export const HomeContainer = styled.div`
  padding-bottom: 50px;
`;

export const StatBox = styled.div`
  background: #f1f3f5;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 30px;
  display: flex;
  justify-content: space-around;
  font-weight: bold;
  color: #495057;

  div {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 5px;
    
    span:first-child { font-size: 14px; color: #868e96; }
    span:last-child { font-size: 20px; color: #333; }
  }
`;

export const Section = styled.section`
  margin-bottom: 40px;

  h3 {
    font-size: 20px;
    margin-bottom: 15px;
    font-weight: 700;
    color: #333;
  }
`;

export const ScrollWrapper = styled.div`
  display: flex;
  gap: 15px;
  overflow-x: auto;
  padding-bottom: 10px;

  &::-webkit-scrollbar {
    height: 8px;
  }
  &::-webkit-scrollbar-thumb {
    background: #ced4da;
    border-radius: 10px;
  }
  &::-webkit-scrollbar-track {
    background: #f8f9fa;
  }
`;

export const ProductCard = styled.div`
  min-width: 200px;
  border: 1px solid #eee;
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  transition: transform 0.2s;
  background: #fff;

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px rgba(0,0,0,0.1);
  }

  img {
    width: 100%;
    height: 150px;
    object-fit: cover;
    background: #eee;
  }

  .info {
    padding: 15px;
    
    .status {
      font-size: 12px;
      padding: 3px 6px;
      border-radius: 4px;
      background: #eee;
      color: #555;
      margin-right: 5px;
      
      &.판매중 { background: #e6fcf5; color: #0ca678; }
      &.예약중 { background: #fff3bf; color: #f08c00; }
      &.판매완료 { background: #f1f3f5; color: #868e96; text-decoration: line-through; }
    }

    h4 {
      margin: 10px 0 5px;
      font-size: 16px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }

    .price {
      font-weight: bold;
      font-size: 18px;
      color: #ff6f61;
    }
  }
`;