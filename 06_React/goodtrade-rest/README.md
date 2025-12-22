# 굿거래 (GoodTrade) - 중고 거래 플랫폼

React와 Spring Boot를 활용한 풀스택 중고 물품 거래 서비스입니다.  
사용자는 물품을 등록하고, 카테고리별로 조회하며, 즉시 구매 상태를 변경할 수 있습니다.

---

## 목차

- [프로젝트 소개](#프로젝트-소개)
- [기술 스택](#기술-스택)
- [주요 기능](#주요-기능)
- [프로젝트 구조](#프로젝트-구조)
- [시작하기](#시작하기)
- [API 문서](#api-문서)
- [개발 환경](#개발-환경)

---

## 프로젝트 소개

굿거래는 사용자 친화적인 인터페이스로 중고 물품을 쉽게 등록하고 거래할 수 있는 플랫폼입니다.

### 특징
- RESTful API 기반의 백엔드 서버
- React 기반의 모던한 프론트엔드
- 실시간 상품 상태 관리
- 카테고리별 필터링 및 검색 기능
- 사용자 인증 및 권한 관리

---

## 기술 스택

### Frontend
- **React 19.2.0** - UI 라이브러리
- **React Router 7.9.6** - 라우팅
- **Styled Components 6.1.19** - CSS-in-JS 스타일링
- **Axios 1.13.2** - HTTP 클라이언트
- **Vite 7.2.4** - 빌드 도구

### Backend
- **Spring Boot 3.5.8** - Java 웹 프레임워크
- **Spring Data JPA** - 데이터베이스 ORM
- **H2 Database** - 인메모리 데이터베이스
- **Lombok** - 보일러플레이트 코드 감소
- **Java 17** - 프로그래밍 언어

### Database
- **H2 Database** - 개발용 인메모리 데이터베이스

---

## 주요 기능

### 1. 회원 관리 
- **회원가입**: 아이디 중복 체크 및 유효성 검사
- **로그인/로그아웃**: 세션 기반 인증
- **마이페이지**: 내 정보 수정 및 회원 탈퇴
- **비밀번호 확인**: 탈퇴 시 비밀번호 재확인

### 2. 상품 관리 
- **상품 등록**: 이미지 URL, 카테고리, 가격, 설명 입력
- **상품 조회**: 
  - 전체 상품 목록
  - 카테고리별 조회 (전자기기, 패션, 뷰티)
  - 상태별 필터링 (판매중, 예약중, 판매완료)
  - 상품명 검색
- **상품 수정/삭제**: 본인 상품만 수정/삭제 가능
- **상품 상태 변경**: 판매중 → 예약중 → 판매완료

### 3. 거래 기능 
- **구매하기**: 판매자가 아닌 사용자만 구매 가능
- **즉시 상태 변경**: 구매 완료 시 자동으로 '판매완료' 상태로 변경
- **권한 관리**: 본인 상품은 구매 불가

### 4. 기타 기능 
- **검색 기능**: 헤더 검색창을 통한 상품명 검색
- **반응형 디자인**: 다양한 화면 크기 지원
- **404 페이지**: 잘못된 경로 접근 시 에러 페이지

---

## 프로젝트 구조

```
goodtrade-rest/
├── backend/                    # Spring Boot 백엔드
│   ├── src/main/java/com/kh/server/
│   │   ├── controller/         # REST API 컨트롤러
│   │   │   ├── MemberController.java
│   │   │   └── ProductController.java
│   │   ├── service/            # 비즈니스 로직
│   │   │   ├── MemberService.java
│   │   │   ├── MemberServiceImpl.java
│   │   │   ├── ProductService.java
│   │   │   ├── ProductServiceImpl.java
│   │   │   ├── OrderService.java
│   │   │   └── OrderServiceImpl.java
│   │   ├── repository/         # 데이터베이스 접근
│   │   │   ├── MemberRepository.java
│   │   │   ├── MemberRepositoryImpl.java
│   │   │   ├── ProductRepository.java
│   │   │   ├── ProductRepositoryImpl.java
│   │   │   ├── OrderRepository.java
│   │   │   └── OrderRepositoryImpl.java
│   │   ├── entity/             # 데이터베이스 엔티티
│   │   │   ├── BaseTimeEntity.java
│   │   │   ├── Member.java
│   │   │   ├── Product.java
│   │   │   └── Order.java
│   │   ├── dto/                # 데이터 전송 객체
│   │   │   ├── MemberSignupRequestDto.java
│   │   │   ├── MemberLoginRequestDto.java
│   │   │   ├── MemberLoginResponseDto.java
│   │   │   ├── MemberUpdateRequestDto.java
│   │   │   ├── MemberDeleteRequestDto.java
│   │   │   ├── ProductRequestDto.java
│   │   │   ├── ProductResponseDto.java
│   │   │   └── ProductUpdateRequestDto.java
│   │   └── ServerApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── build.gradle
│
├── frontend/                   # React 프론트엔드
│   ├── src/
│   │   ├── components/         # 공통 컴포넌트
│   │   │   └── Header.jsx
│   │   ├── context/            # Context API (전역 상태)
│   │   │   ├── UserContext.jsx
│   │   │   └── ProductContext.jsx
│   │   ├── pages/              # 페이지 컴포넌트
│   │   │   ├── Home.jsx
│   │   │   ├── Login.jsx
│   │   │   ├── Signup.jsx
│   │   │   ├── MyPage.jsx
│   │   │   ├── ProductRegister.jsx
│   │   │   ├── ProductDetail.jsx
│   │   │   ├── CategoryList.jsx
│   │   │   └── SearchPage.jsx
│   │   ├── App.jsx             # 라우팅 설정
│   │   └── main.jsx            # 진입점
│   ├── package.json
│   └── vite.config.js          # Vite 설정 (프록시 포함)
│
└── README.md                   # 프로젝트 문서
```

---

## 시작하기

### 사전 요구사항

- **Java 17** 이상
- **Node.js 18** 이상
- **npm** 또는 **yarn**

### 1. 백엔드 서버 실행

```bash
# backend 디렉토리로 이동
cd backend

# Gradle을 사용하여 실행 (Windows)
.\gradlew bootRun

# 또는 (Linux/Mac)
./gradlew bootRun
```

백엔드 서버가 `http://localhost:8080`에서 실행됩니다.

### 2. H2 데이터베이스 콘솔 접속

백엔드 서버 실행 후 브라우저에서 H2 콘솔에 접속할 수 있습니다.

**접속 정보:**
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./MyShoppingDB`
- 사용자명: `sa`
- 비밀번호: (비워두기)

**주의사항:**
- 백엔드 서버가 실행 중이어야 H2 콘솔에 접속할 수 있습니다.
- 데이터베이스 파일은 프로젝트 루트 디렉토리에 `MyShoppingDB.mv.db`로 생성됩니다.

### 3. 프론트엔드 서버 실행

```bash
# 새 터미널에서 frontend 디렉토리로 이동
cd frontend

# 의존성 설치 (최초 1회)
npm install

# 또는 필요한 주요 패키지만 설치하려면
npm install axios styled-components react-router-dom

# 개발 서버 실행
npm run dev
```

프론트엔드 서버가 `http://localhost:5173`에서 실행됩니다.

### 4. 브라우저에서 접속

```
http://localhost:5173
```

---

## 주요 도메인 설명

### 엔티티 구조

프로젝트는 3개의 주요 엔티티로 구성되어 있습니다.

#### 1. Member (회원)
- 회원의 기본 정보를 저장하는 엔티티
- 주요 필드: id, userId, password, name, address, age
- 연관관계:
  - `@OneToMany` with Product (판매한 상품 목록)
  - `@OneToMany` with Order (구매한 주문 목록)

#### 2. Product (상품)
- 중고 물품 정보를 저장하는 엔티티
- 주요 필드: id, name, price, category, status, description, images
- 연관관계:
  - `@ManyToOne` with Member (판매자)
  - `@OneToMany` with Order (해당 상품의 주문 목록)

#### 3. Order (주문)
- 구매 주문 정보를 저장하는 엔티티
- BaseTimeEntity를 상속하여 생성/수정 시간 자동 관리
- 주요 필드: id, price, status, createDate, modifyDate
- 연관관계:
  - `@ManyToOne` with Member (구매자)
  - `@ManyToOne` with Product (구매한 상품)

### 연관관계 구조

```
Member (1) ──< (N) Product (판매자 관계)
Member (1) ──< (N) Order (구매자 관계)
Product (1) ──< (N) Order (상품 관계)
```

### Service 계층 구조

- **MemberService**: 회원 관련 비즈니스 로직
  - 회원가입 (아이디 중복 체크)
  - 로그인 (아이디/비밀번호 검증)
  - 회원 정보 수정
  - 회원 탈퇴 (비밀번호 확인)
  
- **ProductService**: 상품 관련 비즈니스 로직
  - 상품 등록, 조회, 수정, 삭제
  - 카테고리별/상태별 필터링
  - 상품명 검색
  - 판매자 권한 확인
  
- **OrderService**: 주문 관련 비즈니스 로직
  - 상품 구매 처리
  - Order 엔티티 생성 및 저장
  - 구매 유효성 검사 (본인 상품 구매 방지, 이미 판매완료 방지)

### Repository 구조

모든 Repository는 커스텀 구현체 방식으로 구현되어 있습니다:
- **인터페이스**: 메서드 선언만
- **Impl 클래스**: EntityManager를 사용하여 JPQL 쿼리 직접 작성
- JPA의 메서드 이름 규칙 대신 명시적인 쿼리 작성으로 유지보수성 향상

---

## API 문서

### 회원 관리 API (`/api/users`)

#### POST `/api/users` - 회원가입

**Request:**
```json
{
  "id": "user123",
  "pwd": "password123",
  "name": "홍길동",
  "address": "서울시 강남구",
  "age": 25
}
```

**Response:**
- 성공 (201 Created): `"회원가입 성공!"`
- 실패 (400 Bad Request): `"이미 존재하는 아이디입니다."`

---

#### POST `/api/users/login` - 로그인

**Request:**
```json
{
  "id": "user123",
  "pwd": "password123"
}
```

**Response:**
- 성공 (200 OK):
```json
{
  "id": 1,
  "userId": "user123",
  "name": "홍길동",
  "address": "서울시 강남구",
  "age": 25
}
```
- 실패 (401 Unauthorized): `"아이디 또는 비밀번호가 일치하지 않습니다."`

---

#### PUT `/api/users/{id}` - 사용자 정보 수정

**Path Variable:**
- `id`: 회원 ID (Long)

**Request:**
```json
{
  "pwd": "newPassword123",
  "name": "홍길동",
  "address": "서울시 서초구",
  "age": 26
}
```

**Response:**
- 성공 (200 OK):
```json
{
  "id": 1,
  "userId": "user123",
  "name": "홍길동",
  "address": "서울시 서초구",
  "age": 26
}
```
- 실패 (400 Bad Request): 에러 메시지

---

#### DELETE `/api/users/{id}` - 회원 탈퇴

**Path Variable:**
- `id`: 회원 ID (Long)

**Request:**
```json
{
  "pwd": "password123"
}
```

**Response:**
- 성공 (200 OK): `"회원 탈퇴가 완료되었습니다."`
- 실패 (400 Bad Request): `"비밀번호가 일치하지 않습니다."`

---

### 상품 관리 API (`/api/products`)

#### POST `/api/products?sellerId={id}` - 상품 등록

**Query Parameter:**
- `sellerId`: 판매자 ID (Long)

**Request:**
```json
{
  "name": "아이폰 14",
  "price": 800000,
  "category": "전자기기",
  "description": "사용감 적은 아이폰 14입니다.",
  "images": [
    "https://example.com/image1.jpg",
    "https://example.com/image2.jpg"
  ]
}
```

**Response:**
- 성공 (201 Created):
```json
{
  "id": 1,
  "name": "아이폰 14",
  "price": 800000,
  "category": "전자기기",
  "status": "판매중",
  "seller": 1,
  "description": "사용감 적은 아이폰 14입니다.",
  "images": [
    "https://example.com/image1.jpg",
    "https://example.com/image2.jpg"
  ]
}
```

---

#### GET `/api/products` - 전체 상품 조회

**Response:**
- 성공 (200 OK):
```json
[
  {
    "id": 1,
    "name": "아이폰 14",
    "price": 800000,
    "category": "전자기기",
    "status": "판매중",
    "seller": 1,
    "description": "사용감 적은 아이폰 14입니다.",
    "images": ["https://example.com/image1.jpg"]
  },
  {
    "id": 2,
    "name": "나이키 운동화",
    "price": 120000,
    "category": "패션",
    "status": "판매중",
    "seller": 2,
    "description": "새상품입니다.",
    "images": []
  }
]
```

---

#### GET `/api/products/{id}` - 상품 상세 조회

**Path Variable:**
- `id`: 상품 ID (Long)

**Response:**
- 성공 (200 OK):
```json
{
  "id": 1,
  "name": "아이폰 14",
  "price": 800000,
  "category": "전자기기",
  "status": "판매중",
  "seller": 1,
  "description": "사용감 적은 아이폰 14입니다.",
  "images": ["https://example.com/image1.jpg"]
}
```
- 실패 (400 Bad Request): `"상품을 찾을 수 없습니다."`

---

#### GET `/api/products/category/{category}` - 카테고리별 조회

**Path Variable:**
- `category`: 카테고리명 (String) - 예: "전자기기", "패션", "뷰티"

**Response:**
- 성공 (200 OK): 상품 목록 배열 (위와 동일한 형식)

---

#### GET `/api/products/category/{category}/status/{status}` - 카테고리+상태별 조회

**Path Variables:**
- `category`: 카테고리명 (String)
- `status`: 상태 (String) - "판매중", "예약중", "판매완료"

**Response:**
- 성공 (200 OK): 상품 목록 배열

---

#### GET `/api/products/search?keyword={keyword}` - 상품 검색

**Query Parameter:**
- `keyword`: 검색 키워드 (String)

**Response:**
- 성공 (200 OK): 상품명에 키워드가 포함된 상품 목록 배열

---

#### PUT `/api/products/{id}?sellerId={id}` - 상품 수정

**Path Variable:**
- `id`: 상품 ID (Long)

**Query Parameter:**
- `sellerId`: 판매자 ID (Long)

**Request:**
```json
{
  "name": "아이폰 14 Pro",
  "price": 900000,
  "category": "전자기기",
  "status": "예약중",
  "description": "수정된 설명입니다.",
  "images": ["https://example.com/new-image.jpg"]
}
```

**Response:**
- 성공 (200 OK): 수정된 상품 정보 (ProductResponseDto 형식)
- 실패 (400 Bad Request): `"본인의 상품만 수정할 수 있습니다."` 또는 `"상품을 찾을 수 없습니다."`

---

#### PUT `/api/products/{id}/status?status={상태}` - 상태 변경

**Path Variable:**
- `id`: 상품 ID (Long)

**Query Parameter:**
- `status`: 변경할 상태 (String) - "판매중", "예약중", "판매완료"

**Response:**
- 성공 (200 OK): 상태가 변경된 상품 정보
- 실패 (400 Bad Request): `"상품을 찾을 수 없습니다."`

---

#### POST `/api/products/{id}/purchase?buyerId={id}` - 상품 구매

**Path Variable:**
- `id`: 상품 ID (Long)

**Query Parameter:**
- `buyerId`: 구매자 ID (Long)

**Response:**
- 성공 (200 OK):
```json
{
  "id": 1,
  "name": "아이폰 14",
  "price": 800000,
  "category": "전자기기",
  "status": "판매완료",
  "seller": 1,
  "description": "사용감 적은 아이폰 14입니다.",
  "images": ["https://example.com/image1.jpg"]
}
```
- 실패 (400 Bad Request): 
  - `"자신의 상품은 구매할 수 없습니다."`
  - `"이미 판매완료된 상품입니다."`
  - `"상품을 찾을 수 없습니다."`

**참고:** 구매 시 Order 테이블에 주문 정보가 자동으로 저장되며, 상품 상태가 "판매완료"로 변경됩니다.

---

#### DELETE `/api/products/{id}?sellerId={id}` - 상품 삭제

**Path Variable:**
- `id`: 상품 ID (Long)

**Query Parameter:**
- `sellerId`: 판매자 ID (Long)

**Response:**
- 성공 (200 OK): `"상품이 삭제되었습니다."`
- 실패 (400 Bad Request): `"본인의 상품만 삭제할 수 있습니다."` 또는 `"상품을 찾을 수 없습니다."`


## 개발 환경

### 백엔드 설정

**application.properties**
```properties
server.port=8080

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.datasource.url=jdbc:h2:file:./MyShoppingDB;AUTO_SERVER=TRUE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 프론트엔드 설정

**vite.config.js**
```javascript
proxy: {
  '/api': {
    target: 'http://localhost:8080',
    changeOrigin: true,
  }
}
```

---

## 주요 파일 설명

### 백엔드

- **Controller**: REST API 엔드포인트 정의 및 요청/응답 처리
  - `MemberController`: 회원 관련 API (`/api/users`)
  - `ProductController`: 상품 관련 API (`/api/products`)
- **Service**: 비즈니스 로직 구현
  - `MemberService`: 회원가입, 로그인, 정보 수정, 탈퇴
  - `ProductService`: 상품 CRUD, 검색, 필터링
  - `OrderService`: 상품 구매 처리 (Order 생성)
- **Repository**: 데이터베이스 접근 계층
  - 인터페이스: 메서드 선언
  - Impl 클래스: EntityManager를 사용한 JPQL 쿼리 직접 구현
  - `MemberRepository`, `ProductRepository`, `OrderRepository`
- **Entity**: 데이터베이스 테이블 구조 정의
  - `Member`: 회원 정보
  - `Product`: 상품 정보
  - `Order`: 주문 정보
  - `BaseTimeEntity`: 생성/수정 시간 자동 관리
- **DTO**: 클라이언트와 서버 간 데이터 전송 형식 정의
  - Request DTO: 클라이언트에서 서버로 전송
  - Response DTO: 서버에서 클라이언트로 전송

### 프론트엔드

- **Context**: 전역 상태 관리 및 API 호출 함수 제공
- **Pages**: 각 페이지의 UI 컴포넌트
- **Components**: 재사용 가능한 공통 컴포넌트

---

## 데이터 흐름

```
사용자 액션
    ↓
React 컴포넌트 (Pages)
    ↓
Context API (API 호출)
    ↓
Axios (HTTP 요청)
    ↓
Vite Proxy (프록시)
    ↓
Spring Boot Controller
    ↓
Service (비즈니스 로직)
    ↓
Repository (데이터베이스)
    ↓
응답 반환
    ↓
화면 업데이트
```

---

## 문제 해결

### 백엔드 서버가 시작되지 않을 때
- Java 17이 설치되어 있는지 확인
- 포트 8080이 사용 중인지 확인

### 프론트엔드에서 API 호출 실패 시
- 백엔드 서버가 실행 중인지 확인
- `vite.config.js`의 프록시 설정 확인
- 브라우저 개발자 도구의 Network 탭에서 에러 확인

### 데이터베이스 초기화
- `MyShoppingDB.mv.db` 파일 삭제 후 서버 재시작

---

## 참고 자료

- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [React 공식 문서](https://react.dev)


