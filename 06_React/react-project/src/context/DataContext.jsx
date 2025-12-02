import { createContext, useEffect, useState } from "react";

export const DataContext = createContext();

export const DataProvider = ({ children }) => {
  const [userList, setUserList] = useState(() => {
    const savedUsers = localStorage.getItem('userList');
    return savedUsers ? JSON.parse(savedUsers) : [];
  });

  const [loginUser, setLoginUser] = useState(() => {
    const savedSession = localStorage.getItem('loginUser');
    return savedSession ? JSON.parse(savedSession) : null;
  })

    useEffect(() => {
        localStorage.setItem('userList', JSON.stringify(userList));
    }, [userList])

    useEffect(() => {
        if(loginUser){
            localStorage.setItem('currentUser', JSON.stringify(loginUser));
        }else{
            localStorage.removeItem('loginUser');
        }
    }, [loginUser]);

    const signup = (userInfo) => {
        const exists = userList.find(u => u.id === userInfo.id);
        if(exists) {
            return false;
        }else{
            setUserList([...userList, userInfo]);
            return true;
        }
    }

    const login = (id, pwd) => {
        const foundUser = userList.find(u => u.id === id && u.pwd === pwd);
        if (foundUser){
            setLoginUser(foundUser);
            return true;
        }else{
            return false;
        }
    };

    const logout = () => {
        setLoginUser(null);
    }

    const [products, setProducts] = useState([]);

    return (
        <DataContext.Provider value={{
            user: loginUser,
            signup,
            login,
            logout,
            products
        }}>
            {children}
        </DataContext.Provider>
    )
}