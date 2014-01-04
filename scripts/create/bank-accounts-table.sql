create table BANK_ACCOUNTS (
	BANK_ACCOUNT_ID numeric(20, 0) not null,
	ACCOUNT_NUMBER  numeric(20, 0) not null,
	USER_ID         numeric(20, 0) not null,
	BANK_ID         numeric(20, 0) not null,
	BANK_BRANCH_ID  numeric(20, 0) not null,

	primary key (BANK_ACCOUNT_ID)
);