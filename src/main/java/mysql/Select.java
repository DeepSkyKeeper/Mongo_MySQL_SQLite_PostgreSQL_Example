package mysql;

import java.sql.*;

public class Select {
    /**
     * Connect to the some.db database
     * <p>
     * Return the Connection object
     */

    public void selectAll(Connection conn) {
        String sql = "select id,name,phone from Users";
        try (conn) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
//loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(Connection conn) {
        String sql = "select id,name,phone from Users";
        String sqlCreate = "\n" +
                "INSERT INTO Users (name,phone) values\n" +
                "('Petya','12323400'),\n" +
                "        ('Vasya','544546'),\n" +
                "        ('Katya',null)";
        Statement stmtCreate = null;
        try {
            stmtCreate = conn.createStatement();
            stmtCreate.execute(sqlCreate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Select app = new Select();
        app.selectAll(Connect.connect());
    }
}
