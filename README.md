# 베이직 신입 직원 교육 프레임웍

> 빠르고 쉽게 시작할 수 있는 일반적인 형태의 MVC 3 Layer 프레임워크

#### 기본 기능 요약

+ 라이브러리: SpringBoot, SpringMVC, MyBaits, Bootstrap3, Freemarker, Apache Shiro,Lombok
+ 기본 CRUD 및 Paging, XSS, SQL injection prevent 포함


#### 데이터베이스 설정
> MySQL의 db1, db2 다중 데이타소스 지원
+ mysql에 db1, db2 스키마 생성
+ db1에서 src/main/resources/db/mysql/db1.sql 실행
+ db2에서 src/main/resources/db/mysql/db2.sql 실행
+ src/main/resources/application.yml 파일에서 DB연결 정보 수정
```yaml
spring:
  datasource:
      datasource:
        db1:
          username: root
          password: root
          url: 'jdbc:mysql://localhost:3306/db1?useUnicode=true&characterEncoding=UTF-8&useSSL=false'
        db2:
          username: root
          password: root
          url: 'jdbc:mysql://localhost:3306/db2?useUnicode=true&characterEncoding=UTF-8&useSSL=false'
``` 

#### Lombok 설정(IntelliJ, Eclipse)
> 참고: https://www.baeldung.com/lombok-ide

#### 프로젝트 시작
+ 프로젝트를 IDE에 Import
+ com.basicit.Application에서 메인 메소드 실행
+ http://localhost:8090 접속, 로그인 계정: admin/123456

#### 프로젝트 링크
+ [프로젝트 홈](https://basic.jetbrains.space/p/baisc-newbie-study)   
+ [이슈관리](https://basic.jetbrains.space/p/baisc-newbie-study/issue-boards/%EC%8B%A0%EC%9E%85%EA%B5%90%EC%9C%A1%201%EC%B0%A8/1%EC%B0%A8%20%EC%8A%A4%ED%94%84%EB%A6%B0%ED%8A%B8)    
+ [문서관리](https://basic.jetbrains.space/p/baisc-newbie-study/documents/all)
+ [소스코드](https://basic.jetbrains.space/p/baisc-newbie-study/repositories/study_spring_1/)   
+ [프로젝트 멤버](https://basic.jetbrains.space/p/baisc-newbie-study/people/team)
  