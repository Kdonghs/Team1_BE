# Seamless
- Notion, Trello와 같은 협업 툴 개발을 목표로하는 프로젝트입니다.
  - 기존의 협업 툴로 간단한 프로젝트를 진행하려고 하는데, 처음 써보는 사람에게는 너무 복잡하다고 느꼈습니다.
  - 배보다 배꼽이 더 큰 것은 프로젝트 진행에 모순이라는 생각이 들어서, 이 웹을 제작하게 되었습니다.
- 이미 시중에 나와있는 협업 툴들에 비교한 경쟁력은 다음과 같습니다.
  - 로그인 없이 **참여 코드**만으로 참여할 수 있게 하여, 참여 과정에 대한 진입 장벽을 낮추었습니다.
  - 깃허브 잔디심기와 같이 동기부여를 위한 게이미피케이션 요소들을 기능으로 탑재했습니다.

---

# 어플리케이션 사용법
- 이 앱의 사용자는 크게 **팀장**과 **팀원**으로 나뉜다.
- 팀장은 프로젝트를 생성하고 팀원들을 초대할 수 있다.
  - 팀장은 어텐드링크를 생성하여 팀원들을 초대할 수 있다.
  - 팀원들은 어텐드링크에서 정보를 입력하면 자신의 이메일로 **참여 코드**를 받는다.
- 초대받은 팀원들은 **로그인 없이** 참여 코드의 입력만으로 프로젝트에 참가할 수 있다.
- 참여 코드를 입력한 팀원은 자신의 프로젝트에 입장하여 프로젝트 진행도 및 팀원들 개인의 진행도를 확인할 수 있다.
  - 또한 팀원은 자신의 태스크를 컨트롤 할 수 있다.
