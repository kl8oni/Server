alter table BANK_ACCOUNTS
add constraint USER_ACCOUNT_FK
foreign key (USER_ID)
references USERS (USER_ID);

alter table BANK_ACCOUNTS
add constraint BANK_ACCOUNT_FK
foreign key (BANK_ID)
references BANKS (BANK_ID);

alter table BANK_ACCOUNTS
add constraint BANK_BRANCH_ACCOUNT_FK
foreign key (BANK_BRANCH_ID)
references BANK_BRANCHES (BANK_BRANCH_ID);

alter table BANK_BRANCHES
add constraint BANK_BANK_BRANCH_FK
foreign key (BANK_ID)
references BANKS (BANK_ID);

alter table DOCUMENT_TEMPLATES
add constraint OFFICE_TYPE_DOCUMENT_TEMPLATE_FK
foreign key (GOVERNMENT_OFFICE_TYPE_ID)
references GOVERNMENT_OFFICE_TYPES (GOVERNMENT_OFFICE_TYPE_ID);

alter table DOCUMENTS
add constraint USER_DOCUMENT_FK
foreign key (USER_ID)
references USERS (USER_ID);

alter table DOCUMENTS
add constraint OFFICE_DOCUMENT_FK
foreign key (OFFICE_ID)
references GOVERNMENT_OFFICES (GOVERNMENT_OFFICE_ID);

alter table DOCUMENTS
add constraint DOCUMENT_TEMPLATE_DOCUMENT_FK
foreign key (DOCUMENT_TEMPLATE_ID)
references DOCUMENT_TEMPLATES (DOCUMENT_TEMPLATE_ID);

alter table GOVERNMENT_OFFICE_TYPES
add constraint OFFICE_TYPE_PARENT_FK
foreign key (PARENT_ID)
references GOVERNMENT_OFFICE_TYPES (GOVERNMENT_OFFICE_TYPE_ID);

alter table GOVERNMENT_OFFICES
add constraint OFFICE_TYPE_OFFICE_FK
foreign key (GOVERNMENT_OFFICE_TYPE_ID)
references GOVERNMENT_OFFICE_TYPES (GOVERNMENT_OFFICE_TYPE_ID);

alter table USER_EMAILS
add constraint USER_EMAIL_FK
foreign key (USER_ID)
references USERS (USER_ID);

alter table USERS
add constraint IDENTIFY_DOCUMENT_USER_FK
foreign key (IDENTIFY_DOCUMENT_ID)
references DOCUMENTS (DOCUMENT_ID);