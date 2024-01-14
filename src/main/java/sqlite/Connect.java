package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static void main(String[] args) {
//        sqliteConnect();
        mariaConnect();
    }
    public static void sqliteConnect() {
        Connection conn = null;
        try {
            //db paratmeters
            String url = "jdbc:sqlite:sq.db";
            //create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null){
                    conn.close();
                }
            }catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static Connection mariaConnect() {
        Connection conn = null;
        try {
            //db paratmeters
            String url = "jdbc:mariadb://localhost:3306/somedb";
            //create a connection to the database
            conn = DriverManager.getConnection(url,"someuser","somepass");
            System.out.println("Connection to Maria has been established");
            conn.createStatement().execute("CREATE table if not exists Users(\n" +
                    "id int primary key auto_increment,\n" +
                    "name varchar(20) not null,\n" +
                    "phone varchar(20) default null\n" +
                    ");");
            System.out.println("Tables created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
