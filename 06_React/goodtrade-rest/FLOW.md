# 🔄 프로젝트 동작 흐름 정리

## 1) 전체 아키텍처 한눈에 보기
```
[사용자 브라우저]
    ↓ (라우팅)
React (frontend)
    ↓ (axios 요청: /api/...)
Vite Dev Server
    ↓ (proxy: /api -> http://localhost:8080)
Spring Boot (backend)
    ↓
Controller → Service → Repository → DB(H2)
```

## 2) 페이지 로딩 시 초기 데이터 흐름 (상품 목록)
```
App.jsx 로드
  → ProductProvider 마운트
    → useEffect()에서 loadProducts()
      → axios.get('/api/products')
        → Vite proxy로 백엔드 전달
          → ProductController.getAll()
            → ProductService.getAll()
              → ProductRepository.findAll()  // SELECT * FROM PRODUCT
            ← 상품 리스트 반환
        ← 응답을 products state에 저장
  → 화면에 상품 목록 렌더링
```

## 3) 회원 관련 흐름
### 회원가입 (Signup.jsx → UserContext.signup)
```
axios.post('/api/users', {id, pwd, name, address, age})
  → MemberController.signup()
    → MemberService.signup()
      → MemberRepository.save()
  ← 201 Created / 에러 메시지
```

### 로그인 (Login.jsx → UserContext.login)
```
axios.post('/api/users/login', {id, pwd})
  → MemberController.login()
    → MemberService.login()
      → MemberRepository.findByUserId()
  ← 200 OK: 사용자 정보 → setUser → localStorage 저장
```

### 마이페이지 수정/삭제 (MyPage.jsx → UserContext.updateUser/deleteUser)
```
PUT /api/users/{id}
DELETE /api/users/{id}
  → MemberController.update/delete
    → MemberService.update/delete
      → MemberRepository 호출 (findById, save, delete)
```

## 4) 상품 관련 흐름
### 등록 (ProductRegister.jsx → ProductContext.addProduct)
```
axios.post('/api/products?sellerId={user.id}', productData)
  → ProductController.create()
    → ProductService.create()
      → ProductRepository.save()  // INSERT
  ← 생성된 상품
  → loadProducts() 재호출로 목록 갱신
```

### 수정 (ProductDetail.jsx → ProductContext.updateProduct)
```
axios.put('/api/products/{id}?sellerId={seller}', productData)
  → ProductController.update()
    → ProductService.update()
      → ProductRepository.findById() + save()  // UPDATE
  ← 수정된 상품
  → loadProducts() 재호출
```

### 삭제 (ProductDetail.jsx → ProductContext.deleteProduct)
```
axios.delete('/api/products/{id}?sellerId={seller}')
  → ProductController.delete()
    → ProductService.delete()
      → ProductRepository.delete()  // DELETE
  ← 성공 메시지
  → loadProducts() 재호출
```

### 상태 변경/구매 (ProductDetail.jsx → ProductContext.updateProduct)
```
axios.put('/api/products/{id}?sellerId={seller}', { status: '판매완료', ... })
  → ProductController.update()
    → ProductService.updateStatus() 또는 update()
      → ProductRepository.save()
```

### 조회/검색/필터
```
GET /api/products                       // 전체
GET /api/products/{id}                  // 상세
GET /api/products/category/{category}   // 카테고리
GET /api/products/category/{c}/status/{s} // 카테고리+상태
GET /api/products/search?keyword=...    // 검색
```
모두 ProductController → ProductService → ProductRepository(findBy...) → DB 순서로 동작.

## 5) 주요 구성요소 역할
- **React 컴포넌트 (pages)**: UI, 사용자 입력, 버튼 핸들러
- **Context (UserContext, ProductContext)**: API 호출(axios) + 전역 state 관리
- **Vite Proxy**: `/api` 요청을 `http://localhost:8080`으로 전달
- **Controller**: HTTP 엔드포인트 (요청/응답 처리 시작점)
- **Service**: 비즈니스 로직, 권한/검증, 트랜잭션
- **Repository**: DB 접근 (JPA), CRUD/검색
- **Entity/DTO**: Entity=DB 구조, DTO=입출력 데이터 포맷
- **H2 DB**: 실제 데이터 저장

## 6) 상태 저장 포인트
- **프론트**: React state, localStorage(`loginUser`)
- **백엔드**: H2 DB (`MyShoppingDB.mv.db`)

## 7) 빠른 체크 포인트
- 프록시: `frontend/vite.config.js`에서 `/api` → `http://localhost:8080`
- 서버 포트: `backend/src/main/resources/application.properties` → `server.port=8080`
- API 호출 실패 시: 브라우저 Network 탭, 백엔드 콘솔 로그 확인


