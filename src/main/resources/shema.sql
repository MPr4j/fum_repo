CREATE TABLE if not exists users (
    id varchar(255) not null,
    username varchar(255) not null,
    password varchar(255) not null,
    fullname varchar(255) not null,
    email varchar(255) not null,
    city varchar(255) not null,
    phone_number varchar(255) not null,
    user_type varchar(255) not null -- Discriminator column
);

CREATE TABLE if not exists regular_users (
    user_id varchar(255) not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE if not exists business_owners (
    user_id varchar(255) not null,
    service_type varchar(255) not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE user_role (
    user_id VARCHAR(50),
    role VARCHAR(255),
    -- other columns as needed
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
