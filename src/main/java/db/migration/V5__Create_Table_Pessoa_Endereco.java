package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V5__Create_Table_Pessoa_Endereco extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("CREATE TABLE pessoa_endereco (\n" +
                        "    pessoa_id INTEGER NOT NULL,\n" +
                        "    endereco_id INTEGER NOT NULL,\n" +
                        "    PRIMARY KEY(pessoa_id, endereco_id) \n" +
                        ");");
    }
}
