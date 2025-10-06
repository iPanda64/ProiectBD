-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: platforma_studiu
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activitate`
--

DROP TABLE IF EXISTS `activitate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activitate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `curs_id` int DEFAULT NULL,
  `tip` varchar(50) NOT NULL,
  `pondere` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curs_id` (`curs_id`),
  CONSTRAINT `activitate_ibfk_1` FOREIGN KEY (`curs_id`) REFERENCES `curs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitate`
--

LOCK TABLES `activitate` WRITE;
/*!40000 ALTER TABLE `activitate` DISABLE KEYS */;
INSERT INTO `activitate` VALUES (1,1,'Examen',50),(2,1,'Colocviu',30),(3,1,'Laborator',20),(4,2,'Examen',20),(5,2,'Curs',40),(6,2,'Laborator',40),(7,3,'Laborator',30),(8,3,'Examen',50),(9,3,'Colocviu',20),(10,4,'Curs',40),(11,4,'Examen',60),(12,5,'Seminar',30),(13,5,'Colocviu',10),(14,5,'Laborator',30),(15,5,'Seminar',30),(16,1,'Aprofundare',NULL),(17,1,'Proiect',NULL),(18,2,'Aprofundare',NULL),(19,3,'Aprofundare',NULL),(20,4,'Aprofundare',NULL),(21,1,'Pauza',0),(22,4,'Seminar',100),(23,4,'Seminar',100),(24,4,'Seminar',100),(26,1,'Proiect_Practice',NULL),(27,2,'Practice',NULL),(28,1,'Relax',NULL);
/*!40000 ALTER TABLE `activitate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activitate_nota`
--

DROP TABLE IF EXISTS `activitate_nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activitate_nota` (
  `activitate_id` int NOT NULL,
  `student_id` int NOT NULL,
  `nota` int DEFAULT NULL,
  PRIMARY KEY (`activitate_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `activitate_nota_ibfk_1` FOREIGN KEY (`activitate_id`) REFERENCES `activitate` (`id`),
  CONSTRAINT `activitate_nota_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitate_nota`
--

