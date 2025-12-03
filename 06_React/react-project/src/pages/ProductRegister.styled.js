import styled from 'styled-components';

export const RegisterContainer = styled.div`
  max-width: 600px;
  margin: 0 auto;
  padding: 50px 20px;
`;

export const Title = styled.h2`
  text-align: center;
  margin-bottom: 40px;
  color: #333;
  font-size: 28px;
`;

export const RegisterForm = styled.div`
  display: flex;
  flex-direction: column;
  gap: 25px;
  background: #fff;
  padding: 30px;
  border: 1px solid #eee;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0,0,0,0.05);
`;

export const InputGroup = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;

  label {
    font-weight: 700;
    font-size: 15px;
    color: #495057;
  }

  input, select, textarea {
    padding: 12px;
    border: 1px solid #ddd;
    border-radius: 8px;
    font-size: 16px;
    outline: none;
    transition: border-color 0.2s;

    &:focus {
      border-color: #ff6f61;
    }
  }

  textarea {
    height: 150px;
    resize: none;
    line-height: 1.5;
  }
`;

export const SubmitButton = styled.button`
  background-color: #ff6f61;
  color: white;
  padding: 15px;
  border: none;
  border-radius: 8px;
  font-size: 18px;
  font-weight: bold;
  cursor: pointer;
  margin-top: 10px;
  transition: background-color 0.2s;

  &:hover {
    background-color: #e85a4f;
  }
`;