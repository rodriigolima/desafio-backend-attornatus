CREATE TABLE IF NOT EXISTS `pessoas` (
    `id` SERIAL,
    `nome` varchar(180) NOT NULL,
    `data_nascimento` date NOT NULL,
    PRIMARY KEY (`id`)
)

