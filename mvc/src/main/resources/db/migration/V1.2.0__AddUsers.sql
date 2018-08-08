CREATE SEQUENCE users_id_seq;
create table Users (
    id bigint not null DEFAULT NEXTVAL('users_id_seq'),
    email varchar(255) not NULL UNIQUE,
    username varchar(255) not NULL UNIQUE,
    first_name varchar(255),
    last_name varchar(255),
    avatar_url varchar(255),
    password varchar(255) not NULL,
    confirm_password varchar(255) not NULL,
    confirm_token varchar(255),

    create_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP DEFAULT NULL,
    confirm_at TIMESTAMP DEFAULT NULL,
    last_login_at TIMESTAMP DEFAULT NULL,
    last_reset_at TIMESTAMP DEFAULT NULL,
    confirm_status integer DEFAULT 0,
    timezone character varying(32) DEFAULT NULL,
    primary key (id)
);
ALTER SEQUENCE users_id_seq OWNED BY Users.id;