import React from 'react';
import { 
    HomeContainer, 
    Title, 
    StatsContainer, 
    StatCard, 
    StatNumber, 
    StatLabel,
    CategorySection,
    SubTitle,
    CategoryItem,
    CategoryName,
    Badge
} from './Home.styled';
import { useTodos } from '../context/TodoContext';

const Home = () => {
  const {todos} = useTodos();

  const totalCount = todos.length;
  const completedCount = todos.filter(todo => todo.completed).length;
  const activeCount = totalCount - completedCount;

  const workCount = todos.filter(todo => todo.category === 'work').length;
  const studyCount = todos.filter(todo => todo.category === 'study').length;
  const healthCount = todos.filter(todo => todo.category === 'health').length;
  return (
    <HomeContainer>
        <Title>Dashboard</Title>

        <StatsContainer>
            <StatCard>
                <StatNumber>{totalCount}</StatNumber>
                <StatLabel>전체 할일</StatLabel>
            </StatCard>
            <StatCard>
                <StatNumber>{activeCount}</StatNumber>
                <StatLabel>미완료</StatLabel>
            </StatCard>
            <StatCard>
                <StatNumber>{completedCount}</StatNumber>
                <StatLabel>완료</StatLabel>
            </StatCard>
        </StatsContainer>

        <CategorySection>
            <SubTitle>카테고리별 할일</SubTitle>
            
            <CategoryItem>
                <CategoryName>학습</CategoryName>
                <Badge>{studyCount}</Badge>
            </CategoryItem>
            
            <CategoryItem>
                <CategoryName>업무</CategoryName>
                <Badge>{workCount}</Badge>
            </CategoryItem>
            
            <CategoryItem>
                <CategoryName>건강</CategoryName>
                <Badge>{healthCount}</Badge>
            </CategoryItem>

        </CategorySection>
    </HomeContainer>
  )
}

export default Home;