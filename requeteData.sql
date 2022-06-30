insert into user (username,pwd) values ('admin','admin');
insert into region values('R002','TUNIS');
insert into structure values('STR001','structure1', 'R002');
insert into Service values('SER01','Finance', 'STR001');
insert into PositionDeTravail values('P001','Directeur', 'SER01');
insert into Employe values('EMP001','Foued', 'LAMINE', '02/02/2000','P001');

INSERT INTO sirh_laposte.heursupp (id, employee, date, type) VALUES (1, 'km23', '2022-06-25', 'dimanche');
INSERT INTO sirh_laposte.heursupp (id, employee, date, type) VALUES (4, 'EMP001', '2022-06-12', 'dimanche');
INSERT INTO sirh_laposte.heursupp (id, employee, date, type) VALUES (5, 'EMP001', '2022-06-05', 'dimanche');
INSERT INTO sirh_laposte.heursupp (id, employee, date, type) VALUES (6, 'KM001', '2022-06-05', 'dimanche');
INSERT INTO sirh_laposte.heursupp (id, employee, date, type) VALUES (8, 'km23', '2022-06-05', 'dimanche');
INSERT INTO sirh_laposte.heursupp (id, employee, date, type) VALUES (9, 'T001', '2022-06-12', 'dimanche');
INSERT INTO sirh_laposte.heursupp (id, employee, date, type) VALUES (10, 'km23', '2022-06-19', 'dimanche');

select matricule,nom,prenom,date,postion,nom_service,nom_structure,nom_region from employe join positiondetravail p on p.id_PositionDeTravail = employe.id_PositionDeTravail join service s on s.id_service = p.id_service join structure s2 on s2.id_structure = s.id_structure join region r on r.id_region = s2.id_region;