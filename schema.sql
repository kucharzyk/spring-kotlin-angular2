
    drop table hibernate_sequence;

    drop table USERS;

    create table hibernate_sequence (
        next_val numeric(19,0)
    );

    insert into hibernate_sequence values ( 1 );

    create table USERS (
        id numeric(19,0) not null,
        uuid varchar(255),
        createdBy numeric(19,0),
        createdDate varbinary(255),
        updatedBy numeric(19,0),
        updatedDate varbinary(255),
        version numeric(19,0),
        role varchar(255),
        username varchar(255),
        primary key (id)
    );
