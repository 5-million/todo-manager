create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table tbl_todo (id bigint not null, cron varchar(255), message varchar(255), todo varchar(255), user_id varchar(255), primary key (id)) engine=InnoDB
create table tbl_user (user_id varchar(255) not null, platform varchar(255), webhook_url varchar(255), primary key (user_id)) engine=InnoDB
alter table tbl_todo add constraint FKggudr021qjk64ih12koc0apuh foreign key (user_id) references tbl_user (user_id)