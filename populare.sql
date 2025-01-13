use platforma_studiu;
insert into utilizator ( id,tip, CNP, nume, prenume, numar_telefon, email, cont_IBAN, numar_contract) VALUES
(19,'Student','1334567890123', 'Popescu', 'Ion', '0712345678', 'ion.popescu@example.com', 'RO49AAAA1B31007593840000', 1001);

INSERT INTO autentificare (id, username, parola)
VALUES
(19, 'ion.popescu', 'password123');
insert into utilizator ( id,tip, CNP, nume, prenume, numar_telefon, email, cont_IBAN, numar_contract) VALUES
(22,'Profesor','1234567890789', 'Ionescu', 'Elena', '0713345678', 'elena.ionescu@example.com', 'RO49AAAA1B31007593840003', 1003);
insert into autentificare(id,username,parola)values
(22,'ionescu.elena','1234');


INSERT INTO utilizator (id, tip, CNP, nume, prenume, numar_telefon, email, cont_IBAN, numar_contract)
VALUES
-- Studenti
(1, 'Student', '1234567890123', 'Popescu', 'Ana', '0712345678', 'ana.popescu@student.ro', 'RO49AAAA1B31007593840000', 1001),
(2, 'Student', '1234567890124', 'Ionescu', 'George', '0723456789', 'george.ionescu@student.ro', 'RO49AAAA1B31007593840001', 1002),
(3, 'Student', '1234567890125', 'Marinescu', 'Ioana', '0734567890', 'ioana.marinescu@student.ro', 'RO49AAAA1B31007593840002', 1003),
(4, 'Student', '1234567890126', 'Dumitrescu', 'Alex', '0745678901', 'alex.dumitrescu@student.ro', 'RO49AAAA1B31007593840003', 1004),
(5, 'Student', '1234567890127', 'Tudor', 'Maria', '0756789012', 'maria.tudor@student.ro', 'RO49AAAA1B31007593840004', 1005),
(6, 'Student', '1234567890128', 'Ilie', 'Cristian', '0767890123', 'cristian.ilie@student.ro', 'RO49AAAA1B31007593840005', 1006),
(7, 'Student', '1234567890129', 'Georgescu', 'Elena', '0778901234', 'elena.georgescu@student.ro', 'RO49AAAA1B31007593840006', 1007),
(8, 'Student', '1234567890130', 'Mihai', 'Andrei', '0789012345', 'andrei.mihai@student.ro', 'RO49AAAA1B31007593840007', 1008),
(9, 'Student', '1234567890131', 'Radulescu', 'Laura', '0790123456', 'laura.radulescu@student.ro', 'RO49AAAA1B31007593840008', 1009),
(10, 'Student', '1234567890132', 'Petrescu', 'Marius', '0710111213', 'marius.petrescu@student.ro', 'RO49AAAA1B31007593840009', 1010),
(11, 'Student', '1234567890133', 'Popa', 'Bianca', '0720111213', 'bianca.popa@student.ro', 'RO49AAAA1B31007593840010', 1011),
(12, 'Student', '1234567890134', 'Grigore', 'Daniel', '0730111213', 'daniel.grigore@student.ro', 'RO49AAAA1B31007593840011', 1012),
(13, 'Student', '1234567890135', 'Serban', 'Florin', '0740111213', 'florin.serban@student.ro', 'RO49AAAA1B31007593840012', 1013),
(14, 'Student', '1234567890136', 'Avram', 'Corina', '0750111213', 'corina.avram@student.ro', 'RO49AAAA1B31007593840013', 1014),
(15, 'Student', '1234567890137', 'Barbu', 'Razvan', '0760111213', 'razvan.barbu@student.ro', 'RO49AAAA1B31007593840014', 1015),
(16, 'Student', '1234567890138', 'Lupu', 'Cristina', '0770111213', 'cristina.lupu@student.ro', 'RO49AAAA1B31007593840015', 1016),
(17, 'Student', '1234567890139', 'Tanase', 'Ioan', '0780111213', 'ioan.tanase@student.ro', 'RO49AAAA1B31007593840016', 1017),
(18, 'Student', '1234567890140', 'Zamfir', 'Adriana', '0790111213', 'adriana.zamfir@student.ro', 'RO49AAAA1B31007593840017', 1018),
-- Profesori
(20, 'Profesor', '2234567890123', 'Popescu', 'Ion', '0712345670', 'ion.popescu@profesor.ro', 'RO49AAAA1B31007593840020', 2001),
(21, 'Profesor', '2234567890124', 'Ionescu', 'Elena', '0723456701', 'elena.ionescu@profesor.ro', 'RO49AAAA1B31007593840021', 2002),
-- Administratori
(23, 'Administrator', '3234567890123', 'Stefan', 'Marina', '0712345679', 'marina.stefan@admin.ro', 'RO49AAAA1B31007593840022', 3001),
(24, 'Administrator', '3234567890124', 'Nedelcu', 'Andreea', '0723456790', 'andreea.nedelcu@admin.ro', 'RO49AAAA1B31007593840023', 3002),
(25, 'Administrator', '3234567890125', 'Gheorghe', 'Victor', '0734567890', 'victor.gheorghe@admin.ro', 'RO49AAAA1B31007593840024', 3003),
-- Super-Administratori
(26, 'Super-Administrator', '4234567890123', 'Vlad', 'Rares', '0712345677', 'rares.vlad@superadmin.ro', 'RO49AAAA1B31007593840025', 4001),
(27, 'Super-Administrator', '4234567890124', 'Mircea', 'Teodor', '0723456788', 'teodor.mircea@superadmin.ro', 'RO49AAAA1B31007593840026', 4002);

