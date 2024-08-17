-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mymedicalbillsdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mymedicalbillsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mymedicalbillsdb` DEFAULT CHARACTER SET utf8 ;
USE `mymedicalbillsdb` ;

-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`State` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`State` (
  `state_cd` VARCHAR(2) NOT NULL,
  `state_description` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`state_cd`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Address` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Address` (
  `address_id` BIGINT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(256) NULL,
  `city` VARCHAR(128) NULL,
  `state_cd` VARCHAR(2) NOT NULL,
  `zip` VARCHAR(5) NULL,
  PRIMARY KEY (`address_id`),
  INDEX `fk_Address_State1_idx` (`state_cd` ASC) VISIBLE,
  CONSTRAINT `fk_Address_State1`
    FOREIGN KEY (`state_cd`)
    REFERENCES `mymedicalbillsdb`.`State` (`state_cd`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Provider_Group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Provider_Group` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Provider_Group` (
  `provider_group_id` BIGINT NOT NULL AUTO_INCREMENT,
  `address_id` BIGINT NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `phone` VARCHAR(10) NULL,
  `website` VARCHAR(128) NULL,
  PRIMARY KEY (`provider_group_id`),
  INDEX `fk_Provider_Group_Address1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_Provider_Group_Address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `mymedicalbillsdb`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Patient` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Patient` (
  `patient_id` BIGINT NOT NULL AUTO_INCREMENT,
  `address_id` BIGINT NOT NULL,
  `account_number` VARCHAR(32) NULL,
  `first_name` VARCHAR(45) NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`patient_id`),
  INDEX `fk_Patient_Address1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_Patient_Address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `mymedicalbillsdb`.`Address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Bill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Bill` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Bill` (
  `bill_id` BIGINT NOT NULL AUTO_INCREMENT,
  `provider_group_id` BIGINT NOT NULL,
  `patient_id` BIGINT NOT NULL,
  `due_date` DATE NULL,
  `statement_date` DATE NULL,
  PRIMARY KEY (`bill_id`),
  INDEX `fk_Bill_Patient1_idx` (`patient_id` ASC) VISIBLE,
  INDEX `fk_Bill_Provider_Group1_idx` (`provider_group_id` ASC) VISIBLE,
  CONSTRAINT `fk_Bill_Patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `mymedicalbillsdb`.`Patient` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Bill_Provider_Group1`
    FOREIGN KEY (`provider_group_id`)
    REFERENCES `mymedicalbillsdb`.`Provider_Group` (`provider_group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Provider`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Provider` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Provider` (
  `provider_id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(64) NULL,
  `middle_name` VARCHAR(64) NULL,
  `last_name` VARCHAR(64) NULL,
  PRIMARY KEY (`provider_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Service` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Service` (
  `service_id` INT NOT NULL AUTO_INCREMENT,
  `service_description` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`service_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Transaction_Type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Transaction_Type` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Transaction_Type` (
  `transaction_type_id` INT NOT NULL AUTO_INCREMENT,
  `trans_type_description` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`transaction_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Transaction` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Transaction` (
  `transaction_id` BIGINT NOT NULL AUTO_INCREMENT,
  `bill_id` BIGINT NOT NULL,
  `provider_id` BIGINT NOT NULL,
  `service_id` INT NOT NULL,
  `transaction_type_id` INT NOT NULL,
  `amount` DECIMAL NULL,
  `transaction_date` DATE NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_Transaction_Bill1_idx` (`bill_id` ASC) VISIBLE,
  INDEX `fk_Transaction_Provider1_idx` (`provider_id` ASC) VISIBLE,
  INDEX `fk_Transaction_Service1_idx` (`service_id` ASC) VISIBLE,
  INDEX `fk_Transaction_Transaction_Type1_idx` (`transaction_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Transaction_Bill1`
    FOREIGN KEY (`bill_id`)
    REFERENCES `mymedicalbillsdb`.`Bill` (`bill_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Provider1`
    FOREIGN KEY (`provider_id`)
    REFERENCES `mymedicalbillsdb`.`Provider` (`provider_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `mymedicalbillsdb`.`Service` (`service_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Transaction_Type1`
    FOREIGN KEY (`transaction_type_id`)
    REFERENCES `mymedicalbillsdb`.`Transaction_Type` (`transaction_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mymedicalbillsdb`.`Provider_Group_has_Patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mymedicalbillsdb`.`Provider_Group_has_Patient` ;

CREATE TABLE IF NOT EXISTS `mymedicalbillsdb`.`Provider_Group_has_Patient` (
  `provider_group_id` BIGINT NOT NULL,
  `patient_id` BIGINT NOT NULL,
  PRIMARY KEY (`provider_group_id`, `patient_id`),
  INDEX `fk_Provider_Group_has_Patient_Patient1_idx` (`patient_id` ASC) VISIBLE,
  INDEX `fk_Provider_Group_has_Patient_Provider_Group1_idx` (`provider_group_id` ASC) VISIBLE,
  CONSTRAINT `fk_Provider_Group_has_Patient_Provider_Group1`
    FOREIGN KEY (`provider_group_id`)
    REFERENCES `mymedicalbillsdb`.`Provider_Group` (`provider_group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Provider_Group_has_Patient_Patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `mymedicalbillsdb`.`Patient` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
