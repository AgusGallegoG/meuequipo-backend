-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema meuequipo-local
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema meuequipo-local
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `meuequipo-local` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `meuequipo-local`;

-- -----------------------------------------------------
-- Table `meuequipo-local`.`season`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`season`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `end_date`           DATE         NULL DEFAULT NULL,
    `is_active`          BIT(1)       NULL DEFAULT NULL,
    `name`               VARCHAR(255) NULL DEFAULT NULL,
    `start_date`         DATE         NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`category`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `is_active`          BIT(1)       NOT NULL,
    `name`               VARCHAR(255) NULL DEFAULT NULL,
    `year_end`           DATE         NULL DEFAULT NULL,
    `year_init`          DATE         NULL DEFAULT NULL,
    `season_id`          BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK6ovrk8owu4v43nnljb10smghd` (`season_id` ASC) VISIBLE,
    CONSTRAINT `FK6ovrk8owu4v43nnljb10smghd`
        FOREIGN KEY (`season_id`)
            REFERENCES `meuequipo-local`.`season` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`publication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`publication`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `body`               TEXT         NOT NULL,
    `title`              VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`image`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `original_name`      VARCHAR(255) NOT NULL,
    `relative_path`      VARCHAR(255) NOT NULL,
    `stored_name`        VARCHAR(255) NOT NULL,
    `publication_id`     BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKcscqhhl79jrxps1fpv5rmej1t` (`publication_id` ASC) VISIBLE,
    CONSTRAINT `FKcscqhhl79jrxps1fpv5rmej1t`
        FOREIGN KEY (`publication_id`)
            REFERENCES `meuequipo-local`.`publication` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`team`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `name`               VARCHAR(255) NOT NULL,
    `sex`                BIGINT       NOT NULL,
    `trainer`            VARCHAR(255) NOT NULL,
    `trainer_contact`    VARCHAR(255) NULL DEFAULT NULL,
    `category_id`        BIGINT       NOT NULL,
    `season_id`          BIGINT       NOT NULL,
    `team_image_id`      BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK9r7387m3a63dy2rd9ub8dceeg` (`team_image_id` ASC) VISIBLE,
    INDEX `FKr01s1jbaa1i4i4bimgqpf1oa` (`category_id` ASC) VISIBLE,
    INDEX `FK91i24ep6xjpoxfydqb17eym4h` (`season_id` ASC) VISIBLE,
    CONSTRAINT `FK91i24ep6xjpoxfydqb17eym4h`
        FOREIGN KEY (`season_id`)
            REFERENCES `meuequipo-local`.`season` (`id`),
    CONSTRAINT `FKkfkn42xxk16njyqsem4oq9bbo`
        FOREIGN KEY (`team_image_id`)
            REFERENCES `meuequipo-local`.`image` (`id`),
    CONSTRAINT `FKr01s1jbaa1i4i4bimgqpf1oa`
        FOREIGN KEY (`category_id`)
            REFERENCES `meuequipo-local`.`category` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`rival`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`rival`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `email`              VARCHAR(255) NOT NULL,
    `name`               VARCHAR(255) NOT NULL,
    `responsible`        VARCHAR(255) NULL DEFAULT NULL,
    `tlf`                VARCHAR(255) NOT NULL,
    `logo_id`            BIGINT       NULL DEFAULT NULL,
    `season_id`          BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UKkvfm0vo0m6pnxxvri9hmk4f8t` (`logo_id` ASC) VISIBLE,
    INDEX `FK5skbv584ocr90lqkg1e5efnqw` (`season_id` ASC) VISIBLE,
    CONSTRAINT `FK5skbv584ocr90lqkg1e5efnqw`
        FOREIGN KEY (`season_id`)
            REFERENCES `meuequipo-local`.`season` (`id`),
    CONSTRAINT `FKsb8lujcw98otri78dp6fmgqer`
        FOREIGN KEY (`logo_id`)
            REFERENCES `meuequipo-local`.`image` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`game`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `game_date`          DATETIME(6)  NOT NULL,
    `game_state`         BIGINT       NOT NULL,
    `is_local`           BIT(1)       NOT NULL,
    `local_points`       INT          NULL DEFAULT NULL,
    `location`           VARCHAR(255) NOT NULL,
    `visitor_points`     INT          NULL DEFAULT NULL,
    `category_id`        BIGINT       NOT NULL,
    `rival_id`           BIGINT       NOT NULL,
    `season_id`          BIGINT       NOT NULL,
    `team_id`            BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKqfot8nih9aqqj3ql4ig0ei9ea` (`category_id` ASC) VISIBLE,
    INDEX `FKlyjh6b8rx8pvyysrxl2rdxset` (`rival_id` ASC) VISIBLE,
    INDEX `FK3xfbgv78vv6qrx7or7wnamon` (`season_id` ASC) VISIBLE,
    INDEX `FK1gq3iavetstsxyuqd92o5e6lj` (`team_id` ASC) VISIBLE,
    CONSTRAINT `FK1gq3iavetstsxyuqd92o5e6lj`
        FOREIGN KEY (`team_id`)
            REFERENCES `meuequipo-local`.`team` (`id`),
    CONSTRAINT `FK3xfbgv78vv6qrx7or7wnamon`
        FOREIGN KEY (`season_id`)
            REFERENCES `meuequipo-local`.`season` (`id`),
    CONSTRAINT `FKlyjh6b8rx8pvyysrxl2rdxset`
        FOREIGN KEY (`rival_id`)
            REFERENCES `meuequipo-local`.`rival` (`id`),
    CONSTRAINT `FKqfot8nih9aqqj3ql4ig0ei9ea`
        FOREIGN KEY (`category_id`)
            REFERENCES `meuequipo-local`.`category` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`player`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `birth_date`         DATE         NOT NULL,
    `name`               VARCHAR(255) NOT NULL,
    `player_sex`         BIGINT       NOT NULL,
    `surnames`           VARCHAR(255) NOT NULL,
    `category_id`        BIGINT       NOT NULL,
    `season_id`          BIGINT       NULL DEFAULT NULL,
    `team_id`            BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKqf6gkagc2mfcoatnev1fq84m4` (`category_id` ASC) VISIBLE,
    INDEX `FKa2coersmmlwta5gu4r5biekxo` (`season_id` ASC) VISIBLE,
    INDEX `FKdvd6ljes11r44igawmpm1mc5s` (`team_id` ASC) VISIBLE,
    CONSTRAINT `FKa2coersmmlwta5gu4r5biekxo`
        FOREIGN KEY (`season_id`)
            REFERENCES `meuequipo-local`.`season` (`id`),
    CONSTRAINT `FKdvd6ljes11r44igawmpm1mc5s`
        FOREIGN KEY (`team_id`)
            REFERENCES `meuequipo-local`.`team` (`id`),
    CONSTRAINT `FKqf6gkagc2mfcoatnev1fq84m4`
        FOREIGN KEY (`category_id`)
            REFERENCES `meuequipo-local`.`category` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`rival_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`rival_category`
