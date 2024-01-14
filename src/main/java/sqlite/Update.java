package sqlite;

import java.sql.*;

public class Update {
    /**
     * Connect to the sq.db database
     * <p>
     * Return the Connection object
     */
    private Connection connect() {
//SQLite connection string
        String url = "jdbc:sqlite:sq.db";
        Connection conn = null;
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

    public void update() {
        String sql = "update Users set name=? where id=?";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "Henry");
            stmt.setInt(2, 2);
            stmt.executeUpdate();
            System.out.println("Database updated successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Update app = new Update();
        app.update();
    }
}
