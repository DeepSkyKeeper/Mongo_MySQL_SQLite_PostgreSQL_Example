package postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    public static Connection connect() {
        Connection conn = null;
        try {
            //db paratmeters
            String url = "jdbc:postgresql://localhost:5432/mypostgresdb";
            //create a connection to the database
            conn = DriverManager.getConnection(url,"someuser","somepass");
            System.out.println("Connection to Postgres has been established");
            conn.createStatement().execute("create table if not exists \"Users\"(\n" +
                    "id INt generated always as identity,\n" +
                    "name varchar(20) not null,\n" +
                    "primary key(id)\n" +
                    ");\n"+
                    "create table \"Contacts\"(\n" +
                    "id INT generated always as identity,\n" +
                    "\"customerId\" INT,\n" +
                    "\"contactName\" VarChar(255) not null,\n" +
                    "phone varchar(15),\n" +
                    "email varchar(100),\n" +
                    "primary key(id),\n" +
                    "constraint \"fk_Users_Contacts\"\n" +
                    "foreign key(\"customerId\")\n" +
                    "references \"Users\"(id)\n" +
                    ");");
            System.out.println("Tables created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