- 자세한 설명은 여기로! 👉 [어플리케이션 사용법 링크](https://github.com/kakao-tech-campus-2nd-step3/Team1_BE/wiki/%EC%96%B4%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98-%EC%82%AC%EC%9A%A9%EB%B2%95)

예시 사진)
1. 랜딩 페이지
   ![1  랜딩 페이지](https://github.com/user-attachments/assets/070052c6-333d-49bc-8b6f-b272de5a8a9f)
2. 로그인 UI
   ![2  로그인 시 UI](https://github.com/user-attachments/assets/3d3e342e-7021-46da-8b40-fc1cf864edfc)
3. 프로젝트 참여시 UI
   ![4  프로젝트 참여(사이드 메뉴O)](https://github.com/user-attachments/assets/bf635f6c-299b-4f6c-9c7e-30a763ecd85f)


- 전체 프로토 타입은 여기로! [프로토타입 링크](https://www.figma.com/design/ZhOOxxb7yLfcJORzvXLFjh/%EC%99%80%EC%9D%B4%EC%96%B4%ED%94%84%EB%A0%88%EC%9E%84?node-id=15-7915&node-type=canvas&t=3Nv8MvV08K1XFntC-11)

---

# 기술스택

- **프론트 팀**
  - ![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![JS](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white) ![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB) ![NPM](https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white) ![visualcode](https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual)
- **백엔드 팀**
  - ![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![SPRING](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![MYSQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white) ![HIBERNATE](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) ![GMAIL](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white) ![intellij](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
- **배포**
  - ![GITHUB ACTION](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white) ![AMAZON AWS](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
- **디자인**
  - ![figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)
- **소통**
  - ![slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white) ![git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white) ![github](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)

---

# 프로젝트 구조도

![seamless drawio](https://github.com/user-attachments/assets/2bc76c2c-ac20-4d67-b746-f4fa2b064cec)
- 사용자는 브라우저나 클라이언트를 통해 HTTPS로 특정 기능(예: 로그인, 데이터 조회, 페이지 로드 등)을 요청합니다.
- 사용자 요청은 IP 주소를 기반으로 Nginx로 전달합니다.
  - JSON 데이터 요청
- 요청을 받은 백엔드에서 로그인, 데이터 저장/조회 요청 시, 사용자에게 적절한 응답(JSON 형식)을 반환합니다.
- 페이지 로드 시 필요한 데이터(예: 사용자 정보, 프로젝트 목록 등)는 백엔드에서의 API 응답 데이터를 바탕으로 화면을 구성하여 사용자와 상호작용할 수 있는 UI를 제공합니다.
- 백엔드와 프론트엔드는 각각 독립적으로 GitHub Actions를 통해 빌드 및 배포했습니다.
- 자세한 설명은 여기로! 👉 [아키텍처 설명 링크](https://github.com/kakao-tech-campus-2nd-step3/Team1_BE/wiki/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98%EC%97%90-%EB%8C%80%ED%95%9C-%EC%9E%90%EC%84%B8%ED%95%9C-%EC%84%A4%EB%AA%85)


---

# ERD

![KakaoTalk_20241112_183947117](https://github.com/user-attachments/assets/fcac1f9b-2f0f-4276-9481-47748648e7bf)


- **User 관리** 
  - 팀장의 로그인 및 CRUD
- **프로젝트 관리**
  - 프로젝트 CRUD 
- **멤버 관리**
  - 프로젝트에 참여하는 팀원 CRUD 
- **태스크 관리**
  - 프로젝트 내 작업 생성, 진행 상태 및 우선순위 등 CRUD
- 자세한 설명은 여기로! 👉 [ERD 설명 링크](https://github.com/kakao-tech-campus-2nd-step3/Team1_BE/wiki/ERD-%EC%84%A4%EB%AA%85)


---

# 협업 전략

- **브랜치 전략**
  1. (프론트, 백엔드 공통) 팀원들은 매주 weekly 브랜치를 받아서 자신의 이름 브랜치를 생성한다.
  2. 각자 금주의 작업이 완료되면, weekly 브랜치로 PR을 보낸다.
  3. weekly 브랜치에서 충돌을 해결한다.
  4. 충돌이 완료되면 develop 브랜치 -> master 브랜치 순으로 보낸다.
     - develop 브랜치가 CI/CD를 담당하는 브랜치이다.
  5. master 브랜치에서 review 브랜치로 멘토님께 리뷰를 요청하는 PR을 보낸다.
  6. 멘토님의 리뷰가 완료되면 해결 후 그 다음주 weekly 브랜치에 반영한다.
  7. 팀원들은 다시 weekly 브랜치를 받아서 똑같이 작업한다.


- **커밋 메세지 규칙(angular code conventions 기반)**
  - feat : 새로운 기능 추가
  - fix : 버그 수정
  - docs : 문서 변경
  - style : 코드 스타일 변경 (포매팅 수정, 세미콜론 추가 등)
  - refactor : 코드 리팩토링
  - test : 테스트 코드 추가, 수정
  - chore : 빌드 프로세스, 도구 설정 변경 등 기타 작업
  - 자세한 설명은 여기로! 👉 [커밋 메시지 규칙 링크](https://quickest-asterisk-75d.notion.site/P2P-d38e691fbcbb4a719274fb91e48f91cd?p=1d0fac986a2e48e5a4152524214084e7&pm=s)


- **API문서**
  - 백엔드가 개발해야할 API의 설명과 입출력 타입을 정의한다.
  - 자세한 설명은 여기로! 👉 [API 문서 링크](https://quickest-asterisk-75d.notion.site/P2P-d38e691fbcbb4a719274fb91e48f91cd?p=905fd6f9a8a140178580b5fd9593e0fe&pm=s)

---

# 백엔드 구현 기능

1. 프로젝트 관리 API
2. 팀원 관리 API
3. 태스크 관리 API
4. 이벤트 관련 API
   - 독려 이메일 전달 API
   - 각 팀원별 진행도 조회 API
   - 태스크별 진행도 조회 API
5. **로그인 및 인증관리 API**
   - **주안점을 두고 개발한 기능!**
   - 로그인 및 인증 플로우 사진)
![seamless 로그인 플로우차트 drawio](https://github.com/user-attachments/assets/0dd89459-af2a-42be-9a06-18791d4e3f1b)
![seamless 멤버 인증 플로우 차트 drawio](https://github.com/user-attachments/assets/030044e6-054a-4c12-b63e-044064ae5cbd)
   1. Google OAuth2 로그인 플로우
      - 사용자가 로그인 요청.
      - 백엔드 서버가 Google 인증 서버에 로그인 요청을 전달.
      - Google 인증 서버는 사용자 인증 후, 인증 코드를 백엔드 서버에 반환.
      - 백엔드 서버는 인증 코드로 엑세스 토큰을 요청하여 사용자 정보를 가져옴.
      - 사용자 정보로 회원가입 여부 확인 후 JWT 토큰을 발급하여 반환.
   2. 멤버 초대 및 참여 플로우
      - 초대 과정
      - 팀장이 백엔드에 멤버 초대 링크 생성 요청.
      - 백엔드에서 초대 링크를 생성해 팀장에게 반환.
      - 팀장이 초대 링크를 팀원에게 전달.
      - 참여 과정
      - 팀원이 초대 링크 접속 후, 이름과 이메일 입력.
      - 입력 정보로 참여 코드를 이메일로 수신.
      - 팀원이 참여 코드를 입력해 프로젝트에 참가.
      - 백엔드가 참여 코드 검증 후, JWT 토큰 발급 및 프로젝트 정보 반환.

- 더 자세한 설명은 여기로! 👉 [백엔드 구현 기능 링크](https://github.com/kakao-tech-campus-2nd-step3/Team1_BE/wiki/%EB%B0%B1%EC%97%94%EB%93%9C-%EA%B5%AC%ED%98%84-%EA%B8%B0%EB%8A%A5-%EC%83%81%EC%84%B8-%EC%84%A4%EB%AA%85-%ED%8E%98%EC%9D%B4%EC%A7%80)

---

# Technical Issue와 해결과정

- 각자 작업하다가 생긴 기술적인 이슈들에 대해서 협업으로 해결 및 공유하기 위해 **GitHub Issue**를 활용함.
- 다음 사진들은 기술적인 이슈들의 해결과정 예시 사진이다.
  ![스크린샷 2024-11-12 193537](https://github.com/user-attachments/assets/55fb2544-0547-4e1c-aa99-2deee1541767)
![스크린샷 2024-11-12 193624](https://github.com/user-attachments/assets/16174838-f3db-4161-98f6-54dda06d3e03)
- 자세한 설명은 여기로! 👉 [깃허브 이슈 링크](https://github.com/kakao-tech-campus-2nd-step3/Team1_BE/issues)

---

# 협업에 대한 이슈들 해결 

- 프론트 팀과의 협업에서는 다음의 사진과 같이 노션을 이용하여, 협업에 관한 이슈를 해결해나감.
- 다음은 프론트 팀으로부터 요청 사항을 받고 답변을 주고 받는 **협업**이 이루어지는 사진들이다.
![스크린샷 2024-11-12 194745](https://github.com/user-attachments/assets/c57ea804-562c-4855-bb28-29feaedf55c3)
![스크린샷 2024-11-12 194841](https://github.com/user-attachments/assets/8e61bab5-a27f-4251-a3fa-e51dd098e4f4)
- 자세한 설명은 여기로! 👉 [트랙 간 협업 과정 링크](https://polar-yellowhorn-1cd.notion.site/Swagger-241107-137a2fbcb2b180a0a67cf6906ab83ab8)

---

# 배포중인 인스턴스 주소

- 백엔드 : [URL]
- 프론트엔드 : [URL]
- 프론트엔드 GitHub 주소 : [프론트엔드 깃허브 링크](https://github.com/kakao-tech-campus-2nd-step3/Team1_FE)