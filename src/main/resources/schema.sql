create table if not exists Services (
    id varchar(4) primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Photo_Session (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists Photo_Session_Services (
    photoSession bigint not null,
    services varchar(4) not null
);

alter table Photo_Session_Services
    add foreign key (photoSession) references Photo_Session(id);
alter table Photo_Session_Services
    add foreign key (services) references Services(id);

create table if not exists Photo_Session_Order (
    id identity,
    name varchar(50) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(8) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists Photo_Session_Order_Photo_Sessions (
    photoSessionOrder bigint not null,
    photoSession bigint not null
);

alter table Photo_Session_Order_Photo_Sessions
    add foreign key (photoSessionOrder) references Photo_Session_Order(id);
alter table Photo_Session_Order_Photo_Sessions
    add foreign key (photoSession) references Photo_Session(id);