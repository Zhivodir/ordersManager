package ua.kiev.prog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by User on 21.12.2016.
 */
public class DBConnect {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/bd_orders";
    static final String DB_USER = "admin";
    static final String DB_PASSWORD = "admin";

    static Connection connection;

    public DBConnect() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            initDB();
        }catch(SQLException e){e.printStackTrace();}
    }

    public void initDB() throws SQLException {
//
//        Statement st = connection.createStatement();
//        try {
//            st.execute("CREATE DATABASE  IF NOT EXISTS `apartments`");
//            st.execute("DROP TABLE IF EXISTS flats");
//            st.execute("CREATE TABLE `flats` (\n" +
//                    "  `id` int(2) NOT NULL AUTO_INCREMENT,\n" +
//                    "  `district` varchar(45) NOT NULL,\n" +
//                    "  `address` varchar(45) NOT NULL,\n" +
//                    "  `area` double NOT NULL,\n" +
//                    "  `rooms` int(2) NOT NULL,\n" +
//                    "  `price` double NOT NULL,\n" +
//                    "  PRIMARY KEY (`id`)\n" +
//                    ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;");
//            st.execute("INSERT INTO `flats` VALUES (1,'Darnitsa','Voskresenskaya 14',100,3,90000),\n" +
//                    "  (2,'Darnitsa','Voskresenskaya 14',55,1,45000),\n" +
//                    "  (3,'Borschagovka','Ulica 22',85,3,70000),\n" +
//                    "  (4,'Sviatoshin','Pobedi 2',100,3,95000),\n" +
//                    "  (5,'Darnitsa','Voskresenskaya 12',50,1,40000);");
//        } finally {
//            st.close();
//        }
        Operations operations = new Operations(this);
    }

    public Connection getConnection() {
        return connection;
    }
}
