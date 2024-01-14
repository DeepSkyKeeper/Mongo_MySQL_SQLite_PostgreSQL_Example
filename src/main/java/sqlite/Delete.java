package sqlite;

import java.sql.*;

public class Delete {
    /**
     * Connect to the sq.db database
     *
     * Return the Connection object
     */
    private Connection connect(){
//SQLite connection string
        String url = "jdbc:sqlite:sq123.db";
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url);
            conn.createStatement().execute("CREATE table if not exists Users(\n" +
                    "id integer primary key autoincrement,\n" +
                    "name varchar(20) not null,\n" +
                    "phone varchar(20) default null\n" +
                    ");");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void selectAll(){
        String sql = "select id,name,phone from Users where name like '%Petya%'";
        try(Connection conn=this.connect();
            Statement stmt =conn.createStatement()){
            stmt.execute("Delete from Users;");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Delete app=new Delete();
        app.selectAll();
    }
}
