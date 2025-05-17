package com.pi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/projetosigaviagem"; 
    private static final String USUARIO = "postgres"; // Seu nome de usuário do PostgreSQL
    private static final String SENHA = "admin"; // Sua senha do PostgreSQL

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
