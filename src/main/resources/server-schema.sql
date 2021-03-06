create table cycle_dairy_db.access_token_store
(
	token_id varchar(40) not null,
	access_token varchar(100) not null,
	member_id varchar(100) not null,
	constraint access_token_store_pk
		primary key (token_id)
);

create table cycle_dairy_db.cycle_member
(
	MEMBER_ID varchar(100) not null
		primary key,
	PASSWORD varchar(100) not null,
	EMAIL varchar(100) not null,
	REGISTER_DATE datetime not null,
	LAST_LOGIN_DATE datetime not null,
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
