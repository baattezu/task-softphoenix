create table users(
    id serial primary key ,
    username varchar(255) not null ,
    password varchar(255) not null ,
    f_name varchar(255),
    l_name varchar(255),
    patronymic varchar(255),
    avatar_url varchar(255)
)