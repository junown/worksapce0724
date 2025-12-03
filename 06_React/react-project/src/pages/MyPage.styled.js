import styled from 'styled-components';

export const MyPageContainer = styled.div`
  display: flex;
  justify-content: center;
  padding: 50px 20px;
`;

export const ProfileCard = styled.div`
  width: 100%;
  max-width: 500px;
  background: white;
  border-radius: 15px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
  padding: 40px;
  display: flex;
  flex-direction: column;
  gap: 25px; /* 간격 조금 넓힘 */

  h2 {
    text-align: center;
    color: #333;
    margin-bottom: 10px;
    padding-bottom: 15px;
    border-bottom: 2px solid #f8f9fa;
    font-size: 24px;
  }
`;

export const InfoRow = styled.div`
  display: flex;
  flex-direction: column; 
  gap: 8px;

  label {
    font-weight: 700;
    font-size: 14px;
    color: #555;
    margin-left: 5px;
  }

  input {
    padding: 12px 15px;
    border: 1px solid #ddd;
    border-radius: 8px;
    font-size: 16px;
    outline: none;
    transition: all 0.2s;

    &:focus {
      border-color: #ff6f61;
      box-shadow: 0 0 0 3px rgba(255, 111, 97, 0.1);
    }
    
    &:disabled {
      background: #f1f3f5;
      color: #868e96;
      cursor: not-allowed;
    }
  }
`;

export const ButtonGroup = styled.div`
  display: flex;
  flex-direction: column; 
  gap: 15px;
  margin-top: 20px;

  button {
    padding: 15px;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;
    border: none;
    transition: all 0.2s;
  }

  .save-btn {
    background: #ff6f61;
    color: white;
    &:hover { background: #e85a4f; }
  }

  .delete-btn {
    background: #f8f9fa;
    color: #fa5252;
    border: 1px solid #fa5252;
    &:hover {
      background: #fa5252;
      color: white;
    }
  }
`;