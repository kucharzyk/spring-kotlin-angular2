
    alter table User_Role 
        drop constraint FK7qnwwe579g9frolyprat52l4d;

    alter table User_Role 
        drop constraint FKc52d1rv3ijbpu6lo2v3rej1tx;

    alter table User_UserGroup 
        drop constraint FKjm0hncrkstjukhunsw3joyatc;

    alter table User_UserGroup 
        drop constraint FKoemvh3b2apfvls9vsu63gp6kk;

    alter table UserGroup_Role 
        drop constraint FKfhbft3d9vbm9mqq2gxthimkw1;

    alter table UserGroup_Role 
        drop constraint FK39n818mnc83gkgct72fhtkp37;

    drop table if exists Role cascade;

    drop table if exists User cascade;

    drop table if exists User_Role cascade;

    drop table if exists User_UserGroup cascade;

    drop table if exists UserGroup cascade;

    drop table if exists UserGroup_Role cascade;

    drop sequence Sequence_Group;

    drop sequence Sequence_Role;

    drop sequence Sequence_User;

    create sequence Sequence_Group start 1 increment 1;

    create sequence Sequence_Role start 1 increment 1;

    create sequence Sequence_User start 1 increment 1;

    create table Role (
        id int8 not null,
        uuid varchar(255),
        createdBy int8,
        createdDate bytea,
        updatedBy int8,
        updatedDate bytea,
        version int8,
        active boolean not null,
        name varchar(255),
        primary key (id)
    );

    create table User (
        id int8 not null,
        uuid varchar(255),
        createdBy int8,
        createdDate bytea,
        updatedBy int8,
        updatedDate bytea,
        version int8,
        username varchar(255),
        primary key (id)
    );

    create table User_Role (
        User_id int8 not null,
        roles_id int8 not null,
        primary key (User_id, roles_id)
    );

    create table User_UserGroup (
        User_id int8 not null,
        userGroups_id int8 not null,
        primary key (User_id, userGroups_id)
    );

    create table UserGroup (
        id int8 not null,
        uuid varchar(255),
        createdBy int8,
        createdDate bytea,
        updatedBy int8,
        updatedDate bytea,
        version int8,
        active boolean not null,
        name varchar(255),
        primary key (id)
    );

    create table UserGroup_Role (
        UserGroup_id int8 not null,
        roles_id int8 not null,
        primary key (UserGroup_id, roles_id)
    );

    alter table User_Role 
        add constraint FK7qnwwe579g9frolyprat52l4d 
        foreign key (roles_id) 
        references Role;

    alter table User_Role 
        add constraint FKc52d1rv3ijbpu6lo2v3rej1tx 
        foreign key (User_id) 
        references User;

    alter table User_UserGroup 
        add constraint FKjm0hncrkstjukhunsw3joyatc 
        foreign key (userGroups_id) 
        references UserGroup;

    alter table User_UserGroup 
        add constraint FKoemvh3b2apfvls9vsu63gp6kk 
        foreign key (User_id) 
        references User;

    alter table UserGroup_Role 
        add constraint FKfhbft3d9vbm9mqq2gxthimkw1 
        foreign key (roles_id) 
        references Role;

    alter table UserGroup_Role 
        add constraint FK39n818mnc83gkgct72fhtkp37 
        foreign key (UserGroup_id) 
        references UserGroup;
