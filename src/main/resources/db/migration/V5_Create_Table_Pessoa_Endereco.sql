CREATE TABLE IF NOT EXISTS `pessoa_endereco` (
    `pessoa_id` bigint(20) NOT NULL,
    `endereco_id` bigint(20) NOT NULL,
    PRIMARY KEY(pessoa_id, endereco_id) 
)