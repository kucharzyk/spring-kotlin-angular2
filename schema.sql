
    alter table jwt_token 
        drop constraint FKrma6363nvuvw4npx6xidhqeov;

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

    drop table if exists jwt_token cascade;

    drop table if exists role cascade;

    drop table if exists user cascade;

    drop table if exists user_roles cascade;

    drop table if exists user_user_groups cascade;

    drop table if exists user_group cascade;

    drop table if exists user_group_roles cascade;

    drop sequence sequence_jwt_token;

    drop sequence sequence_role;

    drop sequence sequence_user;

    drop sequence sequence_user_group;

    create sequence sequence_jwt_token start 1 increment 1;

    create sequence sequence_role start 1 increment 1;

    create sequence sequence_user start 1 increment 1;

    create sequence sequence_user_group start 1 increment 1;

    create table jwt_token (
        id int8 not null,
        uuid varchar(255) not null,
        created_by int8,
        created_date timestamp not null,
        updated_by int8,
        updated_date timestamp,
        version int8 not null,
        active boolean not null,
        expiration_date timestamp not null,
        invalidated boolean not null,
        invalidation_date timestamp,
        ip_adress varchar(39) not null,
        user_id int8 not null,
        primary key (id)
    );

    create table role (
        id int8 not null,
        uuid varchar(255) not null,
        created_by int8,
        created_date timestamp not null,
        updated_by int8,
        updated_date timestamp,
        version int8 not null,
        active boolean not null,
        name varchar(250) not null,
        primary key (id)
    );

    create table user (
        id int8 not null,
        uuid varchar(255) not null,
        created_by int8,
        created_date timestamp not null,
        updated_by int8,
        updated_date timestamp,
        version int8 not null,
        activated boolean not null,
        email varchar(120) not null,
        first_name varchar(120) not null,
        last_name varchar(120) not null,
        password varchar(512) not null,
        username varchar(250) not null,
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
        uuid varchar(255) not null,
        created_by int8,
        created_date timestamp not null,
        updated_by int8,
        updated_date timestamp,
        version int8 not null,
        active boolean not null,
        name varchar(250) not null,
        primary key (id)
    );

    create table user_group_roles (
        user_group_id int8 not null,
        roles_id int8 not null,
        primary key (user_group_id, roles_id)
    );

    alter table role 
        add constraint UK_8sewwnpamngi6b1dwaa88askk unique (name);

    alter table user 
        add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

    alter table user_group 
        add constraint UK_kas9w8ead0ska5n3csefp2bpp unique (name);

    alter table jwt_token 
        add constraint FKrma6363nvuvw4npx6xidhqeov 
        foreign key (user_id) 
        references user;

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
