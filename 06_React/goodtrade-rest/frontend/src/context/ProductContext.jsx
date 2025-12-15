import React, { createContext, useEffect, useState } from "react";
import axios from 'axios';

export const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);

  // 초기 데이터 로드
  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    try {
      const response = await axios.get('/api/products');
      setProducts(response.data);
    } catch (error) {
      console.error("상품 목록 로드 에러:", error);
      setProducts([]);
    } finally {
      setLoading(false);
    }
  };

  const addProduct = async (newProduct) => {
    try {
      const productData = {
        name: newProduct.name,
        price: newProduct.price,
        category: newProduct.category,
        description: newProduct.description || '',
        images: newProduct.images || []
      };

      const response = await axios.post(`/api/products?sellerId=${newProduct.seller}`, productData);
      
      // 성공 시 목록 새로고침
      await loadProducts();
      return { success: true, data: response.data };
    } catch (error) {
      console.error("상품 등록 에러:", error);
      const errorMsg = error.response?.data || "상품 등록 실패";
      return { success: false, message: errorMsg };
    }
  };

  const updateProduct = async (updateProduct) => {
    try {
      const productData = {
        name: updateProduct.name,
        price: updateProduct.price,
        category: updateProduct.category,
        status: updateProduct.status,
        description: updateProduct.description || '',
        images: updateProduct.images || []
      };

      const response = await axios.put(
        `/api/products/${updateProduct.id}?sellerId=${updateProduct.seller}`,
        productData
      );
      
      // 성공 시 목록 새로고침
      await loadProducts();
      return { success: true, data: response.data };
    } catch (error) {
      console.error("상품 수정 에러:", error);
      const errorMsg = error.response?.data || "상품 수정 실패";
      return { success: false, message: errorMsg };
    }
  };

  const deleteProduct = async (id, sellerId) => {
    try {
      await axios.delete(`/api/products/${id}?sellerId=${sellerId}`);
      
      // 성공 시 목록 새로고침
      await loadProducts();
      return { success: true };
    } catch (error) {
      console.error("상품 삭제 에러:", error);
      const errorMsg = error.response?.data || "상품 삭제 실패";
      return { success: false, message: errorMsg };
    }
  };

  return (
    <ProductContext.Provider value={{ products, loading, addProduct, updateProduct, deleteProduct, loadProducts }}>
      {children}
    </ProductContext.Provider>
  );
};