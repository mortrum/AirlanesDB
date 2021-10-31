INSERT INTO Countries VALUES (0, 'Canada');
INSERT INTO Countries VALUES (1, 'Russia');
INSERT INTO Countries VALUES (2, 'Ukraine');

INSERT INTO Airports VALUES (0, 'Vancouver', 0);
INSERT INTO Airports VALUES (1, 'Winnipeg', 0);
INSERT INTO Airports VALUES (2, 'Khrabrovo', 1);
INSERT INTO Airports VALUES (3, 'Murmansk', 1);
INSERT INTO Airports VALUES (4, 'Boryspil', 2);
INSERT INTO Airports VALUES (5, 'Kyiv', 2);

INSERT INTO Airplanes VALUES (0);
INSERT INTO Airplanes VALUES (1);
INSERT INTO Airplanes VALUES (2);
INSERT INTO Airplanes VALUES (3);
INSERT INTO Airplanes VALUES (4);
INSERT INTO Airplanes VALUES (5);

INSERT INTO Pilots VALUES (0, 'Jackson', 1037, 0, TRUE);
INSERT INTO Pilots VALUES (1, 'Jackson', 1072, 1), TRUE;
INSERT INTO Pilots VALUES (2, 'Jackson', 2073, 2), TRUE;
INSERT INTO Pilots VALUES (3, 'Jackson', 7053, 3, TRUE);
INSERT INTO Pilots VALUES (4, 'Jackson', 5037, 4, TRUE);
INSERT INTO Pilots VALUES (5, 'Jackson', 6061, 5, TRUE);

INSERT INTO Flights VALUES (0, '2021-10-10 12:00:00', 0, 0, 1);
INSERT INTO Flights VALUES (1, '2021-10-8 15:20:00', 1, 1, 2);
INSERT INTO Flights VALUES (2, '2021-10-13 17:30:00', 2, 2, 3);
INSERT INTO Flights VALUES (3, '2021-10-15 20:15:00', 3, 3, 4);
INSERT INTO Flights VALUES (4, '2021-10-11 11:00:00', 4, 4, 5);