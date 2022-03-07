CREATE TABLE if not exists accident (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE if not exists types (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE if not exists rules (
    id serial primary key,
    name varchar(2000)
);

insert into accident (id, name) values (1, 'acc1');
insert into accident (id, name) values (2, 'acc2');
insert into accident (id, name) values (3, 'acc3');

insert into types (id, name) values (1, 'Две машины');
insert into types (id, name) values (2, 'Машина и человек');
insert into types (id, name) values (3, 'Машина и велосипед');

insert into rules (id, name) values (1, 'Статья. 1');
insert into rules (id, name) values (2, 'Статья. 2');
insert into rules (id, name) values (3, 'Статья. 3');



