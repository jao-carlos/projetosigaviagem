package com.pi.classes;

public class ControladorDeEstados {
    // Verifica se o usuario verificou se algo esta impedindo da porta fechar
    private boolean verificouAlgoNaPorta = false;

    public boolean isVerificouAlgoNaPorta() {
        return verificouAlgoNaPorta;
    }

    public void setVerificouAlgoNaPorta(boolean verificouAlgoNaPorta) {
        this.verificouAlgoNaPorta = verificouAlgoNaPorta;
    }

    // Painel Externo
    private boolean painelExternoAberto = false;

    public boolean isPainelExternoAberto() {
        return painelExternoAberto;
    }

    public void setPainelExternoAberto(boolean painelExternoAberto) {
        this.painelExternoAberto = painelExternoAberto;
    }
    
  

}
