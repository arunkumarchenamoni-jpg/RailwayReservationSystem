import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static void main(String[] args) {

        try {

            Class.forName(
                "com.mysql.cj.jdbc.Driver"
            );

            Connection con =
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/railwaydb",
                    "root",
                    "root123"
                );

            System.out.println(
                "Database Connected Successfully"
            );

            con.close();
        }

        catch(Exception e) {

            System.out.println(e);
        }
    }
}