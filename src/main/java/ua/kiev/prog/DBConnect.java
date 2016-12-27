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

        Statement st = connection.createStatement();
        try {
            st.execute("CREATE DATABASE  IF NOT EXISTS `bd_orders`");
            st.execute("DROP TABLE IF EXISTS clients");
            st.execute("DROP TABLE IF EXISTS goods");
            st.execute("DROP TABLE IF EXISTS orders");
            st.execute("DROP TABLE IF EXISTS goodsinorders");
            st.execute("CREATE TABLE `clients` (\n" +
                    "  `id` int(3) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            st.execute("CREATE TABLE `goods` (\n" +
                    "  `id` int(3) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `price` double NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            st.execute("CREATE TABLE `orders` (\n" +
                    "  `id` int(3) NOT NULL AUTO_INCREMENT,\n" +
                    "  `id_client` int(3) NOT NULL,\n" +
                    "  `date` TIMESTAMP NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            st.execute("CREATE TABLE `goodsinorders` (\n" +
                    "  `id_good` int(3) NOT NULL,\n" +
                    "  `id_order` int(3) NOT NULL\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        } finally {
            st.close();
        }
        Operations operations = new Operations(this);
    }
    public Connection getConnection() {
        return connection;
    }
}