(
    `rival_id`    BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,
    INDEX `FKkuda7bvmmgjp0leca6ejxi1ty` (`category_id` ASC) VISIBLE,
    INDEX `FK369tl28id9jar09xbvpf1nycv` (`rival_id` ASC) VISIBLE,
    CONSTRAINT `FK369tl28id9jar09xbvpf1nycv`
        FOREIGN KEY (`rival_id`)
            REFERENCES `meuequipo-local`.`rival` (`id`),
    CONSTRAINT `FKkuda7bvmmgjp0leca6ejxi1ty`
        FOREIGN KEY (`category_id`)
            REFERENCES `meuequipo-local`.`category` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`signin_period`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`signin_period`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `date_end`           DATE         NULL DEFAULT NULL,
    `date_init`          DATE         NULL DEFAULT NULL,
    `downloads`          INT          NULL DEFAULT NULL,
    `form_path`          VARCHAR(255) NULL DEFAULT NULL,
    `season_id`          BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK28pivicyu6f4bhr5y3v61ajsv` (`season_id` ASC) VISIBLE,
    CONSTRAINT `FKe3m1ua6railiujuiyudarxxq9`
        FOREIGN KEY (`season_id`)
            REFERENCES `meuequipo-local`.`season` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`sponsor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`sponsor`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `name`               VARCHAR(255) NOT NULL,
    `url`                VARCHAR(255) NULL DEFAULT NULL,
    `logo_id`            BIGINT       NULL DEFAULT NULL,
    `season_id`          BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UKjuwsefjrxdcthkks1957ph07m` (`logo_id` ASC) VISIBLE,
    INDEX `FK31618fasmbjol27382ja1fnfw` (`season_id` ASC) VISIBLE,
    CONSTRAINT `FK31618fasmbjol27382ja1fnfw`
        FOREIGN KEY (`season_id`)
            REFERENCES `meuequipo-local`.`season` (`id`),
    CONSTRAINT `FKfshuacax2m6q5ocwiqc4vdmml`
        FOREIGN KEY (`logo_id`)
            REFERENCES `meuequipo-local`.`image` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`squad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`squad`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `meeting_point` VARCHAR(255) NULL DEFAULT NULL,
    `meeting_time`  DATETIME(6)  NULL DEFAULT NULL,
    `game_id`       BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UKhyrtk8t648mj7j6ppi9rqdkcj` (`game_id` ASC) VISIBLE,
    CONSTRAINT `FKkw5m25lwvq8dwxivu2diag456`
        FOREIGN KEY (`game_id`)
            REFERENCES `meuequipo-local`.`game` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`squad_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`squad_player`
(
    `squad_id`  BIGINT NOT NULL,
    `player_id` BIGINT NOT NULL,
    INDEX `FKoteogwlsc61ta7xtc6fyo0n8b` (`player_id` ASC) VISIBLE,
    INDEX `FKe2jq4xhlvk06drcfdwrd0jyhm` (`squad_id` ASC) VISIBLE,
    CONSTRAINT `FKe2jq4xhlvk06drcfdwrd0jyhm`
        FOREIGN KEY (`squad_id`)
            REFERENCES `meuequipo-local`.`squad` (`id`),
    CONSTRAINT `FKoteogwlsc61ta7xtc6fyo0n8b`
        FOREIGN KEY (`player_id`)
            REFERENCES `meuequipo-local`.`player` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `meuequipo-local`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `meuequipo-local`.`user`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `created_by`         VARCHAR(255) NOT NULL,
    `created_date`       DATETIME(6)  NOT NULL,
    `last_modified_by`   VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date` DATETIME(6)  NULL DEFAULT NULL,
    `email`              VARCHAR(255) NOT NULL,
    `is_active`          BIT(1)       NOT NULL,
    `name`               VARCHAR(255) NOT NULL,
    `password`           VARCHAR(255) NOT NULL,
    `rol`                VARCHAR(255) NOT NULL,
    `surnames`           VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UKob8kqyqqgmefl0aco34akdtpe` (`email` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
