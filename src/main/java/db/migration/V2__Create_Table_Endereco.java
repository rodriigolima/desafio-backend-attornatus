package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V2__Create_Table_Endereco extends BaseJavaMigration {

    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("CREATE TABLE enderecos (\n" +
                        "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                        "    logradouro varchar(180) NOT NULL,\n" +
                        "    cep varchar(80) NOT NULL,\n" +
                        "    numerO INTEGER NOT NULL,\n" +
                        "    cidade varchar(80) NOT NULL,\n" +
                        "    principal boolean NOT NULL\n" +
                        ");\n");
    }
}
