CREATE TABLE imgbbs ( 
  no      NUMBER(7)     NOT NULL,  -- 글 일련 번호, -9999999 ~ +9999999 
  name    VARCHAR(20)   NOT NULL,  -- 글쓴이 
  title   VARCHAR(100)  NOT NULL,  -- 제목(*) 
  content VARCHAR(4000) NOT NULL,  -- 글 내용 
  passwd  VARCHAR(15)   NOT NULL,  -- 비밀 번호 
  viewcnt NUMBER(5)     DEFAULT 0, -- 조회수, 기본값 사용 
  wdate   DATE          NOT NULL,  -- 등록 날짜, sysdate 
  grpno   NUMBER(7)     DEFAULT 0, -- 부모글 번호 
  indent  NUMBER(2)     DEFAULT 0, -- 답변여부,답변의 깊이
  ansnum  NUMBER(5)     DEFAULT 0, -- 답변 순서 
  filename VARCHAR(30)  DEFAULT 'default.jpg', --이미지
  PRIMARY KEY (no)  
); 

INSERT INTO imgbbs(no, name, title, content, passwd, wdate, grpno)
VALUES((SELECT NVL(MAX(no),0)+1 as no FROM imgbbs),  
'르브론', '캐브스', '우승', '1234', sysdate,
(SELECT NVL(MAX(grpno),0) + 1 FROM imgbbs)
);

INSERT INTO imgbbs(no, name, title, content, passwd, wdate, grpno)
VALUES((SELECT NVL(MAX(no),0)+1 as no FROM imgbbs),  
'웨이드', '불스', '우승', '1234', sysdate,
(SELECT NVL(MAX(grpno),0) + 1 FROM imgbbs)
);

INSERT INTO imgbbs(no, name, title, content, passwd, wdate, grpno)
VALUES((SELECT NVL(MAX(no),0)+1 as no FROM imgbbs),  
'앤서니', '닉스', '우승', '1234', sysdate,
(SELECT NVL(MAX(grpno),0) + 1 FROM imgbbs)
);

INSERT INTO imgbbs(no, name, title, content, passwd, wdate, grpno)
VALUES((SELECT NVL(MAX(no),0)+1 as no FROM imgbbs),  
'지단', '레알', '우승', '1234', sysdate,
(SELECT NVL(MAX(grpno),0) + 1 FROM imgbbs)
);

INSERT INTO imgbbs(no, name, title, content, passwd, wdate, grpno)  
VALUES((SELECT NVL(MAX(no), 0) + 1 as no FROM imgbbs), 
'�մ���', '����', '����', '123', sysdate,
(SELECT NVL(MAX(grpno),0) + 1 FROM imgbbs)
);    



--read
select*from imgbbs
order by no desc

--read
select no, filename,name,gender,address1,adress2,addrecode,skill,hobby
from imgbbs
order by no desc


--delete
delete from imgbbs
where no =1