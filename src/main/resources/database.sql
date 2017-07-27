create database finastratest;

use finastratest;

create table customer (customerID integer primary key, name varchar(30), address varchar(50), birthday date);

create table account (accountNumber integer primary key, balance double, currency varchar(3), customer integer, foreign key (customer) references customer(customerID));

insert into customer values (1, 'Roger Moore', 'randomAddress', '1988-02-03');

insert into account values (1234, 200000, 'HUF', 1);