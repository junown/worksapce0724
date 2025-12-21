# 🏗️ 프로젝트 구조 상세 설명서

## 📋 목차
1. [전체 아키텍처 개요](#1-전체-아키텍처-개요)
2. [백엔드 계층별 설명](#2-백엔드-계층별-설명)
3. [프론트엔드 구조 설명](#3-프론트엔드-구조-설명)
4. [데이터 흐름 예시](#4-데이터-흐름-예시)
5. [주요 기술 스택 역할](#5-주요-기술-스택-역할)

---

## 1. 전체 아키텍처 개요

```
┌─────────────────────────────────────────────────────────┐
│                    사용자 (브라우저)                      │
│              http://localhost:5173 접속                   │
└────────────────────┬────────────────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────────────────┐
│  FRONTEND (React + Vite)                                │
│  ├── Pages (화면)                                       │
│  ├── Components (재사용 컴포넌트)                        │
│  ├── Context (전역 상태 + API 호출)                      │
│  └── App.jsx (라우팅)                                   │
└────────────────────┬────────────────────────────────────┘
                     │ axios.get('/api/products')
                     │ axios.post('/api/users', data)
                     ↓
┌─────────────────────────────────────────────────────────┐
│  Vite Proxy (개발 서버)                                  │
│  /api → http://localhost:8080으로 전달                   │
└────────────────────┬────────────────────────────────────┘
                     │
                     ↓
┌─────────────────────────────────────────────────────────┐
│  BACKEND (Spring Boot)                                  │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐    │
│  │ Controller  │→ │   Service   │→ │ Repository  │    │
│  │  (REST API) │  │ (로직 처리)  │  │  (DB 접근)  │    │
│  └─────────────┘  └─────────────┘  └──────┬──────┘    │
│                                             │            │
│                                             ↓            │
│                                    ┌─────────────────┐  │
│                                    │   H2 Database   │  │
│                                    │  (데이터 저장)   │  │
│                                    └─────────────────┘  │
└─────────────────────────────────────────────────────────┘
```

---

## 2. 백엔드 계층별 설명

### 📁 백엔드 디렉토리 구조
```
backend/src/main/java/com/kh/server/
├── controller/          # 🌐 HTTP 요청/응답 처리 (API 엔드포인트)
├── service/            # 💼 비즈니스 로직 (중요한 업무 처리)
│   ├── 인터페이스       # 계약서 (메서드 시그니처만 정의)
│   └── Impl 클래스      # 실제 구현체 (구체적인 로직)
├── repository/         # 📦 데이터베이스 접근 (JPA)
├── entity/             # 🗄️ 데이터베이스 테이블 구조
├── dto/                # 📤📥 데이터 전송 객체 (입출력 포맷)
└── ServerApplication   # 🚀 애플리케이션 시작점
```

---

### 🎯 각 계층의 역할과 책임

#### 1️⃣ **Controller (컨트롤러)**
**위치**: `controller/MemberController.java`, `controller/ProductController.java`

**역할**:
- 🌐 **HTTP 요청을 받아서 응답을 보내는 관문**
- 클라이언트(프론트엔드)와 직접 통신
- URL 매핑 (`@GetMapping`, `@PostMapping` 등)
- 데이터 검증, 에러 처리

**예시**:
```java
@RestController
@RequestMapping("/api/users")
public class MemberController {
    private final MemberService memberService;  // Service에 의존
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberLoginRequestDto dto) {
        // Service에 작업 요청
        MemberLoginResponseDto responseDto = memberService.login(dto);
        return ResponseEntity.ok(responseDto);
    }
}
```

**책임**:
- ✅ HTTP 요청 받기
- ✅ DTO로 데이터 변환
- ✅ Service 메서드 호출
- ✅ 결과를 HTTP 응답으로 반환
- ❌ 비즈니스 로직은 여기서 하지 않음!

---

#### 2️⃣ **Service (서비스) - 인터페이스와 구현체 분리**

**위치**: 
- 인터페이스: `service/MemberService.java`, `service/ProductService.java`
- 구현체: `service/MemberServiceImpl.java`, `service/ProductServiceImpl.java`

**역할**:
- 💼 **비즈니스 로직 처리** (핵심 업무 규칙)
- Controller와 Repository 사이의 중간 계층
- 트랜잭션 관리 (`@Transactional`)

**인터페이스 예시**:
```java
public interface MemberService {
    Long signup(MemberSignupRequestDto dto);
    MemberLoginResponseDto login(MemberLoginRequestDto dto);
    // 메서드 시그니처만 정의 (계약서 같은 역할)
}
```

**구현체 예시**:
```java
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;  // Repository에 의존
    
    @Override
    public MemberLoginResponseDto login(MemberLoginRequestDto dto) {
        // 1. DB에서 사용자 찾기
        Member member = memberRepository.findByUserId(dto.getId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        
        // 2. 비밀번호 검증 (비즈니스 로직)
        if (!member.getPassword().equals(dto.getPwd())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        
        // 3. 결과 반환
        return new MemberLoginResponseDto(member);
    }
}
```

**책임**:
- ✅ 비즈니스 규칙 처리 (예: 중복 체크, 권한 확인, 검증)
- ✅ 트랜잭션 관리 (데이터 일관성 보장)
- ✅ Repository 메서드 호출
- ✅ DTO ↔ Entity 변환
- ❌ 직접 DB에 접근하지 않음 (Repository를 통해서만)

**왜 인터페이스와 구현체를 분리하나요?**
- 🔄 **유연성**: 나중에 다른 구현체로 교체 가능
- 🧪 **테스트 용이**: Mock 객체 사용 쉬움
- 📐 **관심사 분리**: 계약서와 구현의 분리

---

#### 3️⃣ **Repository (리포지토리)**
**위치**: `repository/MemberRepository.java`, `repository/ProductRepository.java`

**역할**:
- 📦 **데이터베이스 접근 전담**
- JPA를 사용한 CRUD 작업
- 쿼리 메서드 정의

**예시**:
```java
public interface MemberRepository extends JpaRepository<Member, Long> {
    // JPA가 자동으로 구현해주는 메서드들:
    // - save(Member) → INSERT 또는 UPDATE
    // - findById(Long) → SELECT WHERE id = ?
    // - findAll() → SELECT * FROM MEMBER
    // - delete(Member) → DELETE
    
    // 커스텀 메서드 (JPA가 메서드 이름으로 쿼리 자동 생성)
    boolean existsByUserId(String userId);
    Optional<Member> findByUserId(String userId);
}
```

**책임**:
- ✅ 데이터베이스 CRUD 작업
- ✅ 쿼리 실행
- ❌ 비즈니스 로직은 처리하지 않음

---

#### 4️⃣ **Entity (엔티티)**
**위치**: `entity/Member.java`, `entity/Product.java`

**역할**:
- 🗄️ **데이터베이스 테이블과 매핑되는 객체**
- JPA가 이 클래스를 보고 테이블을 생성/관리

**예시**:
```java
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;              // PK (자동 증가)
    
    @Column(nullable = false, unique = true)
    private String userId;        // NOT NULL, UNIQUE
    
    @Column(nullable = false)
    private String password;      // NOT NULL
    
    private String name;
    private String address;
    private int age;
}
```

**책임**:
- ✅ 데이터베이스 구조 정의
- ✅ 테이블과 객체 간 매핑
- ❌ 비즈니스 로직은 포함하지 않음

---

#### 5️⃣ **DTO (Data Transfer Object)**
**위치**: `dto/` 폴더

**역할**:
- 📤📥 **클라이언트와 서버 간 데이터 전송 포맷**
- Request DTO: 클라이언트 → 서버 (입력)
- Response DTO: 서버 → 클라이언트 (출력)

**예시**:
```java
// Request DTO (회원가입 입력)
@Data
public class MemberSignupRequestDto {
    private String id;
    private String pwd;
    private String name;
    private String address;
    private int age;
    
    // Entity로 변환하는 메서드
    public Member toEntity() {
        return Member.builder()
            .userId(id)
            .password(pwd)
            .name(name)
            .address(address)
            .age(age)
            .build();
    }
}

// Response DTO (로그인 응답)
@Data
public class MemberLoginResponseDto {
    private Long id;
    private String userId;
    private String name;
    // password는 보안상 포함하지 않음!
}
```

**왜 Entity를 직접 사용하지 않나요?**
- 🔒 **보안**: 비밀번호 같은 민감 정보 노출 방지
- 🎯 **명확성**: 필요한 데이터만 전송
- 🔄 **유연성**: Entity 변경 시 API에 영향 최소화

---

### 🔄 백엔드 데이터 흐름 (회원가입 예시)

```
1. 프론트엔드에서 axios.post('/api/users', {id, pwd, name, ...})
                    ↓
2. MemberController.signup()
   - @RequestBody로 MemberSignupRequestDto 받음
                    ↓
3. memberService.signup(dto) 호출
                    ↓
4. MemberServiceImpl.signup()
   - 중복 체크: memberRepository.existsByUserId()
   - Entity 생성: dto.toEntity()
   - 저장: memberRepository.save(member)
                    ↓
5. JPA가 INSERT INTO MEMBER ... 실행
                    ↓
6. 저장된 Member 반환
                    ↓
7. Controller가 ResponseEntity로 응답
                    ↓
8. 프론트엔드에 결과 전달
```

---

## 3. 프론트엔드 구조 설명

### 📁 프론트엔드 디렉토리 구조
```
frontend/src/
├── components/         # 🔧 재사용 가능한 컴포넌트
│   └── Header.jsx      # 공통 헤더 (네비게이션 바)
├── context/            # 🌍 전역 상태 관리 + API 호출
│   ├── UserContext.jsx      # 사용자 정보, 로그인/회원가입 API
│   └── ProductContext.jsx   # 상품 목록, 상품 CRUD API
├── pages/              # 📄 각 페이지 컴포넌트
│   ├── Home.jsx            # 홈 (상품 목록)
│   ├── Login.jsx           # 로그인 페이지
│   ├── Signup.jsx          # 회원가입 페이지
│   ├── MyPage.jsx          # 마이페이지
│   ├── ProductRegister.jsx # 상품 등록
│   ├── ProductDetail.jsx   # 상품 상세
│   ├── CategoryList.jsx    # 카테고리별 목록
│   └── SearchPage.jsx      # 검색 결과
├── App.jsx             # 🗺️ 라우팅 설정 (URL → 컴포넌트 매핑)
└── main.jsx            # 🚀 진입점 (React 앱 시작)
```

---

### 🎯 각 부분의 역할

#### 1️⃣ **Pages (페이지 컴포넌트)**
**역할**: 각 화면을 담당하는 컴포넌트

**예시** (`pages/Login.jsx`):
```jsx
const Login = () => {
  const { login } = useContext(UserContext);  // Context에서 함수 가져오기
  
  const handleLogin = async () => {
    const success = await login(id, pwd);  // API 호출
    if (success) {
      navigate('/');  // 성공 시 홈으로 이동
    }
  };
  
  return (
    <form onSubmit={handleLogin}>
      {/* 입력 폼 UI */}
    </form>
  );
};
```

**책임**:
- ✅ 사용자 인터페이스(UI) 렌더링
- ✅ 사용자 입력 처리
- ✅ Context의 함수 호출 (API 요청)
- ✅ 라우팅 (페이지 이동)

---

#### 2️⃣ **Context (컨텍스트)**
**역할**: 
- 🌍 **전역 상태 관리** (예: 로그인한 사용자 정보)
- 🔌 **API 호출 함수 제공** (axios 사용)

**예시** (`context/UserContext.jsx`):
```jsx
export const UserProvider = ({ children }) => {
  // 전역 상태
  const [user, setUser] = useState(null);
  
  // API 호출 함수
  const login = async (id, pwd) => {
    try {
      const response = await axios.post('/api/users/login', { id, pwd });
      setUser(response.data);  // 상태 업데이트
      return true;
    } catch (error) {
      alert('로그인 실패');
      return false;
    }
  };
  
  return (
    <UserContext.Provider value={{ user, login, logout, ... }}>
      {children}
    </UserContext.Provider>
  );
};
```

**책임**:
- ✅ 전역 상태 관리 (user, products 등)
- ✅ axios로 백엔드 API 호출
- ✅ 상태와 함수를 모든 컴포넌트에 제공
- ❌ UI 렌더링은 하지 않음

---

#### 3️⃣ **Components (컴포넌트)**
**역할**: 여러 페이지에서 재사용하는 공통 컴포넌트

**예시** (`components/Header.jsx`):
```jsx
const Header = () => {
  const { user, logout } = useContext(UserContext);
  
  return (
    <header>
      <Link to="/">홈</Link>
      {user ? (
        <>
          <Link to="/mypage">마이페이지</Link>
          <button onClick={logout}>로그아웃</button>
        </>
      ) : (
        <Link to="/login">로그인</Link>
      )}
    </header>
  );
};
```

---

#### 4️⃣ **App.jsx (라우팅)**
**역할**: URL 경로와 컴포넌트를 매핑

**예시**:
```jsx
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/mypage" element={<MyPage />} />
        <Route path="/product/:id" element={<ProductDetail />} />
      </Routes>
    </BrowserRouter>
  );
}
```

**SPA (Single Page Application)란?**
- 하나의 HTML 페이지에서 JavaScript로 화면 전환
- 서버에 페이지를 요청하지 않고, 클라이언트에서 라우팅 처리
- 빠르고 부드러운 사용자 경험

---

### 🔄 프론트엔드 데이터 흐름 (로그인 예시)

```
1. 사용자가 Login.jsx에서 로그인 버튼 클릭
                    ↓
2. handleLogin() 함수 실행
                    ↓
3. UserContext.login(id, pwd) 호출
                    ↓
4. axios.post('/api/users/login', {id, pwd})
                    ↓
5. Vite Proxy가 요청을 백엔드(8080)로 전달
                    ↓
6. 백엔드에서 로그인 처리 후 사용자 정보 반환
                    ↓
7. setUser(response.data) → 전역 상태 업데이트
                    ↓
8. localStorage에 사용자 정보 저장
                    ↓
9. navigate('/') → 홈 페이지로 이동
                    ↓
10. Header.jsx에서 user 상태 확인 → "로그아웃" 버튼 표시
```

---

## 4. 데이터 흐름 예시

### 예시 1: 상품 목록 조회

```
[사용자]
  브라우저에서 http://localhost:5173 접속
                    ↓
[Frontend: App.jsx]
  ProductProvider 마운트
                    ↓
[Frontend: ProductContext.jsx]
  useEffect() → loadProducts() 실행
                    ↓
  axios.get('/api/products')
                    ↓
[Vite Proxy]
  /api/products → http://localhost:8080/api/products
                    ↓
[Backend: ProductController]
  @GetMapping("/api/products")
  getAll() 메서드 실행
                    ↓
[Backend: ProductService]
  getAll() 메서드 실행
                    ↓
[Backend: ProductRepository]
  findAll() → JPA 쿼리 실행
  SELECT * FROM PRODUCT
                    ↓
[H2 Database]
  데이터 조회
                    ↓
[응답 역순으로 반환]
  Repository → Service → Controller
                    ↓
[Backend 응답]
  [ProductResponseDto, ProductResponseDto, ...]
                    ↓
[Frontend: ProductContext]
  setProducts(response.data)
  → 전역 상태 업데이트
                    ↓
[Frontend: Home.jsx]
  const { products } = useContext(ProductContext)
  → 화면에 상품 목록 렌더링
```

---

### 예시 2: 상품 등록

```
[사용자]
  ProductRegister.jsx에서 상품 정보 입력 후 "등록" 버튼 클릭
                    ↓
[Frontend: ProductRegister.jsx]
  const { addProduct } = useContext(ProductContext)
  await addProduct(productData)
                    ↓
[Frontend: ProductContext.jsx]
  addProduct() 함수 실행
  axios.post('/api/products?sellerId=123', productData)
                    ↓
[Vite Proxy]
  → http://localhost:8080/api/products?sellerId=123
                    ↓
[Backend: ProductController]
  @PostMapping("/api/products")
  create(ProductRequestDto dto, @RequestParam Long sellerId)
                    ↓
[Backend: ProductService]
  create(dto, sellerId) 실행
  - Product entity 생성: dto.toEntity(sellerId)
  - 검증 로직 (필요 시)
                    ↓
[Backend: ProductRepository]
  save(product) → JPA가 INSERT 실행
                    ↓
[H2 Database]
  INSERT INTO PRODUCT (name, price, ...) VALUES (...)
                    ↓
[응답 반환]
  저장된 Product → ProductResponseDto 변환 → Controller
                    ↓
[Frontend]
  성공 응답 받음
  loadProducts() 재호출 → 목록 갱신
  navigate('/') → 홈으로 이동
```

---

## 5. 주요 기술 스택 역할

### 백엔드

| 기술 | 역할 |
|------|------|
| **Spring Boot** | Java 웹 애플리케이션 프레임워크 (서버 실행, 의존성 주입) |
| **Spring Data JPA** | 데이터베이스 ORM (객체-관계 매핑, 자동 쿼리 생성) |
| **H2 Database** | 인메모리 데이터베이스 (개발/테스트용, 파일로 데이터 저장) |
| **Lombok** | 보일러플레이트 코드 감소 (@Data, @Getter 등) |
| **Gradle** | 빌드 도구 (의존성 관리, 컴파일, 실행) |

### 프론트엔드

| 기술 | 역할 |
|------|------|
| **React** | UI 라이브러리 (컴포넌트 기반 개발) |
| **React Router** | 클라이언트 사이드 라우팅 (SPA 구현) |
| **Axios** | HTTP 클라이언트 (API 호출) |
| **Context API** | 전역 상태 관리 (Props drilling 방지) |
| **Styled Components** | CSS-in-JS (컴포넌트 스타일링) |
| **Vite** | 빌드 도구 + 개발 서버 (프록시 포함) |

### 통신

| 구성요소 | 역할 |
|----------|------|
| **Vite Proxy** | `/api` 요청을 `http://localhost:8080`으로 전달 (CORS 문제 해결) |
| **RESTful API** | HTTP 메서드로 리소스 조작 (GET, POST, PUT, DELETE) |
| **CORS** | Cross-Origin Resource Sharing (백엔드에서 프론트엔드 요청 허용) |

---

## 📚 핵심 개념 정리

### 계층 구조 (Layered Architecture)
```
Controller → Service → Repository → Database
```
- 각 계층은 자신의 역할만 담당
- 의존성은 한 방향으로만 흐름 (Controller → Service → Repository)
- 계층 간 결합도 낮추기 (느슨한 결합)

### 의존성 주입 (Dependency Injection)
```java
@RequiredArgsConstructor  // Lombok이 생성자 자동 생성
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;  // 생성자로 주입
}
```
- 객체를 직접 생성하지 않고, 외부(Spring)에서 주입받음
- 테스트와 유지보수가 쉬워짐

### RESTful API 설계
```
GET    /api/products      → 전체 조회
GET    /api/products/1    → 상세 조회
POST   /api/products      → 생성
PUT    /api/products/1    → 수정
DELETE /api/products/1    → 삭제
```

### JPA (Java Persistence API)
```java
public interface MemberRepository extends JpaRepository<Member, Long> {
    // JPA가 메서드 이름으로 쿼리 자동 생성
    Optional<Member> findByUserId(String userId);
    // → SELECT * FROM MEMBER WHERE user_id = ?
}
```

---

## 🎓 학습 포인트

1. **관심사의 분리**: 각 계층은 자신의 역할만 수행
2. **의존성 주입**: 객체 간 결합도 낮추기
3. **인터페이스 활용**: 구현체 교체 용이하게
4. **DTO 패턴**: Entity와 API 응답 분리
5. **RESTful 설계**: 일관성 있는 API 구조
6. **SPA 구조**: 클라이언트 사이드 라우팅

---

## ✅ 체크리스트

프로젝트를 이해하기 위해 확인할 사항:

- [ ] 백엔드 3계층 구조 이해 (Controller → Service → Repository)
- [ ] 인터페이스와 구현체의 차이 이해
- [ ] Entity와 DTO의 차이 이해
- [ ] 프론트엔드 Context API 역할 이해
- [ ] Vite Proxy 설정 이해
- [ ] 데이터 흐름 추적 가능 (요청 → 응답)

---

이 문서를 읽고 나면 프로젝트의 전체 구조와 각 구성요소의 역할을 이해할 수 있습니다! 🎉

