
DROP TABLE IF EXISTS  Pilots;
DROP TABLE IF EXISTS  Airplanes;
DROP TABLE IF EXISTS  Airports;
DROP TABLE IF EXISTS  Countries;
DROP TABLE IF EXISTS  Flights;
DROP TABLE IF EXISTS  Aviators;
DROP TABLE IF EXISTS  Testing;

CREATE TABLE  Airplanes (
                        id INT NOT NULL
);

CREATE TABLE  Pilots (
                         id INT NOT NULL,
                         name VARCHAR (50) NOT NULL,
                         flight_hours INT NOT NULL,
                         airplane_id INT NOT NULL,
                         is_working BOOLEAN NOT NULL
);

CREATE TABLE  Airports (
                        id INT NOT NULL,
                        name VARCHAR (50),
                        country_id INT NOT NULL
);

CREATE TABLE  Countries (
                        id INT NOT NULL,
                        name VARCHAR (50)
);

CREATE TABLE  Flights (
                        id INT NOT NULL,
                        date DATETIME NOT NULL,
                        airplane_id INT NOT NULL,
                        departure_airport_id INT NOT NULL,
                        destination_airport_Id INT NOT NULL
);