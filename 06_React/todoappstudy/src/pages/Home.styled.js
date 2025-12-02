import styled from 'styled-components';

export const HomeContainer = styled.div`
  width: 100%;
  min-height: 100vh;
  background-color: #f0f2f5; 
  padding: 40px 20px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const Title = styled.h1`
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 40px;
  font-weight: 700;
  text-align: center;
`;

export const StatsContainer = styled.div`
  display: flex;
  justify-content: center;
  gap: 20px;
  width: 100%;
  max-width: 800px; 
  margin-bottom: 30px;

  @media (max-width: 768px) {
    flex-direction: column; 
  }
`;

export const StatCard = styled.div`
  background-color: white;
  flex: 1; 
  padding: 30px 20px;
  border-radius: 15px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-width: 150px;
`;

export const StatNumber = styled.span`
  font-size: 2.5rem;
  font-weight: bold;
  color: #5c85d6; 
  margin-bottom: 10px;
`;

export const StatLabel = styled.span`
  font-size: 1rem;
  color: #666;
  font-weight: 500;
`;

export const CategorySection = styled.div`
  background-color: white;
  width: 100%;
  max-width: 800px;
  border-radius: 15px;
  padding: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  box-sizing: border-box;
`;

export const SubTitle = styled.h2`
  font-size: 1.2rem;
  color: #333;
  text-align: center;
  margin-bottom: 30px;
  font-weight: 600;
`;

export const CategoryItem = styled.div`
  display: flex;
  justify-content: space-between; 
  align-items: center;
  padding: 15px 20px;
  margin-bottom: 10px;
  background-color: #f8f9fa; 
  border-radius: 10px;
  
  &:last-child {
    margin-bottom: 0;
  }
`;

export const CategoryName = styled.span`
  font-size: 1rem;
  color: #333;
  font-weight: 600;
`;

export const Badge = styled.div`
  background-color: #5c85d6;
  color: white;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  font-size: 0.9rem;
`;