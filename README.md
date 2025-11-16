# 🍊 Rotten Tangerines: 개인 소장용 영화 기록 서비스

## 🔖 1. 프로젝트 기본 정보

| 항목 | 내용 |
|---|---|
| **프로젝트명** | Rotten Tangerines🍊 |
| **버전** | 1.0.0 |
| **개발 기간** | 2025.10.30 ~ 2025.11.14 |
| **팀명** | 오사카 귤밭 |
| **팀원** | 강동희, 김현정, 성찬미, 손큰솔, 최정민 |

&nbsp;

## 🧭 2. 프로젝트 개요

*   **목표**
    Java 17 기반 Spring Boot 3.x를 활용하여 일기 형식의 개인 소장용 영화 기록 웹 애플리케이션을 개발합니다. 리뷰 작성/관리, 마이페이지 등의 기능을 통해 영화 리뷰 사이트에 필요한 기본 기능을 구현하고자 합니다.
*   **특징**
    *   Spring Boot 3.x + JPA + Thymeleaf 기반 MVC 구조
    *   REST API
    *   Oracle DB 21c xe 데이터베이스 사용
    *   반응형 웹 (Bootstrap 5.3)

&nbsp;
## 🏗️ 3. 시스템 아키텍처

> Backend
*   Java 17, Spring Boot 3.x
*   Spring Data JPA
*   Spring MVC (REST API)
*   Oracle DB
*   Lombok

> Frontend
*   Thymeleaf (SSR)
*   Bootstrap 5.3

&nbsp;
## 👥 4. 주요 사용자(User Persona)

| 사용자 유형 | 설명 |
|---|---|
| 일반 사용자 | 영화 선택 후 리뷰 작성 |

&nbsp;
## 📌 5. 주요 기능 요약

| 기능 영역 | 기능명 |
|---|---|
| 리뷰 작성 | 평점(1~5), 코멘트, 사진 등록 |
| 리뷰 관리 | 리뷰 작성 |
| 영화 기능 | 영화 등록, 영화 조회 |

&nbsp;
## 🗂️ 6. 개발 일정 요약 (마일스톤)

| 기간 | 내용 |
|---|---|
| 1일차 | 요구사항 분석, 기획, 화면 설계 |
| 2일차 | DB 모델링, 회원/상품 기능 개발 |
| 3일차 | API 명세서, 화면 설계서 작성 |
| 4일차 | 백엔드(등록, 개별조회,전체조회), 프론트(홈, 수정, 등록) |
| 5일차 | 백엔드(이미지업로드/ 삭제  수정 추가), 프론트(네비게이션 기능 추가 및 수정 ), 개인별 면접 확인 |
| 6일차 | 페이지연결 (html, js), 디자인 디테일 수정(버튼사이즈 통일, 조회페이지 테두리 추가) |

&nbsp;
## 📃7. 페이지별 기능
&nbsp;

- 전체 조회
<img width="1920" height="1080" alt="KakaoTalk_20251116_231441435" src="https://github.com/user-attachments/assets/1f66dd1a-fed4-438c-ae10-8dde675f2a8c" />


&nbsp;
- 등록
<img width="1920" height="1080" alt="KakaoTalk_20251116_232021080" src="https://github.com/user-attachments/assets/e3c3da80-982a-4b0b-93fc-c0e6123aaa57" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/14ba2f43-ce04-456a-b2eb-77754a89154b" />


&nbsp;
- 개별 조회
<img width="1920" height="1080" alt="KakaoTalk_20251116_231522900" src="https://github.com/user-attachments/assets/4d33972c-a628-4aa5-a07a-18ed750f1d02" />


&nbsp;
- 수정
<img width="1920" height="1080" alt="KakaoTalk_20251116_231606708" src="https://github.com/user-attachments/assets/5a35034f-0241-48c1-b147-2633c41d9084" />
<img width="1920" height="1080" alt="KakaoTalk_20251116_231620470" src="https://github.com/user-attachments/assets/89653d26-a948-4260-b6dc-c8c3dd7be5a9" />


&nbsp;
- 삭제
<img width="1920" height="1080" alt="KakaoTalk_20251116_232726073" src="https://github.com/user-attachments/assets/7f0d9eb6-a312-4b50-ac20-e3f43dbc054e" />

    
