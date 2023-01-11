CREATE TABLE IF NOT EXISTS `pessoa_endereco` (
    `pessoa_id` INTEGER NOT NULL,
    `endereco_id` INTEGER NOT NULL,
    PRIMARY KEY(pessoa_id, endereco_id) 
)