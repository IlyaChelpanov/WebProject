drop database autobase;
create database autobase;
use autobase;

create table roles(ID integer not null primary key auto_increment,
RoleName varchar(25));

create table users(
ID integer not null primary key auto_increment,
Username varchar(25) not null unique, 
FullName varchar(25) not null,
email varchar(35), 
Pass varchar(25) not null,
RoleID integer not null,
foreign key(RoleID) references roles(ID));

create table RaceStatus(
ID integer not null primary key auto_increment,
StatusName varchar(25) not null);

create table TypeOfRace(
ID integer not null primary key auto_increment,
TypeOfRace varchar(50));

create table Races(
RaceNumber  integer not null primary key auto_increment,
DateOfRide varchar(25) not null,
StartCity varchar(25) not null,
EndCity varchar(25) not null,
TimeOfArrive varchar(25) not null,
TypeOfRace integer not null,
foreign key (TypeOfRace) references TypeOfRace(ID),
RaceStatusID integer,
foreign key (RaceStatusID) references RaceStatus(ID));

create table Cars(
CarNumber integer not null primary key auto_increment,
Model varchar(30) not null,
MaxWeight integer,
MaxCapacity integer,
MaxSpeed integer,
CarCondition varchar(25) default "working");

create table RideStatus(
ID integer not null primary key auto_increment,
StatusName varchar(25));

create table Ride(
ID integer not null primary key auto_increment,
RaceNumber integer not null,
foreign key (RaceNumber) references Races(RaceNumber),
DriverID integer not null, 
foreign key (DriverID) references users(ID),
CarNumber integer not null,
foreign key (CarNumber) references Cars(CarNumber),
RideStatus integer,
foreign key (RideStatus) references RideStatus(ID));

create table BrokenCars(
CarNumber integer not null primary key,
foreign key (CarNumber) references Cars(CarNumber),
DescriptionOfBreakage varchar(300) not null);

create table chat(
ID integer not null primary key auto_increment,
UserID integer not null,
foreign key (UserID) references users(ID),
Date varchar(40),
Message varchar(100));

create table OrderStatus(
ID integer not null primary key auto_increment,
StatusName varchar(25));

create table Orders(
ID integer not null primary key auto_increment,
NumberOfRace integer not null,
foreign key(NumberOfRace) references Races(RaceNumber),
DriverID integer,
foreign key (DriverID) references users(ID),
RequiredWeight integer,
RequiredCapacity integer,
RequiredSpeed integer,
OrderStatusID integer,
foreign key (OrderStatusID) references OrderStatus(ID));