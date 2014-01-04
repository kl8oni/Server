create table USER_EMAILS (
	USER_EMAIL_ID numeric(20, 0) not null,
	EMAIL_ADDRESS varchar(50)    not null,
	USER_ID       numeric(20, 0) not null,
	MAIN_EMAIL    boolean        not null,

	primary key (USER_EMAIL_ID)
);