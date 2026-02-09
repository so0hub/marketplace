drop database if exists boardservice;
create database boardservice;
use boardservice;

create table board(
bno int auto_increment ,
constraint primary key(bno),
writer varchar(30) not null,
pname longtext not null ,
pcontent longtext not null,
pprice int unsigned not null,
pwd varchar(10) not null,
phone varchar(20) not null,
datetime datetime default now(),
forsale bool default true
);

select * from board;

insert into board(writer,pname,pcontent,pprice,pwd,phone) values ('박소영','쳇바퀴','박진감이 안 써서 새 제품입니다.',10000,'1234','01012345678');
insert into board(writer,pname,pcontent,pprice,pwd,phone) values ('박주련','햄스터 숨숨집','박진감이 갉아먹기만 하고 안에서 안 자서 팝니다.',5000,'5678','01034565678');
insert into board(writer,pname,pcontent,pprice,pwd,phone) values ('정란희','완두콩 플레이크','박진감이 안 먹네요. 싸게 팝니다.',6000,'1234','01051475678');

select * from board;