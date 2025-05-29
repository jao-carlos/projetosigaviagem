package com.pi.telas.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    // Configurações do banco
    private static final String DB_IP = "34.75.213.100";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "projetosigaviagem";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "admin";

    // Caminho do certificado no resources (sem /src ou /java)
    private static final String CERT_PATH = "sigaviagem\\src\\main\\java\\server-ca.pem";

    /**
     * Método para conectar no banco Cloud SQL com SSL
     */
    public static Connection conectar() throws Exception {
        File certFile = extrairCertificadoTemporario();

        String url = String.format(
                "jdbc:postgresql://%s:%s/%s?sslmode=verify-ca&sslrootcert=%s",
                DB_IP, DB_PORT, DB_NAME, certFile.getAbsolutePath()
        );

        try {
            System.out.println("Tentando conectar ao banco de dados...");
            Connection conn = DriverManager.getConnection(url, USUARIO, SENHA);
            System.out.println("Conexão realizada com sucesso!");
            return conn;
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Extrai o certificado do resources para um arquivo temporário
     */
    private static File extrairCertificadoTemporario() throws Exception {
        InputStream is = ConexaoBD.class.getClassLoader().getResourceAsStream(CERT_PATH);
        if (is == null) {
            throw new RuntimeException("Certificado não encontrado no caminho: " + CERT_PATH);
        }

        File tempFile = File.createTempFile("server-ca", ".pem");
        tempFile.deleteOnExit();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int bytesLidos;
            while ((bytesLidos = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLidos);
            }
        } finally {
            is.close();
        }

        System.out.println("Certificado extraído para: " + tempFile.getAbsolutePath());
        return tempFile;
    }
}