INSERT INTO autentificare (id, username, parola)
VALUES
(1, 'ana.popescu', 'password1'),
(2, 'george.ionescu', 'password2'),
(3, 'ioana.marinescu', 'password3'),
(4, 'alex.dumitrescu', 'password4'),
(5, 'maria.tudor', 'password5'),
(6, 'cristian.ilie', 'password6'),
(7, 'elena.georgescu', 'password7'),
(8, 'andrei.mihai', 'password8'),
(9, 'laura.radulescu', 'password9'),
(10, 'marius.petrescu', 'password10'),
(11, 'bianca.popa', 'password11'),
(12, 'daniel.grigore', 'password12'),
(13, 'florin.serban', 'password13'),
(14, 'corina.avram', 'password14'),
(15, 'razvan.barbu', 'password15'),
(16, 'cristina.lupu', 'password16'),
(17, 'ioan.tanase', 'password17'),
(18, 'adriana.zamfir', 'password18'),
(20, 'ion.popescu', 'password123'),
(21, 'elena.ionescu', '1234'),
(23, 'marina.stefan', 'admin1'),
(24, 'andreea.nedelcu', 'admin2'),
(25, 'victor.gheorghe', 'admin3'),
(26, 'rares.vlad', 'superadmin1'),
(27, 'teodor.mircea', 'superadmin2');

INSERT INTO curs (id, descriere, max_studenti)
VALUES
(1, 'Matematica Aplicata', 30),
(2, 'Programare Orientata pe Obiecte', 25),
(3, 'Algoritmi si Structuri de Date', 35),
(4, 'Baze de Date', 30),
(5, 'Inteligenta Artificiala', 20);

INSERT INTO student (id, an, nr_ore)
VALUES
(1, 1, 100),
(2, 1, 110),
(3, 2, 90),
(4, 2, 100),
(5, 3, 80),
(6, 3, 90),
(7, 4, 100),
(8, 4, 105),
(9,3,100),
(10,1,75),
(11,2,105),
(12,4,110),
(13,1,120),
(14,2,90),
(15,3,40),
(16,4,100),
(17,1,90),
(18,2,80),
(19,3,85);

INSERT INTO profesor (id, nr_ore_min, nr_ore_max, departament)
VALUES
(20, 10, 20, 'Matematica'),
(21, 15, 25, 'Informatica'),
(22,20,30,'Fizica');

    INSERT INTO curs_student (curs_id, student_id)
    VALUES
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4),
    (3, 5),
    (3, 6),
    (4, 7),
    (4, 8),
    (5, 9),
    (5, 10),
    (1, 11),
    (1, 12),
    (2, 13),
    (2, 14),
    (3, 15),
    (3, 16),
    (4, 17),
    (4, 18),
    (5, 19);

