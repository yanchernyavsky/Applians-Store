-- MySQL Script generated by MySQL Workbench
-- Thu Dec 12 01:50:22 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema electricshop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema electricshop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `electricshop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `electricshop` ;

-- -----------------------------------------------------
-- Table `electricshop`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electricshop`.`category` (
  `categoryname` VARCHAR(45) NOT NULL,
  `productnumber` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(100) NOT NULL,
  `availability` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`categoryname`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electricshop`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electricshop`.`users` (
  `idusers` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `admin` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idusers`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electricshop`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electricshop`.`client` (
  `idclient` INT(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  `users_idusers` INT(11) NOT NULL,
  PRIMARY KEY (`idclient`),
  INDEX `fk_client_users1_idx` (`users_idusers` ASC) VISIBLE,
  CONSTRAINT `fk_client_users1`
    FOREIGN KEY (`users_idusers`)
    REFERENCES `electricshop`.`users` (`idusers`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electricshop`.`provider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electricshop`.`provider` (
  `idprovider` INT(11) NOT NULL,
  `companyname` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idprovider`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electricshop`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electricshop`.`product` (
  `idproduct` INT(11) NOT NULL,
  `idprovider` INT(11) NOT NULL,
  `productname` VARCHAR(45) NOT NULL,
  `number` INT(40) NULL DEFAULT NULL,
  `price` INT(40) NULL DEFAULT NULL,
  `categoryname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idproduct`, `idprovider`),
  INDEX `idprovider_idx` (`idprovider` ASC) VISIBLE,
  INDEX `fk_categoryname_idx` (`categoryname` ASC) VISIBLE,
  CONSTRAINT `fk_categoryname`
    FOREIGN KEY (`categoryname`)
    REFERENCES `electricshop`.`category` (`categoryname`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idprovider`
    FOREIGN KEY (`idprovider`)
    REFERENCES `electricshop`.`provider` (`idprovider`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `electricshop`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `electricshop`.`orders` (
  `idorder` INT(11) NOT NULL,
  `idclient` INT(11) NOT NULL,
  `idproductorder` INT(11) NOT NULL,
  `idprovider` INT(11) NOT NULL,
  `productnumber` INT(11) NULL DEFAULT NULL,
  `finalprice` INT(111) NULL DEFAULT NULL,
  `isready` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idorder`, `idprovider`, `idproductorder`, `idclient`),
  INDEX `fk_idclient_idx` (`idclient` ASC) VISIBLE,
  INDEX `fk_idproduct_idx` (`idproductorder` ASC) VISIBLE,
  INDEX `fk_idprovider_idx` (`idprovider` ASC) VISIBLE,
  CONSTRAINT `fk_client`
    FOREIGN KEY (`idclient`)
    REFERENCES `electricshop`.`client` (`idclient`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idproduct`
    FOREIGN KEY (`idproductorder`)
    REFERENCES `electricshop`.`product` (`idproduct`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_iproviderfororder`
    FOREIGN KEY (`idprovider`)
    REFERENCES `electricshop`.`provider` (`idprovider`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
