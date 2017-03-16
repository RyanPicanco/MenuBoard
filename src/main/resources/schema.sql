# CREATE SCHEMA IF NOT EXISTS `Menu` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
#
# -- -----------------------------------------------------
# -- Table `Menu`.`MenuItems`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `Menu`.`MenuItems` (
#   `ID` INT NOT NULL COMMENT '',
#   `Description` VARCHAR(45) NULL COMMENT '',
#   `StartDate` DATETIME NULL COMMENT '',
#   `EndDate` DATETIME NULL COMMENT '',
#   `ImageData` BLOB NOT NULL COMMENT '',
#   PRIMARY KEY (`ID`)  COMMENT '')
# ENGINE = InnoDB;
#
#
# -- -----------------------------------------------------
# -- Table `Menu`.`MenuItemPrices`
# -- -----------------------------------------------------
# CREATE TABLE IF NOT EXISTS `Menu`.`MenuItemPrices` (
#   `MenuItemID` INT NOT NULL COMMENT '',
#   `PriceID` INT NOT NULL COMMENT '',
#   `Size` VARCHAR(45) NOT NULL COMMENT '',
#   `StartDate` DATETIME NULL COMMENT '',
#   `EndDate` DATETIME NULL COMMENT '',
#   `Cost` FLOAT NOT NULL COMMENT '',
#   PRIMARY KEY (`MenuItemID`, `PriceID`)  COMMENT '',
#   CONSTRAINT `MenuItemPrices_ID_FK`
#   FOREIGN KEY (`MenuItemID`)
#   REFERENCES `Menu`.`MenuItems` (`ID`)
#   ON DELETE CASCADE
#   ON UPDATE NO ACTION)
# ENGINE = InnoDB;
#
#
# INSERT INTO `Menu`.`MenuItems` (ID, Description, StartDate, EndDate, ImageData) VALUES
#   (1, 'First Menu Item', '2017-01-01', '2017-04-01', ''),
#   (2, 'Second Menu Item', '2017-03-01', '2017-04-30', '');
#
#
# INSERT INTO `Menu`.`MenuItemPrices` (MenuItemID, PriceID, Size, StartDate, EndDate, Cost) VALUES
#   (1, 1, 'Single', null, null, 10),
#   (1, 2, 'Single', '2017-03-14', '2017-03-15', 8),
#   (2, 1, 'Single', null, null, 15),
#   (2, 2, 'Double', null, null, 25);
#
