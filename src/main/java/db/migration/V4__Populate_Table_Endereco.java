package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V4__Populate_Table_Endereco extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO `enderecos` (`id`, `logradouro`, `cep`, `numero`, `cidade`, `principal`) VALUES \n" +
                        "    (2, 'Rua Sao Sebastiao', '51425-526', 154, 'São Paulo', 'principal'),\n" +
                        "    (3, 'Rua Rui Barbosa', '53455-126', 85, 'São Paulo', 'nao principal'),\n" +
                        "    (4, 'Rua Pernambuco', '53425-426', 14, 'Recife', 'principal'),\n" +
                        "    (5, 'Rua José Bonifácio', '31455-226', 13, 'Olinda', 'nao principal'),\n" +
                        "    (6, 'Rua Duque De Caxias ', '36775-321', 180, 'Rio de Janeiro', 'principal'),\n" +
                        "    (7, 'Rua Quinze De Novembro', '45625-222', 144, 'Fortaleza', 'principal'),\n" +
                        "    (8, 'Rua Santos Dumont', '21425-626', 11, 'Cabo Verde', 'principal'),\n" +
                        "    (9, 'Rua Dom Pedro II', '45425-124', 171, 'Porto Alegre', 'principal'),\n" +
                        "    (10, 'Rua Primeiro de Maio', '11425-551', 104, 'Porto Alegre', 'nao principal');");
    }
}
