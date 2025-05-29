package com.pi.telas.login;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBD {

    // Configurações do banco
    private static final String DB_IP = "34.75.213.100";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "projetosigaviagem";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "admin";

    // Caminho do certificado dentro do projeto (resources)
    private static final String CERT_PATH = "/sigaviagem/com/pi/server-ca.pem";

    /**
     * Método para conectar no banco Cloud SQL com SSL
     */
    public static Connection conectar() throws Exception {
        // Extrai o certificado para um arquivo temporário
        File certFile = extrairCertificadoTemporario();

        // Monta a URL JDBC com SSL habilitado
        String url = String.format(
                "jdbc:postgresql://%s:%s/%s?sslmode=verify-ca&sslrootcert=%s",
                DB_IP, DB_PORT, DB_NAME, certFile.getAbsolutePath()
        );

        return DriverManager.getConnection(url, USUARIO, SENHA);
    }

    /**
     * Extrai o certificado do resources para arquivo temporário no sistema
     */
    private static File extrairCertificadoTemporario() throws Exception {
        InputStream is = ConexaoBD.class.getResourceAsStream(CERT_PATH);
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
        }

        return tempFile;
    }
}
