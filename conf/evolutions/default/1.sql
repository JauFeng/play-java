# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table test (
  user_name                     varchar(255),
  age                           integer
);

create table t_user_info (
  user_id                       bigint auto_increment not null,
  role_name                     varchar(50) not null,
  device_id                     varchar(128),
  phone_number                  varchar(20),
  mail_address                  varchar(128),
  password                      varchar(50),
  level                         integer(11),
  exp                           integer(11),
  signature                     varchar(256),
  portrait                      varchar(128),
  sex                           tinyint(11),
  birth_day                     datetime(6),
  role_create_time              datetime(6),
  address                       varchar(256),
  constraint pk_t_user_info primary key (user_id)
);


# --- !Downs

drop table if exists test;

drop table if exists t_user_info;

