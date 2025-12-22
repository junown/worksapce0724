import React, { useContext, useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import { UserContext } from '../context/UserContext';
import { 
  DetailContainer, ContentWrapper, ImageArea, InfoArea, 
  CategoryBadge, ProductName, ProductPrice, MetaInfo, DescriptionBox, BackButton, ButtonGroup, EditSelect, PurchaseButton
} from './ProductDetail.styled';

const ProductDetail = () => {
    const { id } = useParams();
    const { products, updateProduct, deleteProduct, purchaseProduct } = useContext(ProductContext);
    const { user } = useContext(UserContext);
    const navigate = useNavigate();

    const product = products.find(p => p.id === Number(id));

    const [isEditing, setisEditing] = useState(false);

    const [editForm, seteditForm] = useState({
      name: '', price: '', category: '', description: '', status: ''
    });

    useEffect(() => {
      if(product){
        seteditForm(product);
      }
    },[product]);

    if (!product){
        return <div>상품을 찾을 수 없습니다</div>
    }

    const isSeller = user && user.id === product.seller;

    const handleChange =(e) => {
      seteditForm({ ...editForm, [e.target.name]: e.target.value});
    }

    const handleUpdate = async () => {
      const result = await updateProduct(editForm);
      if (result.success) {
        setisEditing(false);
        alert('상품 정보가 수정되었습니다');
      } else {
        alert(result.message || '상품 수정에 실패했습니다');
      }
    }

    const handleDelete = async () => {
      if(window.confirm('정말로 이 상품을 삭제하시겠습니까?')){
        const result = await deleteProduct(product.id, product.seller);
        if (result.success) {
          alert('상품이 삭제되었습니다');
          navigate('/');
        } else {
          alert(result.message || '상품 삭제에 실패했습니다');
        }
      }
    }

    const handlePurchase = async () => {
      if (!user) {
        alert('로그인이 필요합니다.');
        navigate('/login');
        return;
      }

      if(user.id === product.seller) {
        alert('자신의 물건은 자기가 구매불가능합니다');
        return;
      }

      if(window.confirm('정말로 이 상품을 구매하시겠습니까?')){
        const result = await purchaseProduct(product.id, user.id);

        if (result.success) {
          alert('구매가 완료되었습니다! 감사합니다');
        } else {
          alert(result.message || '구매 처리에 실패했습니다');
        }
      }
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
          {isEditing ? (
          <>
            <div style={{display:'flex', gap:'10px'}}>
              <EditSelect name="category" value={editForm.category} onChange={handleChange}>
                <option value="전자기기">전자기기</option>
                <option value="패션">패션</option>
                <option value="뷰티">뷰티</option>
              </EditSelect>
              <EditSelect name="status" value={editForm.status} onChange={handleChange}>
                <option value="판매중">판매중</option>
                <option value="예약중">예약중</option>
                <option value="판매완료">판매완료</option>
              </EditSelect>
            </div>

            <ProductName>
              <input name="name" value={editForm.name} onChange={handleChange} placeholder='상품명' />
            </ProductName>
            
            <ProductPrice>
              <input type="number" name="price" value={editForm.price} onChange={handleChange} placeholder='가격'/>
            </ProductPrice>
            
            <DescriptionBox>
              <textarea name="description" value={editForm.description} onChange={handleChange} placeholder='설명을 입력해주세요'/>
            </DescriptionBox>

            <ButtonGroup>
              <button className="save-btn" onClick={handleUpdate}>수정 완료</button>
              <button className="cancel-btn" onClick={() => setisEditing(false)}>취소</button>
            </ButtonGroup>
          </>
          ) : (
            <>
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
              <span>{product.sellerName || product.seller}</span>
            </div>
          </MetaInfo>

          <div style={{marginBottom:'10px', fontWeight:'bold', color:'#333'}}>상품 설명</div>
          <DescriptionBox>
            {product.description || "상품 설명이 없습니다."}
          </DescriptionBox>
          {isSeller ? (
            <ButtonGroup>
              <button className='edit-btn' onClick={() => setisEditing(true)}>수정하기</button>
              <button className='delete-btn' onClick={handleDelete}>삭제하기</button>
            </ButtonGroup>
          ) : (
            <PurchaseButton
              onClick={handlePurchase}
              disabled={product.status === '판매완료'}
            >
              {product.status === '판매완료' ? '품절된 상품입니다' : '구매하기'}
            </PurchaseButton>
          )}
          </>
        )}
          <BackButton onClick={() => navigate(-1)}>← 목록으로 돌아가기</BackButton>
          
        </InfoArea>
      </ContentWrapper>
    </DetailContainer>
  )
}

export default ProductDetail;