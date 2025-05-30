package com.pi.telas.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {

    private static final Logger LOGGER = Logger.getLogger(ConexaoBD.class.getName());

    // Variáveis de ambiente (espera que estejam setadas)
    private static final String DB_HOST = "localhost";  // Ex: localhost
    private static final String DB_PORT = "5432";  // Ex: 5433
    private static final String DB_NAME = "projetosigaviagem";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "admin";

    public static Connection conectar() throws SQLException {
        validarVariaveisAmbiente();

        String url = String.format(
            "jdbc:postgresql://%s:%s/%s",
            DB_HOST, DB_PORT, DB_NAME
        );

        LOGGER.info("Tentando conectar ao banco local PostgreSQL...");
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
        if (isNullOrEmpty(DB_HOST) || isNullOrEmpty(DB_PORT) ||
            isNullOrEmpty(DB_NAME) || isNullOrEmpty(USUARIO) || isNullOrEmpty(SENHA)) {
            String msg = "❌ Variáveis de ambiente para conexão local estão incompletas ou não definidas. " +
                    "Verifique DB_HOST, DB_PORT, DB_NAME, DB_USER e DB_PASS.";
            LOGGER.severe(msg);
            throw new IllegalStateException(msg);
        }
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
