import React, { useState, useContext } from 'react';
import styled from 'styled-components';
import { TodoContext } from '../context/TodoContext';
import { useNavigate } from 'react-router-dom';

const Container = styled.div`
  max-width: 800px;
  margin: 0 auto;
  padding: 40px 20px;
`;

const Title = styled.h2`
  text-align: center;
  margin-bottom: 40px;
  color: #333;
`;

const InputForm = styled.form`
  display: flex;
  gap: 10px;
  margin-bottom: 30px;
  height: 40px;
`;

const Input = styled.input`
  flex: 1;
  padding: 0 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  outline: none;
`;

const Select = styled.select`
  padding: 0 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  min-width: 80px;
`;

const Button = styled.button`
  padding: 0 20px;
  background: #333;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  
`;

const FilterWrap = styled.div`
  display: flex;
  justify-content: center;
  gap: 8px;
  margin-bottom: 20px;
`;

const FilterBtn = styled.button`
  padding: 6px 12px;
  border: 1px solid #ddd;
  background: ${props => props.$active ? '#333' : '#fff'};
  color: ${props => props.$active ? '#fff' : '#333'};
  border-radius: 20px;
  cursor: pointer;
  font-size: 14px;

`;

const List = styled.ul`
  list-style: none;
  padding: 0;
  border-top: 1px solid #eee;
`;

const Item = styled.li`
  display: flex;
  align-items: center;
  padding: 15px 10px;
  border-bottom: 1px solid #eee;
  color: ${props => props.$completed ? '#aaa' : '#333'};
`;

const Content = styled.span`
  flex: 1;
  cursor: pointer;
  margin: 0 10px;
  text-decoration: ${props => props.$completed ? 'line-through' : 'none'};
`;

const Badge = styled.span`
  font-size: 12px;
  padding: 2px 6px;
  border-radius: 4px;
  background: #eee;
  color: #666;
  margin-right: 8px;
`;

const DeleteBtn = styled.button`
  background: none;
  border: 1px solid #ff4d4f;
  color: #ff4d4f;
  border-radius: 4px;
  padding: 4px 8px;
  cursor: pointer;
  font-size: 12px;
`;

const TodoListPage = () => {
  const { todos, addTodo, deleteTodo, toggleTodo } = useContext(TodoContext);
  const navigate = useNavigate();

  const [text, setText] = useState('');
  const [category, setCategory] = useState('personal');
  const [filter, setFilter] = useState('all');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!text.trim()) return;
    addTodo(text, category);
    setText('');
  };

  const filteredTodos = todos.filter((todo) => {
    if (filter === 'active') return !todo.completed;
    if (filter === 'completed') return todo.completed;
    return true;
  });

  const getCategoryName = (cat) => {
    if (cat === 'work') return '업무';
    if (cat === 'study') return '공부';
    return '개인';
  };

  return (
    <Container>
      <h1>TODO-LIST</h1>

      <InputForm onSubmit={handleSubmit}>
        <Input 
          type="text" 
          value={text} 
          onChange={(e) => setText(e.target.value)}
          placeholder="할 일을 입력하세요"
        />
        <Select value={category} onChange={(e) => setCategory(e.target.value)}>
          <option value="personal">개인</option>
          <option value="work">업무</option>
          <option value="study">공부</option>
        </Select>
        <Button type="submit">등록</Button>
      </InputForm>

      <FilterWrap>
        {['all', 'active', 'completed'].map((type) => (
          <FilterBtn 
            key={type}
            $active={filter === type} 
            onClick={() => setFilter(type)}
          >
            {type === 'all' ? '전체' : type === 'active' ? '진행중' : '완료'}
          </FilterBtn>
        ))}
      </FilterWrap>

      <List>
        {filteredTodos.length === 0 ? (
          <li style={{ textAlign: 'center', padding: '20px', color: '#999' }}>
            등록된 할 일이 없습니다.
          </li>
        ) : (
          filteredTodos.map((todo) => (
            <Item key={todo.id} $completed={todo.completed}>
              <input 
                type="checkbox" 
                checked={todo.completed} 
                onChange={() => toggleTodo(todo.id)}
                style={{ cursor: 'pointer' }}
              />
              <Content 
                $completed={todo.completed}
                onClick={() => navigate(`/todos/${todo.id}`)}
              >
                <Badge>{getCategoryName(todo.category)}</Badge>
                {todo.text}
              </Content>
              <DeleteBtn onClick={() => deleteTodo(todo.id)}>삭제</DeleteBtn>
            </Item>
          ))
        )}
      </List>
    </Container>
  );
};

export default TodoListPage;