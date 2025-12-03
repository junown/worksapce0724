import React, { useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import { 
  DetailContainer, ContentWrapper, ImageArea, InfoArea, 
  CategoryBadge, ProductName, ProductPrice, MetaInfo, DescriptionBox, BackButton 
} from './ProductDetail.styled';

const ProductDetail = () => {
    const { id } = useParams();
    const { products } = useContext(ProductContext);
    const navigate = useNavigate();

    const product = products.find(p => p.id === Number(id));

    if (!product){
        return <div>상품을 찾을 수 없습니다</div>
    }

  return (
    <DetailContainer>
      <ContentWrapper>
        <ImageArea>
          {product.images && product.images.length > 0 ? (
            <img src={product.images[0]} alt={product.name} />
          ) : (
            <div style={{color: '#aaa'}}>이미지 없음</div>
          )}
        </ImageArea>

        <InfoArea>
          <div style={{display:'flex', gap:'10px'}}>
             <CategoryBadge>{product.category}</CategoryBadge>
             <CategoryBadge style={{background: product.status === '판매중' ? '#e6fcf5' : '#fff3bf', color: '#333'}}>
                {product.status}
             </CategoryBadge>
          </div>

          <ProductName>{product.name}</ProductName>
          <ProductPrice>{product.price.toLocaleString()}원</ProductPrice>

          <MetaInfo>
            <div>
              <span>판매자</span>
              <span>{product.seller}</span>
            </div>
          </MetaInfo>

          <div style={{marginBottom:'10px', fontWeight:'bold', color:'#333'}}>상품 설명</div>
          <DescriptionBox>
            {product.description || "상품 설명이 없습니다."}
          </DescriptionBox>

          <BackButton onClick={() => navigate(-1)}>← 목록으로 돌아가기</BackButton>
        </InfoArea>
      </ContentWrapper>
    </DetailContainer>
  )
}

export default ProductDetail;