drop database if exists balance_accounts;
CREATE DATABASE balance_accounts;
USE balance_accounts;

create table if not exists FILE_DESCRIPTION(
	FILE_DESCRIPTION_ID int primary key auto_increment,
    BANK_NAME varchar(50) not null,
    PERIOD varchar(50) not null
);

create table if not exists FILES(
	FILE_ID int primary key auto_increment,
    FILE_NAME varchar(50) not null,
	FILE_DESCRIPTION_fk int not null,
	FOREIGN KEY (FILE_DESCRIPTION_fk) REFERENCES FILE_DESCRIPTION (FILE_DESCRIPTION_ID)
);

create table if not exists ACCOUNTS_GROUPS(
	ACCOUNTS_GROUP_ID int primary key default 999999,
    file_fk int not null,
	FOREIGN KEY (file_fk) REFERENCES FILES (FILE_ID)
);

create table if not exists CLASSES(
	CLASS_ID int primary key auto_increment,
    CLASS_NAME varchar(200) not null,
    file_fk int not null,
	FOREIGN KEY (file_fk) REFERENCES FILES (FILE_ID)
);

create table if not exists ACCOUNTS(
	ACCOUNT_ID int primary key default 999999,
    INPUT_BALANCE_ASSET double not null,
	INPUT_BALANCE_LIABILITY double not null,
    DEBIT double not null,
    CREDIT double not null,
    OUTGOING_BALANCE_ASSET double not null,
    OUTGOING_BALANCE_LIABILITY double not null,
	class_fk int not null,
	accounts_group_fk int not null,
    file_fk int not null,
	FOREIGN KEY (class_fk) REFERENCES CLASSES (CLASS_ID),
	FOREIGN KEY (accounts_group_fk) REFERENCES ACCOUNTS_GROUPS (ACCOUNTS_GROUP_ID),
	FOREIGN KEY (file_fk) REFERENCES FILES (FILE_ID)
);

select * from CLASSES;
select * from ACCOUNTS;
select * from ACCOUNTS_GROUPS;
select * from files;
select * from file_description;

select accounts_group_id from accounts_groups where file_fk = 1;


select sum(INPUT_BALANCE_ASSET), sum(INPUT_BALANCE_LIABILITY), sum(DEBIT), sum(CREDIT), sum(OUTGOING_BALANCE_ASSET), sum(OUTGOING_BALANCE_LIABILITY) from ACCOUNTS where accounts_group_fk = 10 and file_fk = 1;

