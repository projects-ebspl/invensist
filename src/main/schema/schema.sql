create table Users (
	id int not null auto_increment,
	firstname varchar(64),
	lastname varchar(64),
	email varchar(128),
	phone varchar(32),
	address text,
	primary key (id)
);
insert into Users values (1,"Admin","admin","admin@einsicht.com","1234","----"); 
create table Roles (
	id int not null auto_increment,
	name varchar(64),
	description text,
	primary key (id)
);
insert into Roles values (1, "Admin", "Administrator");
insert into Roles values (2, "Planner", "Planning invoices");
insert into Roles values (3, "User", "Regular user");
