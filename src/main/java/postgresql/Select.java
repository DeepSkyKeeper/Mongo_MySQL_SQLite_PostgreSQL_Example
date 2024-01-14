package postgresql;

import models.Contact;
import models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Select {
    /**
     * Connect to the some.db database
     * <p>
     * Return the Connection object
     */

    public void selectAll(Connection conn) {
        String sql = "select u.id as \"Users->id\", u.\"name\" as \"Users->name\"," +
                "c.id as \"contactId\",c.\"contactName\" ,c.phone,c.email " +
                "from \"Users\" u left join \"Contacts\" c on c. \"customerId\"=u.id order by \"Users->id\";";
        List<User> users =new ArrayList<>();
        try (conn) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
//loop through the result set
            User tmpUser=null;
            List<Contact> tmpContacts = new ArrayList<Contact>();

            while (rs.next()) {
                if(tmpUser!=null && tmpUser.id!=rs.getInt("Users->id")){
                    tmpUser.contacts=tmpContacts;
                    users.add(tmpUser);
                    tmpUser=null;
                }
                tmpUser =new User(rs.getInt("Users->id"),rs.getString("Users->name"));
                tmpContacts.add(new Contact(rs.getInt("contactId"),
                        rs.getInt("Users->id"),
                        rs.getString("contactName"),rs.getString("phone"),
                        rs.getString("email")));
            }
            if(tmpUser!=null){
                tmpUser.contacts=tmpContacts;
                users.add(tmpUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(users);
    }

    public void insert(Connection conn,String sql) {
        Statement stmtCreate = null;
        try {
            stmtCreate = conn.createStatement();
            stmtCreate.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Select app = new Select();
        Connection conn = Connect.connect();
        String sqlUsers = "insert into \"Users\" (name) values\n" +
                "('Petya'),\n" +
                "('Vasya'),\n" +
                "('Katya');";
        String sqlContacts = "insert into \"Contacts\" (\"customerId\",\"contactName\",phone,email) values\n" +
                "(1,'First','1234567','email@reiwjv.ru'),\n" +
                "(2,'Second','98198191','wergfwregl@v.ru'),\n" +
                "(3,'Main','78498111','eregl@rrev.ru');";
//        app.insert(conn,sqlContacts);
        app.selectAll(conn);
    }
}
