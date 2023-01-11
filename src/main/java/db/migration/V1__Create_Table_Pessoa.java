package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.PreparedStatement;

public class V1__Create_Table_Pessoa  extends BaseJavaMigration {
    
    public void migrate(Context context) throws Exception {
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("CREATE TABLE  pessoas (\n" +
                        "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                        "    nome varchar(180) NOT NULL, \n" +
                        "    data_nascimento date NOT NULL\n" +
                        ");\n");
    }
}
