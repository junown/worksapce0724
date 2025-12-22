import React, { createContext, useState, useEffect } from "react";
import axios from 'axios'; 

// eslint-disable-next-line react-refresh/only-export-components
export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  

  const [user, setUser] = useState(() => {
    const savedUser = localStorage.getItem('loginUser');
    return savedUser ? JSON.parse(savedUser) : null;
  });

  useEffect(() => {
    if (user) {
      localStorage.setItem('loginUser', JSON.stringify(user));
    } else {
      localStorage.removeItem('loginUser');
    }
  }, [user]);

  const signup = async (userInfo) => {
  try {
    const response = await axios.post('/api/users', userInfo);
    
    if (response.status === 201) {
      console.log("회원가입 성공:", response.data);
      return { success: true, message: response.data };  
    } else {
      console.warn("예상치 못한 성공 응답:", response);
      return { success: false, message: "회원가입 실패" }; 
    }
  } catch (error) {
    console.error("회원가입 에러:", error);
    const errorMsg = error.response?.data || "회원가입 실패";
    
    return { success: false, message: errorMsg };
  }
};

  const login = async (id, pwd) => {
    try {
      const response = await axios.post('/api/users/login', { id, pwd });
      
      if (response.status === 200) {
        console.log("로그인 성공:", response.data);
        setUser(response.data);
        return true;
      }
    } catch (error) {
      console.error("로그인 에러:", error);
      alert(error.response?.data || "로그인 실패: 아이디나 비밀번호를 확인하세요.");
      return false;
    }
  };

  const logout = () => {
    setUser(null);
  };

  const updateUser = async (newUserInfo) => {
    try {
      const updateData = {
        pwd: newUserInfo.pwd || '',
        name: newUserInfo.name,
        address: newUserInfo.address || '',
        age: parseInt(newUserInfo.age) || 0
      };

      const response = await axios.put(`/api/users/${newUserInfo.id}`, updateData);
      
      if (response.status === 200) {
        console.log("사용자 정보 수정 성공:", response.data);
        setUser(response.data); 
        return { success: true, message: "회원정보가 성공적으로 수정되었습니다" };
      }
      return { success: false, message: "회원정보 수정 실패" };
    } catch (error) {
      console.error("사용자 정보 수정 에러:", error);
      let errorMsg = "회원정보 수정 실패";
      
      if (error.response?.data) {
        if (typeof error.response.data === 'string') {
          errorMsg = error.response.data;
        } else if (error.response.data.message) {
          errorMsg = error.response.data.message;
        } else {
          errorMsg = JSON.stringify(error.response.data);
        }
      }
      
      return { success: false, message: errorMsg };
    }
  };

  const deleteUser = async (id, pwd) => {
    try {
      const response = await axios.delete(`/api/users/${id}`, {
        data: { pwd: pwd }
      });
      
      if (response.status === 200) {
        console.log("사용자 삭제 성공:", response.data);
        setUser(null);
        return true;
      }
    } catch (error) {
      console.error("사용자 삭제 에러:", error);
      const errorMsg = error.response?.data || "회원 탈퇴 실패";
      alert(errorMsg);
      return false;
    }
  };

  return (
    <UserContext.Provider value={{ user, signup, login, logout, updateUser, deleteUser }}>
      {children}
    </UserContext.Provider>
  );
};