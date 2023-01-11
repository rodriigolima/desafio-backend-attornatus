CREATE TABLE IF NOT EXISTS `enderecos` (
    `id` SERIAL,
    `logradouro` varchar(180) NOT NULL,
    `cep` varchar(80) NOT NULL,
    `numero` INTEGER NOT NULL,
    `cidade` varchar(80) NOT NULL,
    `principal` enum('principal','nao principal') NOT NULL,
    PRIMARY KEY(id)
)
