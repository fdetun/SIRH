drop TABLE IF EXISTS sirh_laposte.user;
create Table user
(
    id int auto increment ,
    username      varchar(50),
    pwd      varchar(50),

    primary key (id)

);

drop TABLE IF EXISTS sirh_laposte.Region;

create Table Region
(
    id_region varchar(50) NOT NULL,
    nom_region      varchar(50),
    primary key (id_region)

);


drop TABLE IF EXISTS sirh_laposte.structure;

create Table structure
(
    id_structure varchar(50) NOT NULL,
    nom_structure       varchar(50),
    id_region varchar(50),
    foreign key (id_region) references Region(id_region) on delete cascade on update cascade,
    primary key (id_structure)


);



drop TABLE IF EXISTS sirh_laposte.Service;

create Table Service
(
    id_service varchar(50) NOT NULL,
    nom_service      varchar(50),
    id_structure varchar(50),
    foreign key (id_structure) references structure(id_structure) on delete cascade on update cascade,
    primary key (id_service)
);


drop TABLE IF EXISTS sirh_laposte.PositionDeTravail;

create Table PositionDeTravail
(
    id_PositionDeTravail varchar(50) NOT NULL,
    Postion      varchar(50),
    id_service varchar(50),
    foreign key (id_service) references Service(id_service) on delete cascade on update cascade,
    primary key (id_PositionDeTravail)

);


drop TABLE IF EXISTS sirh_laposte.Employe;
create Table Employe
(
    matricule varchar(50) NOT NULL,
    nom       varchar(50),
    prenom    varchar(50),
    date      varchar(50),

    id_PositionDeTravail varchar(50),
    foreign key (id_PositionDeTravail) references PositionDeTravail(id_PositionDeTravail) on delete cascade on update cascade,
    primary key (matricule)

);

create table heursupp
(
    id       int auto_increment
        primary key,
    employee varchar(50) not null,
    date     date        not null,
    type     varchar(50) null,
    constraint employee___fk
        foreign key (employee) references employe (matricule)
);