LOCK TABLES `activitate_nota` WRITE;
/*!40000 ALTER TABLE `activitate_nota` DISABLE KEYS */;
INSERT INTO `activitate_nota` VALUES (1,1,3),(1,2,9),(1,3,10),(1,4,3),(1,11,3),(1,12,7),(1,13,5),(1,14,7),(2,1,7),(2,2,3),(2,3,10),(2,4,8),(2,11,2),(2,12,10),(2,13,1),(2,14,10),(3,1,3),(3,2,5),(3,3,4),(3,4,2),(3,11,7),(3,12,8),(3,13,6),(3,14,3),(4,1,7),(4,3,9),(4,4,9),(4,5,2),(4,6,7),(4,13,9),(4,14,9),(4,15,9),(5,3,7),(5,4,7),(5,5,4),(5,6,6),(5,13,5),(5,14,4),(5,15,6),(6,3,2),(6,4,9),(6,5,9),(6,6,4),(6,13,8),(6,14,3),(6,15,4),(7,5,10),(7,6,10),(7,9,5),(7,10,8),(7,15,9),(7,17,6),(7,18,4),(8,5,8),(8,6,4),(8,9,10),(8,10,9),(8,15,7),(8,17,6),(8,18,2),(9,5,5),(9,6,3),(9,9,9),(9,10,10),(9,15,1),(9,17,4),(9,18,10),(10,1,10),(10,2,5),(10,7,5),(10,8,5),(10,11,5),(10,12,10),(10,17,10),(10,18,4),(11,1,4),(11,2,9),(11,7,10),(11,8,8),(11,11,4),(11,12,3),(11,17,8),(11,18,7),(12,1,0),(12,6,6),(12,7,10),(12,8,8),(12,9,9),(12,10,3),(12,19,8),(13,6,3),(13,7,7),(13,8,7),(13,9,9),(13,10,3),(13,19,8),(14,1,0),(14,6,4),(14,7,9),(14,8,1),(14,9,8),(14,10,7),(14,19,1),(15,6,9),(15,7,10),(15,8,4),(15,9,5),(15,10,9),(15,19,1),(16,1,6),(16,2,10),(16,3,2),(16,4,2),(16,11,10),(16,12,5),(16,13,3),(16,14,9),(17,1,7),(17,2,6),(17,3,10),(17,4,10),(17,11,1),(17,12,3),(17,13,3),(17,14,4),(18,3,3),(18,4,3),(18,5,5),(18,6,4),(18,13,6),(18,14,7),(18,15,6),(19,5,8),(19,6,9),(19,9,10),(19,10,3),(19,15,4),(19,17,7),(19,18,8),(20,1,2),(20,2,2),(20,7,1),(20,8,8),(20,11,9),(20,12,1),(20,17,8),(20,18,4),(27,1,NULL),(28,1,NULL);
/*!40000 ALTER TABLE `activitate_nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activitate_profesor`
--

DROP TABLE IF EXISTS `activitate_profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activitate_profesor` (
  `activitate_id` int NOT NULL,
  `profesor_id` int NOT NULL,
  PRIMARY KEY (`activitate_id`,`profesor_id`),
  KEY `profesor_id` (`profesor_id`),
  CONSTRAINT `activitate_profesor_ibfk_1` FOREIGN KEY (`activitate_id`) REFERENCES `activitate` (`id`),
  CONSTRAINT `activitate_profesor_ibfk_2` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitate_profesor`
--

LOCK TABLES `activitate_profesor` WRITE;
/*!40000 ALTER TABLE `activitate_profesor` DISABLE KEYS */;
INSERT INTO `activitate_profesor` VALUES (1,20),(2,20),(7,20),(8,20),(13,20),(1,21),(3,21),(4,21),(9,21),(10,21),(12,21),(14,21),(5,22),(6,22),(11,22),(12,22),(15,22);
/*!40000 ALTER TABLE `activitate_profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `all_profesors`
--

DROP TABLE IF EXISTS `all_profesors`;
/*!50001 DROP VIEW IF EXISTS `all_profesors`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `all_profesors` AS SELECT 
 1 AS `id`,
 1 AS `tip`,
 1 AS `CNP`,
 1 AS `nume`,
 1 AS `prenume`,
 1 AS `numar_telefon`,
 1 AS `email`,
 1 AS `cont_IBAN`,
 1 AS `numar_contract`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `all_studenti`
--

DROP TABLE IF EXISTS `all_studenti`;
/*!50001 DROP VIEW IF EXISTS `all_studenti`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `all_studenti` AS SELECT 
 1 AS `id`,
 1 AS `tip`,
 1 AS `CNP`,
 1 AS `nume`,
 1 AS `prenume`,
 1 AS `numar_telefon`,
 1 AS `email`,
 1 AS `cont_IBAN`,
 1 AS `numar_contract`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `autentificare`
--

DROP TABLE IF EXISTS `autentificare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autentificare` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `parola` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `autentificare_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilizator` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autentificare`
--

LOCK TABLES `autentificare` WRITE;
/*!40000 ALTER TABLE `autentificare` DISABLE KEYS */;
INSERT INTO `autentificare` VALUES (1,'ana.popescu','password1'),(2,'george.ionescu','password2'),(3,'ioana.marinescu','password3'),(4,'alex.dumitrescu','password4'),(5,'maria.tudor','password5'),(6,'cristian.ilie','password6'),(7,'elena.georgescu','password7'),(8,'andrei.mihai','password8'),(9,'laura.radulescu','password9'),(10,'marius.petrescu','password10'),(11,'bianca.popa','password11'),(12,'daniel.grigore','password12'),(13,'florin.serban','password13'),(14,'corina.avram','password14'),(15,'razvan.barbu','password15'),(17,'ioan.tanase','password17'),(18,'adriana.zamfir','password18'),(19,'andrei.popescu','newpassword456'),(20,'ion.popescu','password123'),(21,'elena.ionescu','1234'),(22,'ionescu.elena','1234'),(23,'marina.stefan','admin1'),(24,'andreea.nedelcu','admin2'),(25,'victor.gheorghe','admin3'),(26,'rares.vlad','superadmin1'),(27,'teodor.mircea','superadmin2'),(29,'xxmuxx','1234'),(31,'xxmuxx','1234'),(32,'xxmuxx','1234'),(33,'xxmuxx','1234'),(34,'xxmuxx','1234'),(35,'xxmuxx','1234'),(36,'xxmuxx','1234'),(37,'xxmuxx','1234'),(38,'xxmuxx','1234'),(39,'xxmuxx','1234'),(40,'xxmuxx','1234'),(41,'xxmuxx','1234'),(42,'xxmuxx','1234');
/*!40000 ALTER TABLE `autentificare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `activitate_id` int NOT NULL,
  `data_inceput` datetime DEFAULT NULL,
  `data_sfarsit` datetime DEFAULT NULL,
  KEY `activitate_id` (`activitate_id`),
  CONSTRAINT `calendar___fk` FOREIGN KEY (`activitate_id`) REFERENCES `activitate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES (1,'2025-01-15 09:00:00','2025-01-15 12:00:00'),(1,'2025-02-15 09:00:00','2025-02-15 12:00:00'),(1,'2025-03-15 09:00:00','2025-03-15 12:00:00'),(2,'2025-02-20 10:00:00','2025-02-20 14:00:00'),(2,'2025-03-20 10:00:00','2025-03-20 14:00:00'),(2,'2025-04-20 10:00:00','2025-04-20 14:00:00'),(3,'2025-03-05 13:00:00','2025-03-05 15:00:00'),(3,'2025-04-05 13:00:00','2025-04-05 15:00:00'),(3,'2025-05-05 13:00:00','2025-05-05 15:00:00'),(4,'2025-03-20 10:00:00','2025-03-20 14:00:00'),(4,'2025-04-20 10:00:00','2025-04-20 14:00:00'),(4,'2025-05-20 10:00:00','2025-05-20 14:00:00'),(5,'2025-04-10 08:00:00','2025-04-10 12:00:00'),(5,'2025-05-10 08:00:00','2025-05-10 12:00:00'),(5,'2025-06-10 08:00:00','2025-06-10 12:00:00'),(6,'2025-05-05 09:00:00','2025-05-05 12:00:00'),(6,'2025-06-05 09:00:00','2025-06-05 12:00:00'),(6,'2025-07-05 09:00:00','2025-07-05 12:00:00'),(7,'2025-06-01 11:00:00','2025-06-01 15:00:00'),(7,'2025-07-01 11:00:00','2025-07-01 15:00:00'),(7,'2025-08-01 11:00:00','2025-08-01 15:00:00'),(8,'2025-06-20 10:00:00','2025-06-20 14:00:00'),(8,'2025-07-20 10:00:00','2025-07-20 14:00:00'),(8,'2025-08-20 10:00:00','2025-08-20 14:00:00'),(9,'2025-07-05 13:00:00','2025-07-05 15:00:00'),(9,'2025-08-05 13:00:00','2025-08-05 15:00:00'),(10,'2025-07-10 13:00:00','2025-07-10 15:00:00'),(10,'2025-08-10 13:00:00','2025-08-10 15:00:00'),(11,'2025-07-15 08:00:00','2025-07-15 12:00:00'),(12,'2025-07-20 10:00:00','2025-07-20 14:00:00'),(12,'2025-08-20 10:00:00','2025-08-20 14:00:00'),(13,'2025-07-25 13:00:00','2025-07-25 15:00:00'),(14,'2025-07-30 11:00:00','2025-07-30 15:00:00'),(15,'2025-08-05 10:00:00','2025-08-05 14:00:00'),(1,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(1,'2025-01-01 22:00:00','2025-01-01 23:00:00'),(1,'2025-01-10 22:00:00','2025-01-10 23:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(14,'2026-12-31 20:00:00','2026-12-31 22:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(1,'2025-01-11 22:00:00','2025-01-11 23:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(13,'2025-01-06 14:30:00','2025-01-06 16:00:00'),(1,'2025-01-13 12:00:00','2025-01-13 14:00:00');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curs`
--

DROP TABLE IF EXISTS `curs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descriere` varchar(200) DEFAULT NULL,
  `max_studenti` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curs`
--

LOCK TABLES `curs` WRITE;
/*!40000 ALTER TABLE `curs` DISABLE KEYS */;
INSERT INTO `curs` VALUES (1,'Matematica Aplicata',30),(2,'Programare Orientata pe Obiecte',25),(3,'Algoritmi si Structuri de Date',35),(4,'Baze de Date',30),(5,'Inteligenta Artificiala',20);
/*!40000 ALTER TABLE `curs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curs_student`
--

DROP TABLE IF EXISTS `curs_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curs_student` (
  `curs_id` int NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`curs_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `curs_student_ibfk_1` FOREIGN KEY (`curs_id`) REFERENCES `curs` (`id`),
  CONSTRAINT `curs_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curs_student`
--

LOCK TABLES `curs_student` WRITE;
/*!40000 ALTER TABLE `curs_student` DISABLE KEYS */;
INSERT INTO `curs_student` VALUES (1,1),(2,1),(4,1),(5,1),(1,2),(4,2),(1,3),(2,3),(1,4),(2,4),(2,5),(3,5),(2,6),(3,6),(5,6),(4,7),(5,7),(4,8),(5,8),(3,9),(5,9),(3,10),(5,10),(1,11),(4,11),(1,12),(4,12),(1,13),(2,13),(1,14),(2,14),(2,15),(3,15),(3,17),(4,17),(3,18),(4,18),(5,19);
/*!40000 ALTER TABLE `curs_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grup`
--

DROP TABLE IF EXISTS `grup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grup` (
  `id` int NOT NULL AUTO_INCREMENT,
  `curs_id` int DEFAULT NULL,
  `min_participanti` int DEFAULT NULL,
  `expira` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curs_id` (`curs_id`),
  CONSTRAINT `grup_ibfk_1` FOREIGN KEY (`curs_id`) REFERENCES `curs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grup`
--

LOCK TABLES `grup` WRITE;
/*!40000 ALTER TABLE `grup` DISABLE KEYS */;
INSERT INTO `grup` VALUES (1,1,5,'2025-06-30 23:59:59'),(2,2,4,'2025-07-15 23:59:59'),(3,3,6,'2025-08-01 23:59:59'),(4,4,3,'2025-08-15 23:59:59');
/*!40000 ALTER TABLE `grup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grup_activitate`
--

DROP TABLE IF EXISTS `grup_activitate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grup_activitate` (
  `grup_id` int NOT NULL,
  `activitate_id` int NOT NULL,
  PRIMARY KEY (`grup_id`,`activitate_id`),
  KEY `activitate_id` (`activitate_id`),
  CONSTRAINT `grup_activitate_ibfk_1` FOREIGN KEY (`grup_id`) REFERENCES `grup` (`id`),
  CONSTRAINT `grup_activitate_ibfk_2` FOREIGN KEY (`activitate_id`) REFERENCES `activitate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grup_activitate`
--

LOCK TABLES `grup_activitate` WRITE;
/*!40000 ALTER TABLE `grup_activitate` DISABLE KEYS */;
INSERT INTO `grup_activitate` VALUES (1,16),(1,17),(2,18),(3,19),(4,20),(1,28);
/*!40000 ALTER TABLE `grup_activitate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grup_studenti`
--

DROP TABLE IF EXISTS `grup_studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grup_studenti` (
  `grup_id` int NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`grup_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `grup_studenti_ibfk_1` FOREIGN KEY (`grup_id`) REFERENCES `grup` (`id`),
  CONSTRAINT `grup_studenti_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grup_studenti`
--

LOCK TABLES `grup_studenti` WRITE;
/*!40000 ALTER TABLE `grup_studenti` DISABLE KEYS */;
INSERT INTO `grup_studenti` VALUES (1,1),(2,1),(3,1),(1,2),(2,3),(2,4),(3,5),(3,6),(4,7),(4,8);
/*!40000 ALTER TABLE `grup_studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesaj`
--

DROP TABLE IF EXISTS `mesaj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesaj` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grup_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `continut` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `grup_id` (`grup_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `mesaj_ibfk_1` FOREIGN KEY (`grup_id`) REFERENCES `grup` (`id`),
  CONSTRAINT `mesaj_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesaj`
--

LOCK TABLES `mesaj` WRITE;
/*!40000 ALTER TABLE `mesaj` DISABLE KEYS */;
INSERT INTO `mesaj` VALUES (1,1,1,'Salut, putem colabora la proiectul de grup?'),(2,1,2,'Sigur, să stabilim o întâlnire.'),(3,2,3,'Când aveți timp pentru o discuție despre proiect?'),(4,2,4,'Mâine după-amiază ar fi bine pentru mine.'),(5,3,5,'Am finalizat partea mea din proiect.'),(6,3,6,'Super, voi începe partea mea mâine.'),(7,4,7,'Am nevoie de ajutor cu un subiect.'),(8,4,8,'Sigur, despre ce este vorba?'),(9,1,1,'Hello'),(10,1,2,'Buna!'),(11,1,1,'Ce faceti'),(12,1,1,'Ce faceti'),(13,1,1,'Ce faceti'),(14,1,1,'Ce faceti'),(15,1,1,'Ce faceti'),(16,1,1,'Ce faceti'),(17,1,1,'Ce faceti'),(18,1,1,'Ce faceti'),(19,1,1,'Ce faceti'),(20,1,1,'Ce faceti'),(21,1,1,'Ce faceti'),(22,1,1,'Ce faceti'),(23,1,1,'Ce faceti'),(24,1,1,'Ce faceti'),(25,1,1,'Ce faceti'),(26,1,1,'Ce faceti'),(27,1,1,'Ce faceti'),(28,1,1,'Ce faceti');
/*!40000 ALTER TABLE `mesaj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `id` int NOT NULL,
  `nr_ore_min` int DEFAULT NULL,
  `nr_ore_max` int DEFAULT NULL,
  `departament` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `profesor_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilizator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (20,10,20,'Matematica'),(21,15,25,'Informatica'),(22,20,30,'Fizica'),(29,10,20,'Fizica'),(37,1,10,'Filologie');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete_profesor` BEFORE DELETE ON `profesor` FOR EACH ROW begin

        delete from activitate_profesor
        where activitate_profesor.profesor_id=OLD.id;


    end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `an` int DEFAULT NULL,
  `nr_ore` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id`) REFERENCES `utilizator` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,1,100),(2,1,110),(3,2,90),(4,2,100),(5,3,80),(6,3,90),(7,4,100),(8,4,105),(9,3,100),(10,1,75),(11,2,105),(12,4,110),(13,1,120),(14,2,90),(15,3,40),(17,1,90),(18,2,80),(19,3,85),(41,1,10),(42,1,10);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete_student` BEFORE DELETE ON `student` FOR EACH ROW BEGIN

    DELETE FROM activitate_nota
    WHERE student_id = OLD.id;

    DELETE FROM curs_student
    WHERE student_id = OLD.id;

    delete from autentificare
    where autentificare.id=OLD.id;

    delete from grup_studenti
    where grup_studenti.student_id=OLD.id;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `utilizator`
--

DROP TABLE IF EXISTS `utilizator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tip` enum('Student','Profesor','Administrator','Super-Administrator') NOT NULL,
  `CNP` varchar(13) NOT NULL,
  `nume` varchar(50) NOT NULL,
  `prenume` varchar(50) NOT NULL,
  `numar_telefon` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `cont_IBAN` varchar(50) DEFAULT NULL,
  `numar_contract` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizator`
--

LOCK TABLES `utilizator` WRITE;
/*!40000 ALTER TABLE `utilizator` DISABLE KEYS */;
INSERT INTO `utilizator` VALUES (1,'Student','1234567890123','Popescu','Ana','0712345678','ana.popescu@student.ro','RO49AAAA1B31007593840000',1001),(2,'Student','1234567890124','Ionescu','George','0723456789','george.ionescu@student.ro','RO49AAAA1B31007593840001',1002),(3,'Student','1234567890125','Marinescu','Ioana','0734567890','ioana.marinescu@student.ro','RO49AAAA1B31007593840002',1003),(4,'Student','1234567890126','Dumitrescu','Alex','0745678901','alex.dumitrescu@student.ro','RO49AAAA1B31007593840003',1004),(5,'Student','1234567890127','Tudor','Maria','0756789012','maria.tudor@student.ro','RO49AAAA1B31007593840004',1005),(6,'Student','1234567890128','Ilie','Cristian','0767890123','cristian.ilie@student.ro','RO49AAAA1B31007593840005',1006),(7,'Student','1234567890129','Georgescu','Elena','0778901234','elena.georgescu@student.ro','RO49AAAA1B31007593840006',1007),(8,'Student','1234567890130','Mihai','Andrei','0789012345','andrei.mihai@student.ro','RO49AAAA1B31007593840007',1008),(9,'Student','1234567890131','Radulescu','Laura','0790123456','laura.radulescu@student.ro','RO49AAAA1B31007593840008',1009),(10,'Student','1234567890132','Petrescu','Marius','0710111213','marius.petrescu@student.ro','RO49AAAA1B31007593840009',1010),(11,'Student','1234567890133','Popa','Bianca','0720111213','bianca.popa@student.ro','RO49AAAA1B31007593840010',1011),(12,'Student','1234567890134','Grigore','Daniel','0730111213','daniel.grigore@student.ro','RO49AAAA1B31007593840011',1012),(13,'Student','1234567890135','Serban','Florin','0740111213','florin.serban@student.ro','RO49AAAA1B31007593840012',1013),(14,'Student','1234567890136','Avram','Corina','0750111213','corina.avram@student.ro','RO49AAAA1B31007593840013',1014),(15,'Student','1234567890137','Barbu','Razvan','0760111213','razvan.barbu@student.ro','RO49AAAA1B31007593840014',1015),(17,'Student','1234567890139','Tanase','Ioan','0780111213','ioan.tanase@student.ro','RO49AAAA1B31007593840016',1017),(18,'Student','1234567890140','Zamfir','Adriana','0790111213','adriana.zamfir@student.ro','RO49AAAA1B31007593840017',1018),(19,'Student','1334567890123','Popescu','Andrei','0711223344','andrei.popescu@example.com','RO49AAAA1B31007593840000',1001),(20,'Profesor','2234567890123','Popescu','Ion','0712345670','ion.popescu@profesor.ro','RO49AAAA1B31007593840020',2001),(21,'Profesor','2234567890124','Ionescu','Elena','0723456701','elena.ionescu@profesor.ro','RO49AAAA1B31007593840021',2002),(22,'Profesor','1234567890789','Mosulescu','Aurel','0713345678','elena.ionescu@example.com','RO49AAAA1B31007593840003',1003),(23,'Administrator','3234567890123','Stefan','Marina','0712345679','marina.stefan@admin.ro','RO49AAAA1B31007593840022',3001),(24,'Administrator','3234567890124','Nedelcu','Andreea','0723456790','andreea.nedelcu@admin.ro','RO49AAAA1B31007593840023',3002),(25,'Administrator','3234567890125','Gheorghe','Victor','0734567890','victor.gheorghe@admin.ro','RO49AAAA1B31007593840024',3003),(26,'Super-Administrator','4234567890123','Vlad','Rares','0712345677','rares.vlad@superadmin.ro','RO49AAAA1B31007593840025',4001),(27,'Super-Administrator','4234567890124','Mircea','Teodor','0723456788','teodor.mircea@superadmin.ro','RO49AAAA1B31007593840026',4002),(29,'Profesor','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(31,'Profesor','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(32,'Profesor','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(33,'Profesor','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(34,'Profesor','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(35,'Student','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(36,'Profesor','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(37,'Profesor','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(38,'Student','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(39,'Student','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(40,'Student','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(41,'Student','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979),(42,'Student','1234','Marian','Uzbekistan','0712345633','mUzi@gmail.com','-',3979);
/*!40000 ALTER TABLE `utilizator` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `delete_autentificare` BEFORE DELETE ON `utilizator` FOR EACH ROW begin
        delete from autentificare
            where autentificare.id=OLD.id;
    end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `all_profesors`
--

/*!50001 DROP VIEW IF EXISTS `all_profesors`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_profesors` AS select `utilizator`.`id` AS `id`,`utilizator`.`tip` AS `tip`,`utilizator`.`CNP` AS `CNP`,`utilizator`.`nume` AS `nume`,`utilizator`.`prenume` AS `prenume`,`utilizator`.`numar_telefon` AS `numar_telefon`,`utilizator`.`email` AS `email`,`utilizator`.`cont_IBAN` AS `cont_IBAN`,`utilizator`.`numar_contract` AS `numar_contract` from `utilizator` where (`utilizator`.`tip` = 'Profesor') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `all_studenti`
--

/*!50001 DROP VIEW IF EXISTS `all_studenti`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `all_studenti` AS select `utilizator`.`id` AS `id`,`utilizator`.`tip` AS `tip`,`utilizator`.`CNP` AS `CNP`,`utilizator`.`nume` AS `nume`,`utilizator`.`prenume` AS `prenume`,`utilizator`.`numar_telefon` AS `numar_telefon`,`utilizator`.`email` AS `email`,`utilizator`.`cont_IBAN` AS `cont_IBAN`,`utilizator`.`numar_contract` AS `numar_contract` from `utilizator` where (`utilizator`.`tip` = 'Student') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-29 12:55:25
