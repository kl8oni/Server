create table DOCUMENT_TEMPLATES (
	DOCUMENT_TEMPLATE_ID      numeric(20, 0) not null,
	NAME                      varchar(50)    not null,
	SERIES_PATTERN            varchar(50)    not null,
	NUMBER_PATTERN            varchar(50)    not null,
	GOVERNMENT_OFFICE_TYPE_ID numeric(20, 0) not null,

	primary key (DOCUMENT_TEMPLATE_ID)
);