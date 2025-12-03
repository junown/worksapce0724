import React, { createContext, useState, useEffect } from "react";

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [userList, setUserList] = useState(() => {
    const savedUsers = localStorage.getItem('userList');
    return savedUsers ? JSON.parse(savedUsers) : [];
  });

  const [loginUser, setLoginUser] = useState(() => {
    const savedSession = localStorage.getItem('loginUser');
    return savedSession ? JSON.parse(savedSession) : null;
  });

  useEffect(() => {
    localStorage.setItem('userList', JSON.stringify(userList));
  }, [userList]);

  useEffect(() => {
    if (loginUser) {
      localStorage.setItem('loginUser', JSON.stringify(loginUser));
    } else {
      localStorage.removeItem('loginUser');
    }
  }, [loginUser]);

  const signup = (userInfo) => {
    const exists = userList.find(u => u.id === userInfo.id);
    if (exists) return false;
    
    setUserList([...userList, userInfo]);
    return true;
  };

  const login = (id, pwd) => {
    const foundUser = userList.find(u => u.id === id && u.pwd === pwd);
    if (foundUser) {
      setLoginUser(foundUser);
      return true;
    }
    return false;
  };

  const logout = () => {
    setLoginUser(null);
  };

  const updateUser = (newUserInfo) => {
    const newUserList = userList.map(u => u.id === newUserInfo.id ? newUserInfo : u);
    setUserList(newUserList);
    setLoginUser(newUserInfo);
  };

  const deleteUser = (id, pwd) => {
    if (loginUser.pwd !== pwd) return false;
    
    const newUserList = userList.filter(u => u.id !== id);
    setUserList(newUserList);
    setLoginUser(null);
    return true;
  };

  return (
    <UserContext.Provider value={{ user: loginUser, signup, login, logout, updateUser, deleteUser }}>
      {children}
    </UserContext.Provider>
  );
};