INSERT INTO grup (id, curs_id, min_participanti, expira)
VALUES
(1, 1, 5, '2024-06-30 23:59:59'),
(2, 2, 4, '2024-07-15 23:59:59'),
(3, 3, 6, '2024-08-01 23:59:59'),
(4, 4, 3, '2024-08-15 23:59:59');

INSERT INTO grup_studenti (grup_id, student_id)
VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 5),
(3, 6),
(4, 7),
(4, 8);

INSERT INTO mesaj (id, grup_id, student_id, continut)
VALUES
(1, 1, 1, 'Salut, putem colabora la proiectul de grup?'),
(2, 1, 2, 'Sigur, să stabilim o întâlnire.'),
(3, 2, 3, 'Când aveți timp pentru o discuție despre proiect?'),
(4, 2, 4, 'Mâine după-amiază ar fi bine pentru mine.'),
(5, 3, 5, 'Am finalizat partea mea din proiect.'),
(6, 3, 6, 'Super, voi începe partea mea mâine.'),
(7, 4, 7, 'Am nevoie de ajutor cu un subiect.'),
(8, 4, 8, 'Sigur, despre ce este vorba?');

INSERT INTO activitate (id, curs_id, tip, pondere)
VALUES
(1, 1, 'Examen', 50),
(2, 1, 'Proiect', 30),
(3, 1, 'Laborator', 20), -- Total 50 + 30 + 20 = 100
(4, 2, 'Test intermediar', 20),
(5, 2, 'Proiect', 40),
(6, 2, 'Laborator', 40), -- Total 20 + 40 + 40 = 100
(7, 3, 'Laborator', 30),
(8, 3, 'Examen', 50),
(9, 3, 'Test intermediar', 20), -- Total 30 + 50 + 20 = 100
(10, 4, 'Proiect', 40),
(11, 4, 'Examen', 60), -- Total 40 + 60 = 100
(12, 5, 'Seminar', 30),
(13, 5, 'Proiect', 40),
(14, 5, 'Test final', 30), -- Total 30 + 40 + 30 = 100
(15, 5, 'Test intermediar', 30); -- Total 30 + 40 + 30 = 100

INSERT INTO activitate_profesor (activitate_id, profesor_id)
VALUES
(1, 20),
(2, 20),
(3, 21),
(4, 21),
(5, 22),
(6, 22),
(7, 20),
(8, 20),
(9, 21),
(10, 21),
(11, 22),
(12, 22),
(13, 20),
(14, 21),
(15, 22);

INSERT INTO calendar (activitate_id, data_inceput, data_sfarsit)
VALUES
(1, '2024-01-15 09:00:00', '2024-01-15 12:00:00'),
(1, '2024-02-15 09:00:00', '2024-02-15 12:00:00'),
(1, '2024-03-15 09:00:00', '2024-03-15 12:00:00'),
(2, '2024-02-20 10:00:00', '2024-02-20 14:00:00'),
(2, '2024-03-20 10:00:00', '2024-03-20 14:00:00'),
(2, '2024-04-20 10:00:00', '2024-04-20 14:00:00'),
(3, '2024-03-05 13:00:00', '2024-03-05 15:00:00'),
(3, '2024-04-05 13:00:00', '2024-04-05 15:00:00'),
(3, '2024-05-05 13:00:00', '2024-05-05 15:00:00'),
(4, '2024-03-20 10:00:00', '2024-03-20 14:00:00'),
(4, '2024-04-20 10:00:00', '2024-04-20 14:00:00'),
(4, '2024-05-20 10:00:00', '2024-05-20 14:00:00'),
(5, '2024-04-10 08:00:00', '2024-04-10 12:00:00'),
(5, '2024-05-10 08:00:00', '2024-05-10 12:00:00'),
(5, '2024-06-10 08:00:00', '2024-06-10 12:00:00'),
(6, '2024-05-05 09:00:00', '2024-05-05 12:00:00'),
(6, '2024-06-05 09:00:00', '2024-06-05 12:00:00'),
(6, '2024-07-05 09:00:00', '2024-07-05 12:00:00'),
(7, '2024-06-01 11:00:00', '2024-06-01 15:00:00'),
(7, '2024-07-01 11:00:00', '2024-07-01 15:00:00'),
(7, '2024-08-01 11:00:00', '2024-08-01 15:00:00'),
(8, '2024-06-20 10:00:00', '2024-06-20 14:00:00'),
(8, '2024-07-20 10:00:00', '2024-07-20 14:00:00'),
(8, '2024-08-20 10:00:00', '2024-08-20 14:00:00'),
(9, '2024-07-05 13:00:00', '2024-07-05 15:00:00'),
(9, '2024-08-05 13:00:00', '2024-08-05 15:00:00'),
(10, '2024-07-10 13:00:00', '2024-07-10 15:00:00'),
(10, '2024-08-10 13:00:00', '2024-08-10 15:00:00'),
(11, '2024-07-15 08:00:00', '2024-07-15 12:00:00'),
(12, '2024-07-20 10:00:00', '2024-07-20 14:00:00'),
(12, '2024-08-20 10:00:00', '2024-08-20 14:00:00'),
(13, '2024-07-25 13:00:00', '2024-07-25 15:00:00'),
(14, '2024-07-30 11:00:00', '2024-07-30 15:00:00'),
(15, '2024-08-05 10:00:00', '2024-08-05 14:00:00');

