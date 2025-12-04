import React, { useContext, useState } from 'react'; // useState 추가
import { useParams, useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';

import { 
  ListContainer, Title, FilterBar, FilterBtn, GridContainer, ProductCard 
} from './CategoryList.styled';

const CategoryList = () => {
  const { categoryName } = useParams();
  const { products } = useContext(ProductContext);
  const navigate = useNavigate();

  const [currentFilter, setcurrentFilter] = useState('전체');

  const categoryProducts = products.filter(p=> p.category === categoryName);

  const finalProducts = categoryProducts.filter(p => {
    if (currentFilter === '전체') return true;
    return p.status === currentFilter;
  })

  return (
    <ListContainer>
      <Title>{categoryName}</Title>

      <FilterBar>
        <FilterBtn
          $active={currentFilter === '전체'}
          onClick={() => setcurrentFilter('전체')}
        >
          전체
        </FilterBtn>
        <FilterBtn
          $active={currentFilter === '판매중'}
          onClick={() => setcurrentFilter('판매중')}
        >
          판매중
        </FilterBtn>
        <FilterBtn
          $active={currentFilter === '예약중'}
          onClick={() => setcurrentFilter('예약중')}
        >
          예약중
        </FilterBtn>
        <FilterBtn
          $active={currentFilter === '판매완료'}
          onClick={() => setcurrentFilter('판매완료')}
        >
          판매완료
        </FilterBtn>
      </FilterBar>

      {finalProducts.length === 0 ? (
        <div style={{textAlign: 'center', padding: '50px', color: '#999'}}>
          해당하는 상품이 없습니다
        </div>
      ) : (
        <GridContainer>
          {finalProducts.map((product) => (
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

export default CategoryList