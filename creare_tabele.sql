drop database if exists platforma_studiu;
create database platforma_studiu;
use platforma_studiu;

CREATE TABLE utilizator (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tip ENUM('Student', 'Profesor', 'Administrator', 'Super-Administrator') NOT NULL,
    CNP VARCHAR(13) NOT NULL,
    nume VARCHAR(50) NOT NULL,
    prenume VARCHAR(50) NOT NULL,
    numar_telefon VARCHAR(10),
    email VARCHAR(50),
    cont_IBAN VARCHAR(50),
    numar_contract INT
);

CREATE TABLE autentificare (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    parola VARCHAR(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES utilizator(id)
);

CREATE TABLE student (
    id INT PRIMARY KEY,
    an INT,
    nr_ore INT,
    FOREIGN KEY (id) REFERENCES utilizator(id)
);

CREATE TABLE profesor (
    id INT PRIMARY KEY,
    nr_ore_min INT,
    nr_ore_max INT,
    departament VARCHAR(50),
    FOREIGN KEY (id) REFERENCES utilizator(id)
);

CREATE TABLE administrator (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES utilizator(id)
);

CREATE TABLE super_administrator (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES utilizator(id)
);

CREATE TABLE curs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descriere VARCHAR(200),
    max_studenti INT
);


create table if not exists curs_profesor (
    curs_id int not null ,
    profesor_id int not null,
    foreign key (curs_id) references curs(id),
    foreign key (profesor_id) references profesor(id),
    primary key (curs_id,profesor_id)
);

CREATE TABLE curs_nota (
    curs_id INT,
    student_id INT,
    nota INT,
    PRIMARY KEY (curs_id, student_id),
    FOREIGN KEY (curs_id) REFERENCES curs(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE TABLE activitate (
    id INT PRIMARY KEY AUTO_INCREMENT,
    curs_id INT,
    tip varchar(50) NOT NULL,
    pondere INT,
    FOREIGN KEY (curs_id) REFERENCES curs(id)
);

CREATE TABLE calendar (
    activitate_id INT PRIMARY KEY,
    data_inceput DATETIME,
    data_sfarsit DATETIME,
    FOREIGN KEY (activitate_id) REFERENCES activitate(id)
);

CREATE TABLE activitate_nota (
    activitate_id INT,
    student_id INT,
    nota INT,
    PRIMARY KEY (activitate_id, student_id),
    FOREIGN KEY (activitate_id) REFERENCES activitate(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE TABLE grup (
    id INT PRIMARY KEY AUTO_INCREMENT,
    curs_id INT,
    min_participanti INT,
    expira DATETIME,
    FOREIGN KEY (curs_id) REFERENCES curs(id)
);

create table grup_activitate(
    grup_id int not null,
    activitate_id int not null,
    foreign key (grup_id) references grup(id),
    foreign key (activitate_id)references activitate(id),
    primary key (grup_id,activitate_id)
);

create table grup_studenti(
    grup_id int not null,
    student_id int not null,
    foreign key (grup_id)references grup(id),
    foreign key  (student_id)references student(id),
    primary key (grup_id,student_id)
);
CREATE TABLE mesaj (
    id INT PRIMARY KEY AUTO_INCREMENT,
    grup_id INT,
    student_id INT,
    continut VARCHAR(250),
    FOREIGN KEY (grup_id) REFERENCES grup(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);
drop table administrator;
drop table super_administrator;

ALTER TABLE curs_nota
RENAME TO curs_student;

drop table curs_profesor;

create table if not exists activitate_profesor (
    activitate_id int not null ,
    profesor_id int not null,
    foreign key (activitate_id) references activitate(id),
    foreign key (profesor_id) references profesor(id),
    primary key (activitate_id,profesor_id)
);
alter table curs_student
    drop column nota;
alter table calendar
    drop foreign key calendar_ibfk_1;

alter table calendar
    drop primary key;

alter table calendar
    add unique (activitate_id);

alter table calendar
    add constraint calendar___fk
        foreign key (activitate_id) references activitate (id);

alter table calendar
    drop foreign key calendar___fk;

drop index activitate_id on calendar;

create index activitate_id
    on calendar (activitate_id);

alter table calendar
    add constraint calendar___fk
        foreign key (activitate_id) references activitate (id);


CREATE PROCEDURE CheckStudentActivity(
    IN student_id INT,
    IN datetime1 DATETIME,
    IN datetime2 DATETIME
)
BEGIN
    SELECT
        COUNT(*) > 0 AS hasActivity
    FROM student s
    JOIN activitate_nota an ON s.id = an.student_id
    JOIN calendar c ON an.activitate_id = c.activitate_id
    WHERE s.id = student_id
      AND c.data_inceput <= datetime2
      AND c.data_sfarsit >= datetime1
    GROUP BY an.activitate_id;
END;

create trigger delete_student
    AFTER DELETE ON student
FOR EACH ROW
BEGIN

    DELETE FROM activitate_nota
    WHERE student_id = OLD.id;

    DELETE FROM curs_student
    WHERE student_id = OLD.id;

    delete from autentificare
    where autentificare.id=OLD.id;

    delete from grup_studenti
    where grup_studenti.student_id=OLD.id;

END;

create trigger delete_profesor
    before delete on profesor
for each row
    begin

        delete from activitate_profesor
        where activitate_profesor.profesor_id=OLD.id;


    end;

create trigger delete_autentificare
    before delete on utilizator
for each row
    begin
        delete from autentificare
            where autentificare.id=OLD.id;
    end;

create view all_profesors as
    select * from utilizator where tip = 'Profesor';

create view all_studenti as
    select * from utilizator where tip = 'Student';