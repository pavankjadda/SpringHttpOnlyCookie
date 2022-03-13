-- Create Schema

create table if not exists SPRING_SESSION
(
  PRIMARY_ID            char(36)     not null
  primary key,
  SESSION_ID            char(36)     not null,
  CREATION_TIME         bigint       not null,
  LAST_ACCESS_TIME      bigint       not null,
  MAX_INACTIVE_INTERVAL int          not null,
  EXPIRY_TIME           bigint       not null,
  PRINCIPAL_NAME        varchar(100) null,
  constraint SPRING_SESSION_IX1
  unique (SESSION_ID)
  );

create index SPRING_SESSION_IX2
  on SPRING_SESSION (EXPIRY_TIME);

create index SPRING_SESSION_IX3
  on SPRING_SESSION (PRINCIPAL_NAME);

create table if not exists SPRING_SESSION_ATTRIBUTES
(
  SESSION_PRIMARY_ID char(36)     not null,
  ATTRIBUTE_NAME     varchar(200) not null,
  ATTRIBUTE_BYTES    blob         not null,
  primary key (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
  constraint SPRING_SESSION_ATTRIBUTES_FK
  foreign key (SESSION_PRIMARY_ID) references SPRING_SESSION (PRIMARY_ID)
  on delete cascade
  );

create table if not exists user
(
  id                      bigint auto_increment
  primary key,
  account_non_expired     bit          null,
  account_non_locked      bit          null,
  active                  bit          null,
  credentials_non_expired bit          null,
  email                   varchar(255) null,
  first_name              varchar(255) null,
  last_name               varchar(255) null,
  password                varchar(255) null,
  username                varchar(255) not null,
  constraint UK_sb8bbouer5wak8vyiiy4pf2bx
  unique (username)
  );


-- Insert
insert into user(email,username,first_name,last_name,password) values('demo_user@example.com','demo_user','Demo','User','$2a$12$NKu.jpUZZLBA7gEzroTgAuHBiFE6HMSyZs/SF.Xe./FO4FZEfSI/W');

-- docker exec -i mysql-container mysql -uroot -ptest12345 name_db < db.sql

