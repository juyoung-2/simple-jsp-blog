create table main_t (
    m_idx number constraint main_t_pk primary key, 
    writer varchar2(10) not null, 
    title varchar2(100) not null, 
    content clob, 					
    hit number,
    filename varchar2(100),
    reg_date date
);

create sequence main_seq start with 1 increment by 1;


create table comm_t (
    c_idx number constraint comm_t_pk primary key, 
    writer varchar2(10) not null,  
    content clob, 					
    reg_date date,
    m_idx number not null,
    constraint comm_t_fk foreign key (m_idx) references main_t (m_idx)
);


create sequence comm_seq start with 1 increment by 1;

create table user_t(
    u_idx number primary key,
    u_id VARCHAR(30) not null unique,
    uPw VARCHAR(30) not null,
    uName VARCHAR(30) not null,
    uEmail VARCHAR(30) not null,
    uRegDate date
);

create sequence user_seq start with 1 increment by 1;

ALTER TABLE comm_t
MODIFY writer VARCHAR2(30);


