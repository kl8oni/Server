create table DOCUMENTS (
	DOCUMENT_ID          numeric(20, 0) not null,
	NAME                 varchar(50)    not null,
	SERIES               varchar(10)    not null,
	NUMBER               bigint         not null,
	DELIVERY_DATE        timestamp      not null,
	USER_ID              numeric(20, 0) not null,
	GOVERNMENT_OFFICE_ID numeric(20, 0) not null,
	DOCUMENT_TEMPLATE_ID numeric(20, 0) not null,
	IDENTIFY_DOCUMENT    boolean        not null,

	primary key (DOCUMENT_ID)
);
