select * from tab;

create sequence memo_no_seq
start with 1
increment by 1;

create table memo
(no number(10) constraint memo_no_pk primary key,
 memo varchar2(4000),
 writer varchar2(100),
 password varchar2(100),
 wdate date);
 
-- 공지게시판의 글번호로 사용할 객체를 생성합니다.
create sequence board_no_seq
start with 1
increment by 1;

-- 공지게시판 테이블을 생성합니다.
create table board
(no number(10) constraint board_no_pk primary key,
 title varchar2(100),
 notice varchar2(4000),
 writer varchar2(20),
 password varchar2(20),
 wdate date,
 ref number(10));

 
 -- 답글형 게시판
create sequence board2_no_seq
start with 1
increment by 1;

create table board2
(no number(10) constraint board2_no_pk primary key,
 title varchar2(100),
 content varchar2(4000),
 writer varchar2(20),
 password varchar2(20),
 wdate date,
 ref number(10),
 group_no number(10),
 sequence_in_group number(10),
 indent_in_group number(10),
 ref_no number(10));

create index board2_page_idx
on board2(group_no desc, sequence_in_group asc);


-- 덧글형 게시판
create sequence board3_no_seq
start with 1
increment by 1;

create table board3
(no number(10) constraint board3_no_pk primary key,
 title varchar2(200),
 content varchar2(4000),
 writer varchar2(40),
 password varchar2(40),
 ref number(10),
 reply number(10),
 wdate date);

create sequence reply_no_seq
start with 1
increment by 1;

create table reply
(no number(10) constraint reply_no_pk primary key,
 writer varchar2(40),
 password varchar2(40),
 memo varchar2(4000),
 wdate date,
 ref_no number(10));
 
 
--  답글 및 덧글형 게시판
create sequence board4_no_seq
start with 1
increment by 1;

create table board4
(no number(10) constraint board4_no_pk primary key,
 writer varchar2(40),
 password varchar2(40),
 title varchar2(200),
 content varchar2(4000),
 wdate date,
 read_cnt number(10),
 reply_cnt number(10),
 group_no number(10),
 sequence_in_group number(10),
 indent_in_group number(10),
 ref_no number(10));
 
create index board4_page_idx on board4(group_no desc, sequence_in_group asc);

create sequence reply2_no_seq
start with 1
increment by 1;

create table reply2
(no number(10) constraint reply2_no_pk primary key,
 writer varchar2(40),
 password varchar2(40),
 memo varchar2(4000),
 wdate date,
 ref_no number(10));
