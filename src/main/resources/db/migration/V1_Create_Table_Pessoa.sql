CREATE TABLE IF NOT EXISTS `pessoas` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `nome` varchar(180) NOT NULL,
    `data_nascimento` date,
    PRIMARY KEY (`id`)
)