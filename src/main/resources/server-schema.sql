create table cycle_member
(
    MEMBER_ID varchar(100) not null
        primary key,
    PASSWORD varchar(100) not null,
    EMAIL varchar(100) not null,
    constraint CYCLE_MEMBER_EMAIL_uindex
        unique (EMAIL)
);

create table dairy_record
(
    DAIRY_RECORD_SEQ int auto_increment
        primary key,
    MEMBER_ID varchar(100) not null,
    START_LOCATION_NAME varchar(50) not null,
    START_LOCATION_LAT double not null,
    START_LOCATION_LON double not null,
    END_LOCATION_NAME varchar(50) not null,
    END_LOCATION_LAT double not null,
    END_LOCATION_LON double not null,
    DAIRY_DATE datetime default CURRENT_TIMESTAMP null
);
