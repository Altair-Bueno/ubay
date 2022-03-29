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
    password varchar(20) not null,
    kind     varchar(10) not null,
    user_id  integer
        constraint user_fk
            references client (id)
);

alter table login_credentials
    owner to postgres;

create table product
(
    id            serial primary key,
    title         varchar(50)      not null,
    description   varchar(250)     not null,
    out_price    double precision not null,
    images        varchar(100)   not null,
    close_date   date,
    publish_date timestamp        not null,
    vendor_id   integer not null
        constraint vendor_fk
            references client (id),
    category_id integer not null
        constraint category_fk
            references category (id)
);

alter table product
    owner to postgres;

create table user_favourites
(
    category_id integer not null
        constraint category_fk
            references category (id),
    user_id     integer not null
        constraint user_fk
            references client (id),
    primary key (category_id, user_id)
);

alter table login_credentials
    owner to postgres;

create table bid
(
    publish_date timestamp not null ,
    amount        double precision not null,
    product_id    integer not null
        constraint product_fk
            references product (id),
    user_id       integer not null
        constraint user_fk
            references client (id),
    primary key (product_id, user_id)
);

alter table bid
    owner to postgres;

