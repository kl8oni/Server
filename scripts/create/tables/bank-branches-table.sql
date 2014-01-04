create table BANK_BRANCHES (
	BANK_BRANCH_ID   numeric(20, 0) not null,
	NAME             varchar(50)    not null,
	STATE            varchar(50)    not null,
	CITY             varchar(50)    not null,
	STREET           varchar(50)    not null,
	BUILDING_NUMBER  numeric(3, 0)  not null,
	TELEPHONE_NUMBER varchar(20)    not null,
	BANK_ID          numeric(20, 0) not null,

	primary key (BANK_BRANCH_ID)
);