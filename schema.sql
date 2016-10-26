
    alter table user_roles 
        drop constraint FKj9553ass9uctjrmh0gkqsmv0d;

    alter table user_roles 
        drop constraint FK55itppkw3i07do3h7qoclqd4k;

    alter table user_user_groups 
        drop constraint FKcyk8vtwqqmtm3xv1onm1r81ke;

    alter table user_user_groups 
        drop constraint FKddywflvu1fntus1ch9v9opcew;

    alter table user_group_roles 
        drop constraint FK99vs3uto0r291mfbq6028xib1;

    alter table user_group_roles 
        drop constraint FKhf7dawmx36sxv21p86y3q3ym;

    drop table if exists role cascade;

    drop table if exists user cascade;

    drop table if exists user_roles cascade;

    drop table if exists user_user_groups cascade;

    drop table if exists user_group cascade;

    drop table if exists user_group_roles cascade;

    drop sequence sequence_role;

    drop sequence sequence_user;

    drop sequence sequence_user_group;

    create sequence sequence_role start 1 increment 1;

    create sequence sequence_user start 1 increment 1;

    create sequence sequence_user_group start 1 increment 1;

    create table role (
        id int8 not null,
        uuid varchar(255),
        created_by int8,
        created_date bytea,
        updated_by int8,
        updated_date bytea,
        version int8,
        active boolean not null,
        name varchar(255),
        primary key (id)
    );

    create table user (
        id int8 not null,
        uuid varchar(255),
        created_by int8,
        created_date bytea,
        updated_by int8,
        updated_date bytea,
        version int8,
        username varchar(255),
        primary key (id)
    );

    create table user_roles (
        user_id int8 not null,
        roles_id int8 not null,
        primary key (user_id, roles_id)
    );

    create table user_user_groups (
        user_id int8 not null,
        user_groups_id int8 not null,
        primary key (user_id, user_groups_id)
    );

    create table user_group (
        id int8 not null,
        uuid varchar(255),
        created_by int8,
        created_date bytea,
        updated_by int8,
        updated_date bytea,
        version int8,
        active boolean not null,
        name varchar(255),
        primary key (id)
    );

    create table user_group_roles (
        user_group_id int8 not null,
        roles_id int8 not null,
        primary key (user_group_id, roles_id)
    );

    alter table user_roles 
        add constraint FKj9553ass9uctjrmh0gkqsmv0d 
        foreign key (roles_id) 
        references role;

    alter table user_roles 
        add constraint FK55itppkw3i07do3h7qoclqd4k 
        foreign key (user_id) 
        references user;

    alter table user_user_groups 
        add constraint FKcyk8vtwqqmtm3xv1onm1r81ke 
        foreign key (user_groups_id) 
        references user_group;

    alter table user_user_groups 
        add constraint FKddywflvu1fntus1ch9v9opcew 
        foreign key (user_id) 
        references user;

    alter table user_group_roles 
        add constraint FK99vs3uto0r291mfbq6028xib1 
        foreign key (roles_id) 
        references role;

    alter table user_group_roles 
        add constraint FKhf7dawmx36sxv21p86y3q3ym 
        foreign key (user_group_id) 
        references user_group;
