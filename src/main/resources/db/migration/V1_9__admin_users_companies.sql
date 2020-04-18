create sequence  companies_id_sequence;

create table companies (

    id BIGINT PRIMARY KEY,
    created_at DATE,
    vat_number VARCHAR(20),
    address VARCHAR(2048),
    name VARCHAR(2048),
    blocked Boolean
);

CREATE SEQUENCE admin_users_id_sequence;

CREATE TABLE admin_users (
id BIGINT PRIMARY KEY ,
email VARCHAR(255) UNIQUE NOT NULL,
password VARCHAR(255),
first_name VARCHAR(255),
last_name VARCHAR(255),
token VARCHAR(255),
role VARCHAR(24) NOT NULL,
company_id BIGINT NOT NULL DEFAULT (1)
);

INSERT INTO admin_users (id, email, password, role, company_id,first_name,last_name)
VALUES (nextval('admin_users_id_sequence'), 'admin', 'password', 'COMPANY_ADMIN', 1, 'first_name','last_name');

INSERT INTO companies (id, vat_number, name, address, blocked)
VALUES (nextval('companies_id_sequence'), 'admin', 'password', 'COMPANY_ADMIN', false);