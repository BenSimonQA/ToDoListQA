drop table if exists `task` CASCADE;
drop table if exists `todo` CASCADE;
 
create table if not exists todo (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null, description varchar(255) not null);
create table if not exists task (id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255) not null, difficulty varchar(255) not null, todo_id bigint);
