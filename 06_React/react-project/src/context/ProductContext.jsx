import React, { createContext, useState } from "react";

export const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
  const [products, setProducts] = useState([
    { id: 1, name: '맥북 프로 16인치', price: 2500000, category: '전자기기', status: '판매중', seller: 'admin', images: [] },
    { id: 2, name: '아이폰 14 미니', price: 850000, category: '전자기기', status: '예약중', seller: 'user1', images: [] },
    { id: 3, name: '겨울 롱코트', price: 120000, category: '패션', status: '판매완료', seller: 'user2', images: [] },
    { id: 4, name: '나이키 운동화', price: 90000, category: '패션', status: '판매중', seller: 'user1', images: [] },
    { id: 5, name: '입생로랑 틴트', price: 35000, category: '뷰티', status: '판매중', seller: 'user3', images: [] },
    { id: 6, name: '수분크림 대용량', price: 25000, category: '뷰티', status: '예약중', seller: 'admin', images: [] },
  ]);

  const addProduct = (newProduct) => {
    const productWithId = {
      ...newProduct,
      id: Date.now(),
      status: '판매중'
    };
    setProducts([productWithId, ...products]);
  };

  return (
    <ProductContext.Provider value={{ products, addProduct }}>
      {children}
    </ProductContext.Provider>
  );
};