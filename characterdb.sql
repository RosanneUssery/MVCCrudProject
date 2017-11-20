-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema characterDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `characterDB` ;

-- -----------------------------------------------------
-- Schema characterDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `characterDB` DEFAULT CHARACTER SET utf8 ;
USE `characterDB` ;

-- -----------------------------------------------------
-- Table `gender`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gender` ;

CREATE TABLE IF NOT EXISTS `gender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gender` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`gender` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` ;

CREATE TABLE IF NOT EXISTS `role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`role` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `characters`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `characters` ;

CREATE TABLE IF NOT EXISTS `characters` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `char_name` VARCHAR(300) NOT NULL,
  `age` INT UNSIGNED NULL,
  `gender_id` INT NULL,
  `role_id` INT NULL,
  `backstory` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_character_gender_idx` (`gender_id` ASC),
  INDEX `fk_character_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_character_gender`
    FOREIGN KEY (`gender_id`)
    REFERENCES `gender` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_character_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO author@localhost;
 DROP USER author@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'author'@'localhost' IDENTIFIED BY 'author';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'author'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `gender`
-- -----------------------------------------------------
START TRANSACTION;
USE `characterDB`;
INSERT INTO `gender` (`id`, `gender`) VALUES (1, 'male');
INSERT INTO `gender` (`id`, `gender`) VALUES (2, 'female');
INSERT INTO `gender` (`id`, `gender`) VALUES (3, 'agender');
INSERT INTO `gender` (`id`, `gender`) VALUES (4, 'non-gender conforming');
INSERT INTO `gender` (`id`, `gender`) VALUES (5, 'other');

COMMIT;


-- -----------------------------------------------------
-- Data for table `role`
-- -----------------------------------------------------
START TRANSACTION;
USE `characterDB`;
INSERT INTO `role` (`id`, `role`) VALUES (1, 'protagonist');
INSERT INTO `role` (`id`, `role`) VALUES (2, 'antagonist');
INSERT INTO `role` (`id`, `role`) VALUES (3, 'sidekick');
INSERT INTO `role` (`id`, `role`) VALUES (4, 'love interest');
INSERT INTO `role` (`id`, `role`) VALUES (5, 'guide');
INSERT INTO `role` (`id`, `role`) VALUES (6, 'helper');

COMMIT;


-- -----------------------------------------------------
-- Data for table `characters`
-- -----------------------------------------------------
START TRANSACTION;
USE `characterDB`;
INSERT INTO `characters` (`id`, `char_name`, `age`, `gender_id`, `role_id`, `backstory`) VALUES (1, 'Jon', 22, 1, 1, 'They have one and it\'s surprisingly cool');

COMMIT;
