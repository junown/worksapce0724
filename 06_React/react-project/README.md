# 🛒 굿거래 (GoodTrade) - 중고 거래 플랫폼

React와 Context API를 활용하여 만든 중고 물품 거래 서비스입니다.
사용자는 물품을 등록하고, 카테고리별로 조회하며, 댓글이나 채팅 없이 즉시 구매 상태를 변경할 수 있습니다.

## 📅 프로젝트 기간
2025.12.01 ~ 2025.12.04

## 🛠️ 기술 스택 (Tech Stack)
- **Frontend**: React, React Router
- **State Management**: Context API (UserContext, ProductContext)
- **Styling**: Styled-components
- **Data Persistence**: LocalStorage (새로고침 시 데이터 유지)
- **Image Handling**: Base64 Encoding (FileReader)

## 📌 주요 기능 (Key Features)

### 1. 회원 관리 (User)
- **회원가입**: 아이디 중복 체크 및 유효성 검사.
- **로그인/로그아웃**: LocalStorage를 이용한 로그인 세션 유지.
- **마이페이지**: 내 정보 수정 및 회원 탈퇴 기능.

### 2. 상품 관리 (Product)
- **상품 등록**: 이미지 업로드(미리보기), 카테고리 설정, 가격 및 설명 작성. (판매자 ID 자동 연동)
  - **홈 화면**: 카테고리별(전자기기, 패션, 뷰티) 가로 스크롤 리스트.
  - **카테고리별 페이지**: 필터링(전체/판매중/예약중/판매완료) 기능 제공.
  - **상세 페이지**: 상품 상세 정보 및 이미지 확대 보기.
- **상품 수정/삭제**: 본인이 작성한 상품일 경우에만 수정/삭제 버튼 노출.

### 3. 거래 기능 (Trade)
- **구매하기**: 판매자가 아닌 다른 유저가 접속 시 '구매하기' 버튼 활성화.
- **상태 변경**: 구매 완료 시 상품 상태가 즉시 '판매완료'로 변경되며 버튼 비활성화.

### 4. 기타 (Others)
- **검색 기능**: 헤더의 검색창을 통해 상품명 검색 가능.
- **반응형 디자인**: Flexbox와 Grid를 활용한 레이아웃.
- **404 페이지**: 잘못된 경로 접근 시 에러 페이지 제공.

## 📂 폴더 구조 (Folder Structure)

src/
├── assets/          # 로고 등 이미지 파일
├── components/      # 헤더 등 공통 컴포넌트
├── context/         # UserContext, ProductContext (전역 상태)
├── pages/           # 홈, 로그인, 상품등록, 상세페이지 등
├── App.js           # 라우팅 설정
└── index.js         # 진입점