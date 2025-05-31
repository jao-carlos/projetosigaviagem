package com.pi.telas.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {

    private static final Logger LOGGER = Logger.getLogger(ConexaoBD.class.getName());

    // Troca essas variáveis pelos seus dados do Supabase
    private static final String DB_HOST = "aws-0-us-east-2.pooler.supabase.com";
    private static final String DB_PORT = "6543";
    private static final String DB_NAME = "postgres";  // padrão Supabase
    private static final String USUARIO = "postgres.dqzsugrhokpychwsaxon";  // <--- seu usuário do Supabase
    private static final String SENHA = "Mauaehlegal123";  // <--- sua senha do Supabase

    public static Connection conectar() throws SQLException {
        validarVariaveisAmbiente();

        String url = String.format(
            "jdbc:postgresql://%s:%s/%s?sslmode=require",
            DB_HOST, DB_PORT, DB_NAME
        );

        LOGGER.info("Tentando conectar ao banco Supabase PostgreSQL...");
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
            String msg = "❌ Variáveis para conexão Supabase estão incompletas. " +
                    "Verifique DB_HOST, DB_PORT, DB_NAME, USUARIO e SENHA.";
            LOGGER.severe(msg);
            throw new IllegalStateException(msg);
        }
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
