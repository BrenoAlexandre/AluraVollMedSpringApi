CREATE TABLE patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    postalCode varchar(9) not null,
    city varchar(100) not null,
    state varchar(100) not null,
    number varchar(100),
    complement varchar(100),
    phone varchar(20) not null,
    active boolean not null default true,

    primary key(id)
)