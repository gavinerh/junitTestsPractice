create table if not exists persons
(
	id varchar(100) not null,
	name varchar(100) not null,
	email varchar(100) not null,
	primary key (id)
);