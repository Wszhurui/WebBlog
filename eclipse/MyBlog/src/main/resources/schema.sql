create table if not exists t_user(
id int not null auto_increment,
name varchar(128) not null,
password varchar(128) not null,
primary key (id)
);

create table if not exists t_article(
id int not null auto_increment,
title varchar(128) not null,
content varchar(1000) not null,
primary key (id)
);


