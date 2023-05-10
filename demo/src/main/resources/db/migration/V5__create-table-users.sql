CREATE TABLE users(
    id bigint not null auto_increment,
    login varchar(100) unique not null,
    password varchar(255) not null,

    primary key(id)
);