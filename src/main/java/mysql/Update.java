package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    /**
     * Connect to the somedb.db database
     * <p>
     * Return the Connection object
     */
    Connection conn;

    public static void update(Connection conn) {
        String sql = "update Users set name=? where id=?";
        try (conn) {
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Henry");
            stmt.setInt(2, 2);
            stmt.executeUpdate();
            System.out.println("Database updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
       update(Connect.connect());
    }
}
