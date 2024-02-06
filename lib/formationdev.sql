-- Active: 1706624914641@@127.0.0.1@3306
DROP DATABASE IF EXISTS Formationdev;
CREATE DATABASE Formationdev;
USE Formationdev;
    DEFAULT CHARACTER SET = 'utf8mb4';
    -------------------------------------------------------------------------------------------------------------
    -----------                Construction d'une table des formations en ligne                       ----------- 
    -------------------------------------------------------------------------------------------------------------
    CREATE TABLE T_Courses (
        idCourse INT (4) PRIMARY KEY AUTO_INCREMENT,
        Name           VARCHAR(30) NOT NULL,
        Description    VARCHAR(30) NOT NULL,
        Duration       VARCHAR (30) NOT NULL,
        Remote         VARCHAR(30) NOT NULL,
        UnitaryPrice   FLOAT(8) NOT NULL
    )ENGINE =InnobDB;

    INSER INTO T_Courses (Name,Description, Duration,Remote,UnitaryPrice)VALUES ('Java',' java SE 8: Syntaxe et Poo','5 jours','présentiel',200);
    INSER INTO T_Courses (Name,Description, Duration,Remote,UnitaryPrice)VALUES('Java avancé', 'Exception, fichiers, JDBC, thread','7 jours','distanciel',300);
    INSER INTO T_Courses (Name,Description, Duration,Remote,UnitaryPrice)VALUES('Spring','Spring Core/Mvc,/Security', '15 jours', 'présentiel',500);
    INSER INTO T_Courses (Name,Description, Duration,Remote,UnitaryPrice)VALUES('Php framworks', 'symphony', '15 jours', 'distanciel', 500);
    INSER INTO T_Courses (Name,Description, Duration,Remote,UnitaryPrice)VALUES('C#','DoNet Core', '5 jours', 'distanciel',200);
    