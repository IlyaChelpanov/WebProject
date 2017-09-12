use autobase;
delete from cars;
insert into Cars values(default, "MersedesBenz", 500, 6, 190, default);
insert into Cars values(default, "Камаз-200", 3000, 2, 130, default);
insert into Cars values(default, "Hyundai HD-78", 500, 50,160, default);
insert into Cars values(default, "Aerobus K-300", 500, 45,120, default);
insert into Cars values(default, "Volvo 1251", 400, 6,170, "broken");

insert into TypeOfRace values(default, "Cargo, lesser then 5t.");
insert into TypeOfRace values(default, "Cargo, from 5t. to 10t.");
insert into TypeOfRace values(default, "Cargo, from 10t. to 15t.");
insert into TypeOfRace values(default, "Passenger, lesser then 10 people.");
insert into TypeOfRace values(default, "Passenger, from 10 to 25 people.");
insert into TypeOfRace values(default, "Passenger, from 25 to 50 people.");

delete from brokencars;
insert into brokencars values(5, 
"Критическая поломка двигателя.");

insert into roles values(default, "administrator");
insert into roles values(default, "dispatcher");
insert into roles values(default, "driver");

delete from users;
insert into users values(default, "admin", "Chelpanov Ilya", "ilychelpanovv@gmail.com",
"11e8419d", 1);
insert into users values(default, "dispatcherI", "Bolakevsky Denis", "bolak@mail.ru", "12341234",
2);
insert into users values(default, "dispatcherII", "Mykhailova Anna", "mychann@mail.ru", "12341234",
2);
insert into users values(default, "driverI", "Donald Trump", "president@gmail.com", "12341234",
3);
insert into users values(default, "driverII", "Sonar Jenkins", "jenkins@gmail.com", "12341234",
3);
insert into users values(default, "driverIII", "Thaur Aleks", "thaus@gmail.com", "12341234",
3);
insert into RaceStatus values(default, "FREE");
insert into RaceStatus values(default, "ENGAGED");
insert into RaceStatus values(default, "DECLINED");

insert into RideStatus values(default, "Waiting for execution");
insert into RideStatus values(default, "Executed");

insert into OrderStatus values(default, "CONFIRMED");
insert into OrderStatus values(default, "NOT CONFIRMED");
insert into OrderStatus values(default, "DENIED");
delete from races;
insert into races values(default, "31.01.2017", "Kramatorsk", "Kharkov", "07:00", 6, 1);
insert into races values(default, "02.02.2017", "Chernigov", "Kyiv", "09:00", 5, 1);
insert into races values(default, "24.01.2017", "Moskow", "Chelyabinsk", "09:00", 5, 1);