import React, { useContext } from 'react'
import { ProductContext } from '../context/ProductContext'
import { useNavigate } from 'react-router-dom';
import { HomeContainer, ProductCard, ScrollWrapper, Section, StatBox } from './Home.styled';

const Home = () => {
  const { products } = useContext(ProductContext);
  const navigate = useNavigate();

  const total = products.length;
  const selling = products.filter(p => p.status === '판매중').length;
  const reserved = products.filter(p => p.status === '예약중').length;
  const sold = products.filter(p => p.status === '판매완료').length;

  const renderCategorySection = (CategoryName) => {
    const categoryProducts = products.filter(p => p.category === CategoryName);

    if(categoryProducts.length === 0) return null;

    return (
      <Section>
        <h3>{CategoryName}</h3>
        <ScrollWrapper>
          {categoryProducts.map((product) => (
            <ProductCard key={product.id} onClick={() => navigate(`/product/${product.id}`)}>
              {product.images && product.images.length > 0 ? (
                <img src={product.images[0]} alt={product.name} />
              ) : (
                <div style={{width:'100%', height:'150px', background:'#ddd', display:'flex', alignItems:'center', justifyContent:'center'}}>
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
        </ScrollWrapper>
      </Section>
    )
  }
  return (
    <HomeContainer>
      <StatBox>
        <div><span>총 물품</span><span>{total}개</span></div>
        <div><span>판매중</span><span>{selling}개</span></div>
        <div><span>예약중</span><span>{reserved}개</span></div>
        <div><span>판매완료</span><span>{sold}개</span></div>
      </StatBox>

      {renderCategorySection('전자기기')}
      {renderCategorySection('패션')}
      {renderCategorySection('뷰티')}
      
      {products.length === 0 && (
        <div style={{textAlign: 'center', padding: '50px', color: '#888'}}>
          등록된 상품이 없습니다. <br />
          로그인 후 첫 상품을 등록해보세요!
        </div>
      )}
    </HomeContainer>
  )
}

export default Home