CREATE SCHEMA `bdtipocambio`;
-- -----------------------------------------------------
-- Schema bdtipocambio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdtipocambio` DEFAULT CHARACTER SET utf8 ;
USE `bdtipocambio`;
-- -----------------------------------------------------
-- Table `bdtipocambio`.`tipocambio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdtipocambio`.`tipocambio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `montoOrigen` DOUBLE(40,2) NOT NULL,
  `monedaOrigen` VARCHAR(100) NOT NULL,
  `montoDestino` DOUBLE(40,2) NOT NULL,
  `monedaDestino` VARCHAR(100) NOT NULL,
  `usuarioCreacion` VARCHAR(25) NOT NULL,
  `usuarioModificacion` VARCHAR(25) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
