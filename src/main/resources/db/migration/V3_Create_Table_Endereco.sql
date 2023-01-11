CREATE TABLE IF NOT EXISTS `enderecos` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `logradouro` varchar(180) NOT NULL,
    `cep` varchar(80) NOT NULL,
    `numero` bigint(6) NOT NULL,
    `cidade` varchar(80) NOT NULL,
    `principal` boolean NOT NULL,
    PRIMARY KEY(id)
)