# Java 콘솔 게시판 CRUD

## 프로젝트 소개
Java 문법과 객체지향 구조를 연습하기 위해 구현한 콘솔 기반 게시판 CRUD 프로젝트입니다.  
회원가입과 로그인 기능을 함께 구현하여, 로그인한 사용자만 게시글을 작성·수정·삭제할 수 있도록 구성했습니다.

## 주요 기능

### 회원 기능
- 회원가입
- 아이디 중복 확인
- 로그인
- 로그아웃
- 로그인 상태 관리

### 게시판 기능
- 게시글 작성
- 게시글 목록 조회
- 게시글 상세 조회
- 게시글 제목 수정
- 게시글 내용 수정
- 게시글 삭제

### 권한 처리
- 로그인한 사용자만 게시글 작성 가능
- 게시글 작성자만 수정 가능
- 게시글 작성자만 삭제 가능

## 프로젝트 구조

```text
src/
├─ Main.java
├─ post/
│  ├─ Post.java
│  ├─ PostRepository.java
│  └─ PostService.java
├─ user/
│  ├─ User.java
│  ├─ UserRepository.java
│  └─ UserService.java
└─ ui/
   └─ PostUi.java
```

## 클래스 역할

| 클래스 | 역할 |
|---|---|
| `Main` | 프로그램 실행 및 객체 의존성 연결 |
| `PostUi` | 콘솔 메뉴 출력과 사용자 입력 처리 |
| `PostService` | 게시글 작성, 조회, 수정, 삭제 비즈니스 로직 처리 |
| `PostRepository` | 게시글 데이터 저장 및 조회 |
| `Post` | 게시글 정보 객체 |
| `UserService` | 회원가입, 로그인, 로그아웃 로직 처리 |
| `UserRepository` | 회원 데이터 저장 및 조회 |
| `User` | 회원 정보 객체 |

## 사용 기술

- Java
- Collection Framework
  - `ArrayList`
  - `HashMap`
- 객체지향 프로그래밍
- 콘솔 입출력

## 실행 방법

터미널에서 아래 명령어로 컴파일합니다.

```bash
javac -encoding UTF-8 -d out src/Main.java src/post/*.java src/user/*.java src/ui/*.java
```

컴파일 후 실행합니다.

```bash
java -cp out Main
```

## 구현하며 연습한 점

- UI, Service, Repository 역할 분리
- `ArrayList`를 활용한 게시글 데이터 관리
- `HashMap`을 활용한 회원 데이터 관리
- 로그인 상태에 따른 기능 접근 제어
- 게시글 작성자와 로그인 사용자를 비교한 수정/삭제 권한 처리
- 숫자 입력 예외 처리
