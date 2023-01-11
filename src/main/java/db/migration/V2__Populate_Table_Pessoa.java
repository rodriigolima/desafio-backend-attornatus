package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V2__Populate_Table_Pessoa extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO `pessoas` (`id`, `nome`, `data_nascimento`) VALUES \n" +
                        "    (2, 'Leandro Costa', '1994-02-12'),\n" +
                        "    (3, 'Gabriela Costa', '1991-02-09'),\n" +
                        "    (4, 'Flávio Moreira', '1988-06-13'),\n" +
                        "    (5, 'Fernanda Cardoso da Silva', '1996-06-10'),\n" +
                        "    (6, 'Artur Pedro Lima', '1991-11-09'),\n" +
                        "    (7, 'Marcos Paulo', '1993-03-12')");
    }
}
