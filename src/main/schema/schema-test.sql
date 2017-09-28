create table Users (
	id int not null auto_increment,
	firstname varchar(64),
	lastname varchar(64),
	email varchar(128),
	phone varchar(32),
	address text,
	password text,
	roleAdmin tinyint,
	rolePlanner tinyint,
	roleUser tinyint,
	primary key (id),
	constraint uk_user_email unique (email)
);
insert into Users values (1,"Admin","admin","admin@einsicht.com","1234","----","admin123",1,1,1);
insert into Users values (2,"Mayuresh","Halshikar","mayuresh@einsicht.com","1234","----","mayuresh123",1,1,1);
create table Roles (
	id int not null auto_increment,
	name varchar(64),
	description text,
	primary key (id)
);
insert into Roles values (1, "Admin", "Administrator");
insert into Roles values (2, "Planner", "Planning invoices");
insert into Roles values (3, "User", "Regular user");
create table Stores (
	id int not null auto_increment,
	name varchar(64) not null,
	type enum ('regular','rejection','assembly','wastage','shortage'),
	primary key (id),
	constraint uk_name unique (name)
);
insert into Stores values (1, "Main", "regular");
insert into Stores values (2, "Reject-1", "rejection");
insert into Stores values (3, "Assembly-1", "assembly");
insert into Stores values (4, "Wastage-1", "wastage");
insert into Stores values (5, "Shortage-1", "shortage");
create table InvoiceTax	(
	id int not null auto_increment,
	name varchar(32) not null,
	percentage double,
	primary key (id)
);
insert into InvoiceTax values(1, "cgst", 9);
insert into InvoiceTax values(2, "sgst", 9);
create table Item (
	id int not null auto_increment,
	code varchar(32),
	description text,
	itemcost double,
	assemblycost double,
	type enum ('single', 'combo'),
	primary key (id),
	constraint uk_item_code unique (code)
);
insert into Item values (1, "S001", "Test-1", 40, null, "single");
insert into Item values (2, "S002", "Test-2", 35, null, "single");
insert into Item values (3, "S003", "Test-3", 37, null, "single");
insert into Item values (4, "C001", "TestC-1", 87, 5, "combo");
insert into Item values (5, "C002", "TestC-2", 104, 7, "combo");
insert into Item values (6, "C003", "TestC-3", 146, 13, "combo");

create table ComboItemBreakUp (
	comboitemId int not null,
	childitemid int not null,
	childitemquantity int not null,
	constraint fk_comboitemId foreign key (comboitemId) references Item(id),
	constraint fk_childitemid foreign key (childitemid) references Item(id)
);	
create table Account (
	id int not null auto_increment,
	name varchar(64) not null,
	address text,
	phone varchar(32),
	email varchar(64),
	primary key (id)
);
create table Orders (
	id int not null auto_increment,
	client int not null,
	created datetime,
	due datetime,
	primary key (id),
	constraint fk_client_in_orders foreign key (client) references Account(id)
);
create table OrderItem(
	orderid int not null,
	itemid int not null,
	quantity int not null,
	primary key (orderid, itemid),
	constraint fk_order_in_orderitem foreign key (orderid) references Orders(id),
	constraint fk_item_in_orderitem foreign key (itemid) references Item(id)
);
create table AssemblyInvoice (
	id int not null auto_increment,
	orderid int not null,
	vendour int not null,
	due datetime,
	invoicecost double default	 0,
	totalcost double default 0,
	confirm int,
	confirmmessage text,
	primary key (id),
	constraint fk_order_in_invoiceitem foreign key (orderid) references Orders(id),
	constraint fk_vendour_in_invoiceitem foreign key (vendour) references Account(id)
);
create table AssemblyInvoiceItem(
	invoice int not null,
	item int not null,
	quantity int not null,
	primary key (invoice, item),
	constraint fk_invoice_in_assemblyinvoiceitem foreign key (invoice) references AssemblyInvoice(id),
	constraint fk_item_in_assemblyinvoiceitem foreign key (item) references Item(id)
);
create table AssemblyInvoiceTax	(
	invoice int not null,
	tax int not null,
	primary key (invoice, tax),
	constraint fk_invoice_in_assemblyinvoicetax foreign key (invoice) references AssemblyInvoice(id),
	constraint fk_tax_in_assemblyinvoicetax foreign key (tax) references InvoiceTax(id)
);
create table Inventory (
	store int not null,
	item int not null,
	inventory int not null,
	primary key (store, item),
	constraint fk_store_in_inventory foreign key (store) references Stores(id),
	constraint fk_item_in_inventory foreign key (item) references Item(id)
);
create table Transactions (
	datetime datetime not null default now(),
	fromstore int not null,
	tostore int not null,
	item int not null,
	quantity int not null,
	primary key (datetime, fromstore, tostore, item),
	constraint fk_fromstore_in_transactions foreign key (fromstore) references Stores(id),
	constraint fk_tostore_in_transactions foreign key (tostore) references Stores(id),
	constraint fk_item_in_transactions foreign key (item) references Item(id)
);