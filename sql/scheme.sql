create table example
(
    number serial
        constraint example_pk
            primary key
);

alter table example
    owner to postgres;

create unique index example_number_uindex
    on example (number);

