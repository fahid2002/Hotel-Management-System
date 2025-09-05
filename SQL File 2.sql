create database hotelmanagementsystem;

show databases;

use hotelmanagementsystem;

create table login(username varchar(25), password varchar(25));

insert into login values('admin','12345');

select * from login;

create table employee(name varchar (25), age varchar (25), gender varchar(15), job varchar(30), salary varchar(15), phone varchar (25), email varchar(40), aadhar varchar(20));

describe employee;

select * from employee;

create table room(room_number varchar(10), availability varchar(20), cleaning_status varchar(20), price varchar (20), bed_type varchar(20));

select * from room;

create table driver(name varchar(20), age varchar(10), gender varchar(20), company varchar (29),brand varchar (20), available varchar (20), location varchar (20));

select * from driver;

create table customer (document varchar (25), number varchar (25), name varchar(30), gender varchar(15), country varchar(15), room varchar (25), checkintime varchar(40), deposit varchar(20));

select * from customer;

create table department (department varchar(30), budget varchar(30));

insert into department values('Front Office','500000');
insert into department values('Housekeeping','40000');
insert into department values('Food and Beverage','25000');
insert into department values('kitchen and Food Production','540000');
insert into department values('Security','320000');
