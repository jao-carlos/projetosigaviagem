package com.pi.telas.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    // Dados do banco Cloud SQL
    private static final String DB_IP = "34.75.213.100";
    private static final String DB_PORT = "5433";
    private static final String DB_NAME = "sigaviagem";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123";

    // Caminho do certificado dentro dos recursos do projeto (resources)
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

        System.out.println("Tentando conectar ao banco de dados...");
        try {
            Connection conn = DriverManager.getConnection(url, USUARIO, SENHA);
            System.out.println("✅ Conexão realizada com sucesso!");
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Erro na conexão: " + e.getMessage());
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
                throw new IOException("❌ Certificado não encontrado no caminho: " + CERT_PATH);
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

            System.out.println("✅ Certificado extraído para: " + tempFile.getAbsolutePath());
            return tempFile;
        }
    }
}
