import React from 'react'
import { Link } from 'react-router-dom'
import styled from 'styled-components'
import { useContext } from 'react'
import { UserContext } from '../context/UserContext'

const Container = styled.div`
  padding: 20px;
`;

const Card = styled.div`
    border : 1px solid #ccc;
    padding : 15px;
    margin: 10px 0;
    background: ${users => users.$isOnline ? '#00ff55' : '#fff'};
`


const UserList = () => {
    const {users} = useContext(UserContext);
  return (
    <Container>
        <h2>유저 전체 리스트 ({users.length}명)</h2>

        {users.map(user => (
            <Card key = {user.id} $isOnline={user.status === 'online'}>
                <h3>
                    <Link to={`/user/${user.id}`}>{user.name} ({user.age}세)</Link>
                </h3>
                <p>상태 : {user.status === 'online'? "온라인상태" : "오프라인상태"}</p>
            </Card>
        ))}
    </Container>
  )
}

export default UserList