package com.pi.telas.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:postgresql://34.75.213.100:5432/projetosigaviagem"; 
    private static final String USUARIO = "postgres"; 
    private static final String SENHA = "admin";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
