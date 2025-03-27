package org.projects.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabasesConfig {
    private static String url;
    private static String user;
    private static String password;

        static {
            try(InputStream inp = DatabasesConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
                Properties prt = new Properties();
                prt.load(inp);
                url = prt.getProperty("db.url");
                user = prt.getProperty("db.user");
                password = prt.getProperty("db.password");
            } catch(IOException e) {
                e.printStackTrace();
                throw new RuntimeException("error..................");
            }
        }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }   
}
