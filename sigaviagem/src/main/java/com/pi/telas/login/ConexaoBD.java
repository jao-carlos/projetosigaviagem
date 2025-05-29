package com.pi.telas.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoBD {

    private static final Logger LOGGER = Logger.getLogger(ConexaoBD.class.getName());

    // Variáveis de ambiente que você deve setar no seu sistema
    private static final String DB_IP = System.getenv("DB_IP");
    private static final String DB_PORT = System.getenv("DB_PORT");
    private static final String DB_NAME = System.getenv("DB_NAME");
    private static final String USUARIO = System.getenv("DB_USER");
    private static final String SENHA = System.getenv("DB_PASS");

    private static final String CERT_PATH = "server-ca.pem";

    /**
     * Estabelece conexão segura com o banco PostgreSQL no Cloud SQL usando SSL com certificado.
     *
     * @return Conexão ativa com o banco de dados
     * @throws SQLException em caso de falha na conexão
     * @throws IOException  em caso de falha ao extrair certificado
     */
    public static Connection conectar() throws SQLException, IOException {
        File certFile = extrairCertificadoTemporario();

        String url = String.format(
            "jdbc:postgresql://%s:%s/%s?sslmode=verify-ca&sslrootcert=%s",
            DB_IP, DB_PORT, DB_NAME, certFile.getAbsolutePath()
        );

        LOGGER.info("Tentando conectar ao banco de dados...");
        try {
            Connection conn = DriverManager.getConnection(url, USUARIO, SENHA);
            LOGGER.info("✅ Conexão realizada com sucesso!");
            return conn;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "❌ Erro na conexão: " + e.getMessage(), e);
            throw new SQLException("Falha ao conectar ao banco: ", e);
        }
    }

    /**
     * Extrai o certificado SSL do resources para um arquivo temporário no sistema.
     *
     * @return Arquivo temporário contendo o certificado SSL
     * @throws IOException em caso de erro ao acessar ou criar o arquivo do certificado
     */
    private static File extrairCertificadoTemporario() throws IOException {
        try (InputStream is = ConexaoBD.class.getClassLoader().getResourceAsStream(CERT_PATH)) {

            if (is == null) {
                String msg = "❌ Certificado não encontrado no caminho: " + CERT_PATH;
                LOGGER.severe(msg);
                throw new IOException(msg);
            }

            File tempFile = File.createTempFile("server-ca", ".pem");
            tempFile.deleteOnExit();

            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[4096];
                int bytesLidos;
                while ((bytesLidos = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesLidos);
                }
            }

            LOGGER.info("✅ Certificado extraído para: " + tempFile.getAbsolutePath());
            return tempFile;
        }
    }
}
