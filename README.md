# survey_project_java

### <주제>
좋아하는 영화 장르  
1. 액션  
2. 멜로  
3. 코미디  
4. 공포  
5. 판타지  
6. 0. 기타  

### <요구사항 정의 : 어떤 결과가 구현되어야 하는가>
- 설문참여 / 설문현황 선택지   
- 선택(숫자) 입력 공간  
- 설문 참여 선택 시 목록보기 및 투표(숫자 선택)할 수 있도록  
- 설문 현황 선택 시 투표 현황 / 총 투표 수 확인할 수 있도록  
- 설문 참여할 때 '기타' 선택 시 직접 입력  
- 직접 입력 시 투표 현황에 입력된 내용 / 투표 수 추가  
- 설문 종료 버튼  
- 종료 버튼 누르면 프로그램 종료  


### <데이터베이스 모델링 : 결과 동작을 위해 필요한 데이터가 무엇인가>
- SURVEY 테이블  
NUMBER(번호) / LIST (영화 장르) / VOTE (투표 수)

- OTHER 테이블  
NUMBER(번호) / INPUT (직접 입력 값)


### <콘솔 출력 포멧 설계(UI) : 결과를 어떤 형태로 보여줄 것인가>
- 자바 콘솔 형태  
- "★좋아하는 영화 장르 설문조사★"  
- 번호, 장르명 세로로 나열  
- 맨 밑 줄 "선택" 입력 공간  
- 투표 완료 후 "설문 완료!" 출력 후 다시 설문참여 / 설문현황 선택지 나오도록  
- 현황보기 완료 후 다시 설문참여 / 설문현황 선택지 나오도록  
- 종료 시 "설문조사가 종료되었습니다."  

### <기능 설계 : 결과 동작을 위한 클래스를 어떻게 정의할 것인가>

1. SurveyVo : 설문 현황 선택 시 나오는 목록을 위한 클래스, 투표 수 반영  
int number, String list, int vote

2. SurveyDao  
selectAll(); - number, list, vote 모두 출력하는 메서드  
counter(); - 번호를 매개변수로 설정하여 선택된 번호의 vote 수가 1씩 증가하는 메서드  
sumVote(); - 총 투표수를 출력하는 메서드  

3. OtherVo : 기타 선택 시 직접 입력한 값을 따로 처리하기 위한 클래스  
int number, String input

4. OtherDao    
selectOther(); - 투표 목록에 '기타' 항목을 따로 추가하기 위한 메서드  
counter(); - 직접 입력한 값을 매개변수로 설정하여 그 값의 vote 수가 1 증가하도록 하는 메서드  
insertSurvey(); - '기타' 선택 시 직접 입력한 값이 "SURVEY"테이블에 추가되는 메서드  
 
5. JdbcTemplate : DB와 JAVA 연동  
7. Main  

----
### <테이블 만들기>
create table "SURVEY" (  
"NUMBER" number primary key,  
"LIST" varchar2(100) not null,  
"VOTE" number  
);  

create table "OTHER" (  
"LIST_NUMBER" number,  
"INPUT" varchar2(100)  
);  


### <SEQUENCE 생성>
create sequence "SEQ_SURVEY" nocache;  


### <값 삽입>
insert into "SURVEY" values ("SEQ_SURVEY".nextval, '액션', 0);  
insert into "SURVEY" values ("SEQ_SURVEY".nextval, '멜로', 0);  
insert into "SURVEY" values ("SEQ_SURVEY".nextval, '코미디', 0);  
insert into "SURVEY" values ("SEQ_SURVEY".nextval, '공포', 0);  
insert into "SURVEY" values ("SEQ_SURVEY".nextval, '판타지', 0);  
