package com.fiap.functions.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        // Pegando as variáveis das "Application Settings" do Azure
        String url = System.getenv("DB_URL"); // Ex: jdbc:mysql://server.mysql.database.azure.com
        String user = System.getenv("DB_USER");
        String pwd = System.getenv("DB_PWD");

        if (url == null || user == null || pwd == null) {
            throw new RuntimeException("Variáveis de ambiente do banco não configuradas");
        }

        // Parâmetros obrigatórios para Azure MySQL em 2026
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        props.setProperty("useSSL", "true");
        props.setProperty("requireSSL", "false");
        props.setProperty("serverTimezone", "UTC");
        props.setProperty("allowPublicKeyRetrieval", "true");

        return DriverManager.getConnection(url, props);
    }

}
