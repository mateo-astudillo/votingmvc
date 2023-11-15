SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Voting
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Voting` ;

-- -----------------------------------------------------
-- Schema Voting
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Voting` ;
USE `Voting` ;

-- -----------------------------------------------------
-- Table `Voting`.`Gender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Gender` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Gender` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `Voting`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Person` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Person` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `document` INT UNSIGNED NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthdate` DATE NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `vote` INT UNSIGNED NOT NULL DEFAULT 0,
  `voting_time` TIME NULL,
  `Gender_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `Gender_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `document_UNIQUE` (`document` ASC) VISIBLE,
  INDEX `fk_Person_Gender1_idx` (`Gender_id` ASC) VISIBLE,
  CONSTRAINT `fk_Person_Gender1`
    FOREIGN KEY (`Gender_id`)
    REFERENCES `Voting`.`Gender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `Voting`.`Office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Office` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Office` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `Voting`.`Contest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Contest` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Contest` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `number` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `Voting`.`Candidate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Candidate` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Candidate` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `votes` INT UNSIGNED NOT NULL DEFAULT 0,
  `Office_id` INT UNSIGNED NOT NULL,
  `Contest_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `Office_id`, `Contest_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Candidate_Office1_idx` (`Office_id` ASC) VISIBLE,
  INDEX `fk_Candidate_Contest1_idx` (`Contest_id` ASC) VISIBLE,
  CONSTRAINT `fk_Candidate_Office1`
    FOREIGN KEY (`Office_id`)
    REFERENCES `Voting`.`Office` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Candidate_Contest1`
    FOREIGN KEY (`Contest_id`)
    REFERENCES `Voting`.`Contest` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `Voting`.`Election_Clerk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Election_Clerk` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Election_Clerk` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `opening_time` TIME NULL,
  `closing_time` TIME NULL,
  `username` CHAR(6) NOT NULL,
  `password` CHAR(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `Voting`.`Incidence_Type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Incidence_Type` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Incidence_Type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `Voting`.`Incidence`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Incidence` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Incidence` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  `Incidence_Type_id` INT UNSIGNED NOT NULL,
  `time` NOT NULL TIME,
  PRIMARY KEY (`id`, `Incidence_Type_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Incidence_Incidence_Type1_idx` (`Incidence_Type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Incidence_Incidence_Type1`
    FOREIGN KEY (`Incidence_Type_id`)
    REFERENCES `Voting`.`Incidence_Type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `Voting`.`Vote`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Voting`.`Vote` ;

CREATE TABLE IF NOT EXISTS `Voting`.`Vote` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Candidate_id` INT UNSIGNED NOT NULL,
  `time` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `Candidate_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Vote_Candidate1_idx` (`Candidate_id` ASC) VISIBLE,
  CONSTRAINT `fk_Vote_Candidate1`
    FOREIGN KEY (`Candidate_id`)
    REFERENCES `Voting`.`Candidate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

USE `Voting` ;

-- -----------------------------------------------------
-- Placeholder table for view `Voting`.`candidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Voting`.`candidate` (`id` INT, `first_name` INT, `last_name` INT, `name` INT, `number` INT);

-- -----------------------------------------------------
-- procedure validateElectionClerk
-- -----------------------------------------------------

USE `Voting`;
DROP procedure IF EXISTS `Voting`.`validateElectionClerk`;

CREATE PROCEDURE `validateElectionClerk` (
    IN `username` CHAR(6),
    IN `password` CHAR(6),
  	OUT `id` INT
)
BEGIN
    SELECT `Election_Clerk`.`id` INTO `id`
    FROM `Election_Clerk`
    WHERE `username` = `Election_Clerk`.`username`
    AND `password` = `Election_Clerk`.`password`;
END;

-- -----------------------------------------------------
-- procedure saveOpeningTime
-- -----------------------------------------------------

USE `Voting`;
DROP procedure IF EXISTS `Voting`.`saveOpeningTime`;

CREATE PROCEDURE `saveOpeningTime` (
	IN `id` int
)
BEGIN
	SET `time_zone` = 'America/Argentina/Buenos_Aires';
	UPDATE `Election_Clerk`
	SET `Election_Clerk`.`opening_time` = NOW()
	WHERE `id` = `Election_Clerk`.`id`;
END;

-- -----------------------------------------------------
-- procedure saveClosingTime
-- -----------------------------------------------------

USE `Voting`;
DROP procedure IF EXISTS `Voting`.`saveClosingTime`;

CREATE PROCEDURE `saveClosingTime` (
	IN `id` INT
)
BEGIN
	SET `time_zone` = 'America/Argentina/Buenos_Aires';
	UPDATE `Election_Clerk`
	SET `closing_time` = NOW()
	WHERE `id` = `Election_Clerk`.`id`;
END;

-- -----------------------------------------------------
-- procedure getPerson
-- -----------------------------------------------------

USE `Voting`;
DROP procedure IF EXISTS `Voting`.`getPerson`;

CREATE PROCEDURE `getPerson` (
	IN `document` INT,
  OUT `id` INT,
	OUT `firstName` VARCHAR(45),
	OUT `lastName` VARCHAR(45),
  OUT `birthdate` DATE,
  OUT `address` VARCHAR(255),
  OUT `gender` VARCHAR(45)
)
BEGIN
	SELECT `Person`.`id`, `Person`.`first_name`, `Person`.`last_name`, `Person`.`birthdate`, `Person`.`address`
    INTO `id`, `firstName`, `lastName`, `birthdate`, `address`
    FROM `Person`
    WHERE `document` = `Person`.`document`;
	SELECT `Gender`.`name`
    INTO `gender`
    FROM `Gender`, `Person`
	WHERE `id` = `Person`.`id`
        AND `Gender`.`id` = `Person`.`Gender_id`;
END;

-- -----------------------------------------------------
-- procedure saveIncidence
-- -----------------------------------------------------

USE `Voting`;
DROP procedure IF EXISTS `Voting`.`saveIncidence`;

CREATE PROCEDURE `saveIncidence` (
  IN `type` VARCHAR(45),
	IN `description` VARCHAR(255),
  OUT `id` INT
)
BEGIN
	DECLARE `type_exist` INT;
  DECLARE `id_type` INT;

  SELECT count(*) INTO `type_exist` FROM `Incidence_Type` WHERE `type` LIKE `Incidence_Type`.`type`;
  IF (`type_exist` = 0) THEN
  	INSERT INTO `Incidence_Type` (`type`) VALUES (`type`);
  END IF;

	SET `time_zone` = 'America/Argentina/Buenos_Aires';
  SELECT `Incidence_Type`.`id` INTO `id_type` FROM `Incidence_Type` WHERE `type` LIKE `Incidence_Type`.`type`;
  INSERT INTO `Incidence` (`Incidence_Type_id`, `Incidence`.`description`, `Incidence`.`time`) VALUES (`id_type`, `description`, NOW());
END;

-- -----------------------------------------------------
-- procedure vote
-- -----------------------------------------------------

USE `Voting`;
DROP procedure IF EXISTS `Voting`.`vote`;

CREATE PROCEDURE `vote` (
	IN `candidateId` INT,
    IN `personId` INT,
    OUT `result` INT
)
BEGIN
	DECLARE `personVote` INT;
	SELECT
    `Person`.`vote`
INTO `personVote` FROM
    `Person`
WHERE
    `personId` = `Person`.`id`;
    IF (`personVote` = 0) THEN
	INSERT INTO `Vote` (`Candidate_id`, `time`) VALUES (`candidateId`, NOW());
UPDATE `Person`
SET
    `vote` = 1;
    SET `result` = 1;
    UPDATE `Candidate` SET `votes` = `votes` + 1 WHERE `candidateId` = `Candidate`.`id`;
    ELSE
    SET `result` = 0;
    END IF;
END;

-- -----------------------------------------------------
-- View `Voting`.`candidate`
-- -----------------------------------------------------
CREATE OR REPLACE VIEW `candidate` AS
    SELECT
        `Candidate`.`id`,
        `Candidate`.`first_name`,
        `Candidate`.`last_name`,
        `Contest`.`name` AS `contest`,
        `Contest`.`number`,
        `Office`.`name` AS `office`
    FROM
        `Candidate`,
        `Contest`,
        `Office`
    WHERE
        `Candidate`.`Office_id` = `Office`.`id`
            AND `Candidate`.`Contest_id` = `Contest`.`id`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
