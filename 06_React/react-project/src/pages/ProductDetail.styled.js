import styled from 'styled-components';

export const DetailContainer = styled.div`
  max-width: 1000px;
  margin: 0 auto;
  padding: 50px 20px;
`;

export const ContentWrapper = styled.div`
  display: flex;
  gap: 50px;
  `;

export const ImageArea = styled.div`
  flex: 1; 
  background-color: #f8f9fa;
  border-radius: 12px;
  overflow: hidden;
  height: 500px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #eee;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

export const InfoArea = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
`;

export const CategoryBadge = styled.span`
  display: inline-block;
  background-color: #f1f3f5;
  color: #868e96;
  padding: 6px 12px;
  border-radius: 20px;
  font-size: 14px;
  font-weight: 600;
  width: fit-content;
  margin-bottom: 15px;
`;

export const ProductName = styled.h1`
  font-size: 28px;
  margin: 0 0 15px 0;
  color: #333;
  line-height: 1.4;

  input {
    font-size: inherit;
    font-weight: inherit;
    width: 100%;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 5px;
  }
`;

export const ProductPrice = styled.div`
  font-size: 32px;
  font-weight: 800;
  color: #333;
  margin-bottom: 30px;
  padding-bottom: 30px;
  border-bottom: 1px solid #eee; 

  input {
    font-size: inherit; 
    font-weight: inherit;
    width: 200px;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 5px;
  }
`;

export const MetaInfo = styled.div`
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-bottom: 30px;

  div {
    display: flex;
    font-size: 16px;
    
    span:first-child { 
      color: #999;
      width: 80px;
      font-weight: 500;
    }
    span:last-child { 
      color: #333;
      font-weight: 600;
    }
  }
`;

export const DescriptionBox = styled.div`
  background-color: #fafafa;
  padding: 20px;
  border-radius: 8px;
  min-height: 150px;
  font-size: 16px;
  line-height: 1.6;
  color: #555;
  white-space: pre-wrap; 

  textarea {
    width: 100%;
    height: 150px; 
    font-size: inherit;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    resize: none;
    background: white; 
  }
`;

export const BackButton = styled.button`
  margin-top: 20px;
  padding: 10px 20px;
  background: white;
  border: 1px solid #ddd;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  &:hover { background: #f8f9fa; }
`;

export const EditSelect = styled.select`
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ddd;
  margin-bottom: 10px;
`;

export const ButtonGroup = styled.div`
  display: flex;
  gap: 10px;
  margin-top: 20px;

  button {
    padding: 10px 20px;
    border-radius: 5px;
    font-weight: bold;
    cursor: pointer;
    border: none;
  }

  .edit-btn { background: #339af0; color: white; }
  .delete-btn { background: #fa5252; color: white; }
  .save-btn { background: #40c057; color: white; }
  .cancel-btn { background: #adb5bd; color: white; }
`;