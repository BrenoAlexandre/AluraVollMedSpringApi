create table medics(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    specialty varchar(100) not null,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    postalCode varchar(9) not null,
    city varchar(100) not null,
    state varchar(100) not null,
    number varchar(100),
    complement varchar(100),

    primary key(id)
);