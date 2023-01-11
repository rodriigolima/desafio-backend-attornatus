package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V6__Populate_Table_Pessoa_Endereco extends BaseJavaMigration {
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO `pessoa_endereco` (`pessoa_id`, `endereco_id`) VALUES \n" +
                        "    (2,2),\n" +
                        "    (2,3),\n" +
                        "    (3,4),\n" +
                        "    (3,5),\n" +
                        "    (4,6),\n" +
                        "    (5,7),\n" +
                        "    (6,8),\n" +
                        "    (7,9),\n" +
                        "    (7,10);");
    }
}
