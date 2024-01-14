package sqlite;

import java.net.ConnectException;
import java.sql.*;

public class Select {
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
            Statement stmt =conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql)){
//loop through the result set
            while(rs.next()){
                System.out.println(rs.getInt("id")+"\t"+
                        rs.getString("name")+"\t"+
                        rs.getString("phone"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void selectAllInMaria(Connection conn){
        String sql = "select id,name,phone from Users where name like '%Petya%'";
        try(conn) {
            String sqlCreate ="\n"+
                    "INSERT INTO Users (name,phone) values\n" +
                    "('Petya','12323400'),\n" +
                    "        ('Vasya','544546'),\n" +
                    "        ('Katya',null)";
            Statement stmtCreate =conn.createStatement();
            stmtCreate.execute(sqlCreate);

            Statement stmt =conn.createStatement();
            ResultSet rs =stmt.executeQuery(sql);
//loop through the result set
            while(rs.next()){
                System.out.println(rs.getInt("id")+"\t"+
                        rs.getString("name")+"\t"+
                        rs.getString("phone"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        Select app=new Select();
        app.selectAll();
    }
}
