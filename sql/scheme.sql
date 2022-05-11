drop table if exists bid cascade ;
drop table if exists category cascade ;
drop table if exists client cascade ;
drop table if exists client cascade ;
drop table if exists login_credentials cascade ;
drop table if exists password_reset cascade ;
drop table if exists product_favourites cascade ;
drop table if exists user_favourites cascade ;
drop table if exists product cascade ;

create table category
(
    id          serial primary key,
    name        varchar(20)  not null,
    description varchar(250) not null
);

alter table category
    owner to postgres;

create table client
(
    id          serial primary key,
    name        varchar(50)  not null,
    last_name   varchar(50)  not null,
    address     varchar(100) not null,
    city        varchar(100) not null,
    birth_date  date         not null,
    gender      varchar(10)  not null
);

alter table client
    owner to postgres;

create table login_credentials
(
    username varchar(20) not null primary key,
    password varchar(60) not null,
    kind     varchar(10) not null,
    user_id  integer
        constraint user_fk
            references client (id)
            on delete cascade
);

alter table login_credentials
    owner to postgres;


create table password_reset
(
    username varchar(20) not null
        constraint login_credentials_fk
            references login_credentials(username)
            on delete cascade ,
    request_id varchar(20) not null,
    primary key (username,request_id)
);

alter table password_reset
    owner to postgres;

create table product
(
    id            serial primary key,
    title         varchar(50)      not null,
    description   varchar(250)     not null,
    out_price    double precision not null,
    images        varchar(100),
    close_date   date,
    publish_date timestamp        not null,
    vendor_id   integer not null
        constraint vendor_fk
            references client (id)
            on delete cascade ,
    category_id integer not null
        constraint category_fk
            references category (id)
            on delete cascade
);

alter table product
    owner to postgres;

create table user_favourites
(
    category_id integer not null
        constraint category_fk
            references category (id)
            on delete cascade ,
    user_id     integer not null
        constraint user_fk
            references client (id)
            on delete cascade ,
    primary key (category_id, user_id)
);

alter table user_favourites
    owner to postgres;

create table product_favourites
(
    product_id integer not null
        constraint product_id_fk
            references product (id)
            on delete cascade ,
    user_id     integer not null
        constraint user_fk
            references client (id)
            on delete cascade ,
    primary key (product_id, user_id)
);

alter table product_favourites
    owner to postgres;

create table bid
(
    id           serial,
    publish_date timestamp not null ,
    amount        double precision not null,
    product_id    integer not null
        constraint product_fk
            references product (id)
            on delete cascade ,
    user_id       integer not null
        constraint user_fk
            references client (id)
            on delete cascade ,
    primary key (id,product_id, user_id)
);

alter table bid
    owner to postgres;
