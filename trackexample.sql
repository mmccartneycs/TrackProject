DROP DATABASE IF EXISTS tracksDB;
CREATE DATABASE tracksDB;

USE tracksDB;

CREATE TABLE tracks(
    id INT Primary Key AUTO_INCREMENT,
    name VARCHAR(50),
    city varchar(30),
    state varchar(30),
    zip int(5),
    owner varchar(30)
);

insert into tracks(id, name, city, state, zip, owner) values
('1', 'Arlington International Racecourse', 'Chicago','Illinois' , '60004' , 'Churchill Downs Inc.'),
('2', 'Churchill Downs', 'Louisville','Kentucky' , '40208' , 'Churchill Downs Inc.'),
('3', 'Pimlico Race Course', 'Baltimore','Maryland' , '21215' , 'Stronach Group'),
('4', 'Belmont Park', 'Elmont','New York' , '92109' , 'State of New York'),
('5', 'Colonial Downs', 'New Kent County','Virginia' , '23124' , 'Churchill Downs Inc.')
;
select * from tracks;