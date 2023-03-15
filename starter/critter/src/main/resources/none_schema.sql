create table if not exists customer (
    id bigint auto_increment not null,
    name nvarchar(255),
    phonenumber nvarchar(255),
    notes nvarchar(255),
);

create table if not exists pets (
    id bigint auto_increment not null,
    type nvarchar(255),
    name nvarchar(255),
    ownerid bigint not null,
    birthday DATE,
    notes nvarchar(255),
);

create table if not exists schedule (
    id bigint auto_increment not null,
    employeeid bigint not null,
    petid bigint not null,
    date DATE
);

create table if not exists schedule_activities (
    scheduleid bigint not null,
    activity nvarchar(255)
);

create table if not exists employee (
    id bigint auto_increment not null,
    name nvarchar(255),
);

create table if not exists employee_details (
    employeeid bigint not null,
    skill nvarchar(255),
    daysAvailable nvarchar(255)
);