INSERT INTO activitate_nota (activitate_id, student_id, nota)
SELECT
    activitate.id AS activitate_id,
    curs_student.student_id as student_id,
    FLOOR(RAND() * 10) + 1 AS nota  -- Generează note între 1 și 10
FROM
    curs_student
JOIN
    activitate
    ON activitate.curs_id = curs_student.curs_id;

insert into activitate(curs_id, tip, pondere)
values
    (1,'Aprofundare',NULL),
    (1,'Proiect',NULL),
    (2,'Aprofundare',NULL),
    (3,'Aprofundare',NULL),
    (4,'Aprofundare',NULL);

INSERT INTO grup_activitate (grup_id, activitate_id)
VALUES
(1, 16),
(1, 17),
(2, 18),
(3, 19),
(4, 20);

UPDATE utilizator
SET
    nume = 'Popescu',
    prenume = 'Andrei',
    email = 'andrei.popescu@example.com',
    numar_telefon = '0711223344'
WHERE id = 19;

UPDATE autentificare
SET
    username = 'andrei.popescu',
    parola = 'newpassword456'
WHERE id = 19;

INSERT INTO curs_student (curs_id, student_id)
VALUES
    (1, 13),
    (1, 14),
    (2, 15),
    (2, 16),
    (3, 17),
    (3, 18),
    (4, 11),
    (4, 12),
    (5, 7),
    (5, 8),
    (1, 3),
    (1, 4),
    (2, 5),
    (2, 6),
    (3, 9),
    (3, 10),
    (4, 1),
    (4, 2),
    (5, 6);
INSERT INTO activitate_nota (activitate_id, student_id, nota)
SELECT
    activitate.id AS activitate_id,
    curs_student.student_id AS student_id,
    FLOOR(RAND() * 10) + 1 AS nota  -- Generează note între 1 și 10
FROM
    curs_student
JOIN
    activitate ON activitate.curs_id = curs_student.curs_id
WHERE
    curs_student.student_id < 19  -- Asigurăm că doar studenții cu ID mai mic de 19 sunt incluși
    AND NOT EXISTS (  -- Verificăm că perechea activitate_id și student_id nu există deja în activitate_nota
        SELECT 1
        FROM activitate_nota
        WHERE activitate_nota.activitate_id = activitate.id
        AND activitate_nota.student_id = curs_student.student_id
    );
insert into grup_studenti(grup_id, student_id) values
                                                   (2,1);

UPDATE grup
SET expira = DATE_ADD(expira, INTERVAL 1 YEAR)
WHERE YEAR(expira) = 2024;
INSERT INTO grup (id, curs_id, min_participanti, expira)
VALUES (5, 5, 10, '2024-12-31 23:59:59');

UPDATE calendar
SET data_inceput = DATE_ADD(data_inceput, INTERVAL 1 YEAR),
    data_sfarsit = DATE_ADD(data_sfarsit, INTERVAL 1 YEAR)
WHERE YEAR(data_inceput) = 2024 OR YEAR(data_sfarsit) = 2024;

insert into calendar(activitate_id, data_inceput, data_sfarsit) VALUES
(14,'2026-12-31 20:00:00','2026-12-31 22:00:00')

insert into activitate_profesor(activitate_id, profesor_id) values (1,21)