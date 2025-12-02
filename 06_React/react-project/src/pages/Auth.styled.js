import styled from 'styled-components';

export const AuthContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 50px 20px;
`;

export const AuthForm = styled.div`
  width: 100%;
  max-width: 400px;
  background: #fff;
  padding: 30px;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);

  h2 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
  }
`;

export const InputGroup = styled.div`
  margin-bottom: 15px;

  label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    font-size: 14px;
    color: #555;
  }

  input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 15px;
    box-sizing: border-box;

    &:focus {
      border-color: #ff6f61;
      outline: none;
    }
  }
`;

export const FullButton = styled.button`
  width: 100%;
  padding: 12px;
  background-color: #ff6f61;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  margin-top: 10px;

  &:hover {
    background-color: #e85a4f;
  }
`;