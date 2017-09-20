create table Users (
	id int not null auto_increment,
	firstname varchar(64),
	lastname varchar(64),
	email varchar(128),
	phone varchar(32),
	address text,
	password text,
	primary key (id)
);
insert into Users values (1,"Admin","admin","admin@einsicht.com","1234","----","admin123"); 
create table Roles (
	id int not null auto_increment,
	name varchar(64),
	description text,
	primary key (id)
);
insert into Roles values (1, "Admin", "Administrator");
insert into Roles values (2, "Planner", "Planning invoices");
insert into Roles values (3, "User", "Regular user");
create table UserRoleMapping (
	user int not null,
	role int not null,
	primary key (user, role),
	constraint fk_user foreign key (user) references Users(id),
	constraint fk_role foreign key (role) references Roles(id)
);
insert into UserRoleMapping values (1,1);
create table Stores (
	id int not null auto_increment,
	name varchar(64) not null,
	type enum ('regular','rejection','assembly','wastage','shortage'),
	primary key (id),
	constraint uk_name unique (name)
);
create table InvoiceTax	(
	id int not null auto_increment,
	name varchar(32) not null,
	percentage double,
	primary key (id)
);
create table Item (
	id int not null auto_increment,
	code varchar(32),
	description text,
	itemcost double,
	assemblycost double,
	type enum ('single', 'combo'),
	tax int,
	primary key (id),
	constraint fk_tax foreign key (tax) references InvoiceTax(id)
);
create table ComboItemBreakUp (
	comboitemId int not null,
	childitemid int not null,
	childitemquantity int not null,
	constraint fk_comboitemId foreign key (comboitemId) references Item(id),
	constraint fk_childitemid foreign key (childitemid) references Item(id)
);	
create table Vendour (
	id int not null auto_increment,
	name varchar(64) not null,
	address text,
	phone varchar(32),
	email varchar(64),
	primary key (id)
);
create table VendourItem (
	vendour int not null,
	item int not null,
	primary key (vendour, item),
	constraint fk_vendour foreign key (vendour) references Vendour(id),
	constraint fk_item_in_vendouritem foreign key (item) references Item(id)
);
create table Client (
	id int not null auto_increment,
	name varchar(64) not null,
	address text,
	phone varchar(32),
	email varchar(64),
	primary key (id)
);
create table ClientItem (
	client int not null,
	item int not null,
	primary key (client, item),
	constraint fk_client foreign key (client) references Client(id),
	constraint fk_item_in_clientitem foreign key (item) references Item(id)
);
create table Orders (
	id int not null auto_increment,
	client int not null,
	created datetime,
	due datetime,
	primary key (id),
	constraint fk_client_in_orders foreign key (client) references Client(id)
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
	constraint fk_vendour_in_invoiceitem foreign key (vendour) references Vendour(id)
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