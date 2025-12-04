import React, { useContext, useState } from 'react'; // useState 추가
import { useParams, useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';

import { 
  ListContainer, Title, GridContainer, ProductCard 
} from './CategoryList.styled';

const SearchPage = () => {
  const { keyword } = useParams();
  const { products } = useContext(ProductContext);
  const navigate = useNavigate();
  
  const searchResults = products.filter(p=> p.name.toLowerCase().includes(keyword.toLowerCase()));


  return (
    <ListContainer>
      <Title>"{keyword}" 검색결과</Title>

      {searchResults.length === 0 ? (
        <div style={{ textAlign: 'center', padding: '50px', color: '#999' }}>
          검색 결과가 없습니다.
        </div>
      ) : (
        <GridContainer>
          {searchResults.map((product) => (
            <ProductCard
              key={product.id}
              onClick={() => navigate(`/product/${product.id}`)}
            >
              {product.images && product.images.length > 0 ? (
                <img src={product.images[0]} alt={product.name} />
              ) : (
                <div style={{width:'100%', height:'200px', background:'#ddd', display:'flex', alignItems:'center', justifyContent:'center'}}>
                   이미지 없음
                </div>
              )}

              <div className="info">
                <span className={`status ${product.status}`}>{product.status}</span>
                <h4>{product.name}</h4>
                <div className="price">{product.price.toLocaleString()}원</div>
              </div>
            </ProductCard>
          ))}
        </GridContainer>
      )}
    </ListContainer>
  )
}

export default SearchPage