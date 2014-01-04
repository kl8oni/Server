create table USERS (
	USER_ID              numeric(20, 0) not null,
	LAST_NAME            varchar(50)    not null,
	FIRST_NAME           varchar(50)    not null,
	MIDDLE_NAME          varchar(50)    not null,
	NICK_NAME            varchar(50)    not null,
	PASSWORD             varchar(32)    not null,
	IDENTIFY_DOCUMENT_ID numeric(20, 0) not null,

	primary key (USER_ID)
);