import React, { useContext } from 'react'
import TodoContext from '../context/TodoContext'
import { useNavigate } from 'react-router-dom';

const Home = () => {
  const {todos} = useContext(TodoContext);
  const navigate = useNavigate();

  const totalCount = todos.length;
  const completedCount = todos.filter(todo => todo.completed).length;
  const activeCount = totalCount - completedCount;

  const workCount = todos.filter(todo => todo.category === 'work').length;
  const personalCount = todos.filter(todo => todo.category === 'personal').length;
  const studyCount = todos.filter(todo => todo.category === 'study').length;
  
  const cardStyle = {
    border: '1px solid #ddd',
    padding: '20px',
    borderRadius: '8px',
    width: '30%',
    textAlign: 'center',
    cursor: 'pointer',
    color: '#333',
    backgroundColor: '#f9f9f9'
  };
  
    return (
    <div style={{padding: '20px'}}>
        <h1>TODO-LIST</h1>

        <div style={{ marginBottom: '30px', padding: '20px', border: '1px solid #ccc' }}>
            <h2>전체 현황</h2>
            <p>전체 할 일 : {totalCount}개</p>
            <p>진행중 : {activeCount}개</p>
            <p>완료 : {completedCount}개</p>
        </div>

        <h2>카테고리별 보기</h2>
        <div style={{display: 'flex', gap: '20px'}} >
        <div style={cardStyle} onClick={() => navigate('category/work')}>
            <h3>업무(Work)</h3>
            <p>{workCount}개</p>
        </div>
        <div style={cardStyle} onClick={() => navigate('category/work')}>
            <h3>개인업무(Personal)</h3>
            <p>{personalCount}개</p>
        </div>
        <div style={cardStyle} onClick={() => navigate('category/work')}>
            <h3>공부(Study)</h3>
            <p>{studyCount}개</p>
            </div>
        </div>
    </div>
  )
}

export default Home