import React from 'react'
import { createContext, useState } from 'react'

export const UserContext = createContext();

export function UserProvider({children}){
    const [users, setUsers] = useState([
    { id : 1, name: "박준언", age: 25, status: "online"},
    { id : 2, name: "상준언", age: 35, status: "offline"},
    { id : 3, name: "김준언", age: 30, status: "online"},
  ])

  const addUser = (newUser) => {
    setUsers([...users, {...newUser, id: Date.now()}]);
  }

  const deleteUser = (id) => {
    setUsers(users.filter(user => user.id !== Number(id)));
  }

  return (
    <UserContext.Provider value={{users, addUser, deleteUser}}>
        {children}
    </UserContext.Provider>
  );
}

export default UserContext