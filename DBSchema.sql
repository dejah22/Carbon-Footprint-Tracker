
-- CARBON FOOTPRINT CALCULATION
 
-- TRAVEL (wrt distance travelled) (note that kg ce stands for kilogram carbon equivalent)

-- Petrol car: 0.192 kg ce/km
-- Diesel car: 0.12 kg ce/km
-- Bus: 1.3 kg ce/km
-- Bicycle: 0.021 kg ce/km
-- Air: 0.115 kg ce/passenger/km
-- Train: 0.041 kg ce/km

-- HOME

-- Electricity: 0.95 kg ce/kWh (this is for coal plants let’s assume everyone has coal plant electricity)
-- LPG cylinder: net weight = 14.2 kg 
-- 				14.2/25 = 0.568 kg
-- 				2.983 kg ce/kg => 2.983*0.568 = 1.694 kg ce (per day)
-- (Per person) 1.694/4 = 0.4235 kg ce
-- Kerosene: 2.5 kg ce/l
-- Wood: 1.65 kg ce/kg
-- Water: 10.6 kg ce/m^3 (1 m^3 = 1000 l => 0.0106 kg ce/l)


-- FOOD (everything in kg of food consumed)

-- Meat: (assume chicken) 10 kg ce/kg
-- Grains: (assume rice) 1.6 kg ce/kg
-- Dairy: (assume just milk) 2.4 kg ce/kg
-- Snacks: (assume potato chips) 2.8 kg ce/kg
-- Fruits and Vegetables: (assume veggies alone) 2 kg ce/kg


create database register;

use register;

create table authority
(
	name varchar(50),
	auth_id varchar(4),
	password varchar(20),
	primary key(auth_id)
);

insert into authority values ("One", "a001", "au001!");
insert into authority values ("Two", "a002", "au002!");
insert into authority values ("Three", "a003", "au003!");

create table user
(
	name varchar(50),
	email varchar(50),
	age int,
	gender varchar(10),
	address varchar(200),
	password varchar(20),
	feedback varchar(200),
	auth_id varchar(50),
	primary key(email),
	foreign key (auth_id) references authority (auth_id)
);

-- ADD DATE IN THE NEXT 3 TABLES GUYS!!!!!!

create table travel
(
	travel_id int auto_increment,
	email varchar(50),
	mode varchar(50),
	distance float, 
	duration float,
	fuel varchar(50),
	travel_sum float,
	today date,
	primary key(travel_id),
	foreign key (email) references user (email)
);

create table home
(
	home_id int auto_increment,
	email varchar(50),
	electricity float,
	water float,
	gas float,
	home_sum float,
	today date,
	primary key(home_id),
	foreign key (email) references user (email)
);

create table food
(
	food_id int auto_increment,
	email varchar(50),
	meat float,
	grains float,
	dairy float,
	snacks float,
	fruits_veggies float,
	food_sum float,
	today date,
	primary key(food_id),
	foreign key (email) references user (email)
);

create table carbon_footprint
(
	email varchar(50),
	day date,
	travel_sum float,
	home_sum float,
	food_sum float,
	footprint float,
	feedback varchar(20),
	primary key(email, day),
	foreign key (email) references user (email)
);

-- CALC FUNCTIONS

delimiter |
drop function if exists get_travel |
create function get_travel ()
returns float
deterministic
begin
	declare total float;
	select sum(travel_sum) into total from travel where today = (select curdate()) group by(today);
	return total;
end;
|

delimiter |
drop function if exists get_home |
create function get_home ()
returns float
deterministic
begin
	declare total float;
	select sum(home_sum) into total from home where today = (select curdate()) group by(today);
	return total;
end;
|

delimiter |
drop function if exists get_food |
create function get_food ()
returns float
deterministic
begin
	declare total float;
	select sum(food_sum) into total from food where today = (select curdate()) group by(today);
	return total;
end;
|

-- AFTER THIS USE ; | AS THE DELIMITER

insert into carbon_footprint values("hi@gmail.com", "2023-04-30",  get_travel(),  get_home(), get_food(), NULL,  NULL);