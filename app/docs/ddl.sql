drop table if exists myapp_users restrict;
drop table if exists myapp_boards restrict;
drop table if exists myapp_projects restrict;
drop table if exists myapp_project_members restrict;

create table myapp_users (
  user_id int not null,
  name varchar(20) not null,
  email varchar(100) not null,
  pwd varchar(100) not null,
  tel varchar(20)
);

alter table myapp_users
  add constraint primary key (user_id),
  modify column user_id int not null auto_increment,
  add constraint myapp_users_uk unique (email);

alter table myapp_users
    add column photo varchar(100);

create table myapp_boards (
  board_id int not null,
  title varchar(255) not null,
  content text not null,
  user_id int not null,
  created_date datetime default now(),
  view_count int default 0
);

alter table myapp_boards
  add constraint primary key (board_id),
  modify column board_id int not null auto_increment;

alter table myapp_boards
  add constraint myapp_boards_fk foreign key (user_id) references myapp_users(user_id);

create table myapp_board_files (
    board_file_id int not null,
    filename varchar(255) not null,
    origin_filename varchar(255) null,
    board_id int not null
);

alter table myapp_board_files
    add constraint primary key (board_file_id),
    modify column board_file_id int not null auto_increment,
    add constraint myapp_board_files_fk foreign key (board_id) references myapp_boards(board_id);

create table myapp_projects (
  project_id int not null,
  title varchar(255) not null,
  description text not null,
  start_date date not null,
  end_date date not null
);

alter table myapp_projects
  add constraint primary key (project_id),
  modify column project_id int not null auto_increment;

create table myapp_project_members (
  user_id int not null,
  project_id int not null
);

alter table myapp_project_members
  add constraint myapp_project_members_fk1 foreign key (user_id) references myapp_users (user_id),
  add constraint myapp_project_members_fk2 foreign key (project_id) references myapp_projects (project_id),
  add constraint primary key (user_id, project_id);









