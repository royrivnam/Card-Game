--liquibase formatted sql
--changeset akash:1

create table gameplay(
    id serial primary key not null,
    player_one integer,
    player_two integer,
    cards jsonb,
    status varchar(20),
    winner integer,
    card_remaining jsonb,
    player_one_moves jsonb,
    player_two_moves jsonb,
    turn integer,
    timer integer,
    created_at timestamp not null,
    updated_at timestamp not null
);

create table card(
    id serial primary key not null,
    suit varchar(20),
    color varchar(20),
    value varchar(2),
    hierarchy integer,
    created_at timestamp not null,
    updated_at timestamp not null
);

create table player(
    id serial primary key not null,
    name varchar(20),
    score integer,
    level integer,
    icon text,
    status varchar(20),
    created_at timestamp not null,
    updated_at timestamp not null
);

CREATE INDEX IF NOT EXISTS index_on_player_name ON player(name);

--rollback drop table gameplay;
--rollback drop table card;
--rollback drop table player;
