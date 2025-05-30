package com.pi.telas.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {

    private static final Logger LOGGER = Logger.getLogger(ConexaoBD.class.getName());

    // Variáveis de ambiente (espera que estejam setadas)
    private static final String DB_NAME = System.getenv("DB_NAME");                    // Ex: projetosigaviagem
    private static final String CLOUD_SQL_CONNECTION_NAME = System.getenv("CLOUD_SQL_CONNECTION_NAME");  // Ex: projeto:regiao:instancia
    private static final String USUARIO = System.getenv("DB_USER");
    private static final String SENHA = System.getenv("DB_PASS");
    private static final String DB_PORT = "5433";

    public static Connection conectar() throws SQLException {
        validarVariaveisAmbiente();

     
String url = String.format(
    "jdbc:postgresql://google/%s?cloudSqlInstance=%s&socketFactory=com.google.cloud.sql.postgres.SocketFactory&useSSL=false",
    DB_NAME, CLOUD_SQL_CONNECTION_NAME);


        LOGGER.info("Tentando conectar ao banco via Cloud SQL Proxy...");
        LOGGER.info("URL de conexão (sem senha): " + url);
        LOGGER.info("Usuário: " + USUARIO);

        try {
            Connection conn = DriverManager.getConnection(url, USUARIO, SENHA);
            LOGGER.info("✅ Conexão realizada com sucesso!");
            return conn;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "❌ Erro na conexão: " + e.getMessage(), e);
            throw e;
        }
    }

    private static void validarVariaveisAmbiente() {
        if (isNullOrEmpty(DB_NAME) || isNullOrEmpty(CLOUD_SQL_CONNECTION_NAME) || isNullOrEmpty(USUARIO) || isNullOrEmpty(SENHA)) {
            String msg = "❌ Variáveis de ambiente para conexão com o banco estão incompletas ou não definidas. " +
                    "Verifique DB_NAME, CLOUD_SQL_CONNECTION_NAME, DB_USER e DB_PASS.";
            LOGGER.severe(msg);
            throw new IllegalStateException(msg);
        }
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
