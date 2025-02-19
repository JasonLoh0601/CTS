drop table if exists price_aggregation cascade
drop table if exists transaction_history cascade
drop table if exists user_wallet cascade
drop table if exists users cascade
drop sequence if exists transaction_history_seq
drop sequence if exists user_wallet_seq
drop sequence if exists users_seq
create sequence transaction_history_seq start with 1 increment by 50
create sequence user_wallet_seq start with 1 increment by 50
create sequence users_seq start with 1 increment by 50
create table price_aggregation (ask float(53), bid float(53), ask_from varchar(255), bid_from varchar(255), symbol varchar(255) not null, primary key (symbol))
create table transaction_history (price float(53),total_price float(53), quantity float(53), created_dttm timestamp(6), id bigint not null, user_id bigint not null, symbol varchar(255), type varchar(255), primary key (id))
create table user_wallet (balance float(53), id bigint not null, updated_dttm timestamp(6), user_id bigint not null, private_key varchar(255), public_key varchar(255), symbol varchar(255), primary key (id))
create table users (id bigint not null, email varchar(255) not null unique, password varchar(255) not null, phone_number varchar(255), username varchar(255) not null unique, primary key (id))

