DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS disbursment;

create table payment (
    id bigint not null AUTO_INCREMENT,
    amount decimal(19,2) not null,
    status varchar(200) default 'NEW',
    user_id bigint,
    create_ts timestamp,
    last_ts timestamp,
    primary key (id)); 

create table user (
    id bigint not null AUTO_INCREMENT,
    name varchar(200),
    create_ts timestamp,
    last_ts timestamp,
    primary key(id)); 
    
create table disbursment (
    id bigint not null AUTO_INCREMENT,
    amount decimal(19,2) not null,
    payment_id bigint,
    user_id bigint,
    create_ts timestamp,
    last_ts timestamp,
    primary key (id)); 