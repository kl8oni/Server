create table GOVERNMENT_OFFICE_TYPES (
	GOVERNMENT_OFFICE_TYPE_ID numeric(20, 0) not null,
	NAME                      varchar(50)    not null,
	PARENT_ID                 numeric(20, 0) not null,

	primary key (GOVERNMENT_OFFICE_TYPE_ID)
);