-- 관리자로 접속하여 필요한 계정을 만듬.
sqlplus / as sysdba

create user user1 identified by 1234;
grant connect, resource to user1;


-- user1로 접속하여 필요한 객체를 생성
sqlplus user1/1234

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