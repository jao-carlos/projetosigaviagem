package com.pi.classes;

public class ControladorDeEstados {    // Verifica se o usuario verificou se algo esta impedindo da porta fechar
    private boolean verificouAlgoNaPorta = false;
    private boolean portaFechada=false;
    private boolean portaComFita=false;

    
    //Porta do carro
    public boolean isPortaFechada() {
        return portaFechada;
    }

    public void setPortaFechada(boolean portaFechada) {
        this.portaFechada = portaFechada;
    }

    public boolean isPortaComFita() {
        return portaComFita;
    }

    public void setPortaComFita(boolean portaComFita) {
        this.portaComFita = portaComFita;
    }

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
    
    private boolean adesivoRemovido = false;
    private boolean cinturaoRemovido = false;

    public boolean isAdesivoRemovido() {
        return adesivoRemovido;
    }

    public void setAdesivoRemovido(boolean removido) {
        this.adesivoRemovido = removido;
    }

    public boolean isCinturaoRemovido() {
        return cinturaoRemovido;
    }

    public void setCinturaoRemovido(boolean removido) {
        this.cinturaoRemovido = removido;
    }

    private long tempoInicio;

    public void marcarInicio() {
        tempoInicio = System.currentTimeMillis();
    }

    public long tempoDecorridoEmSegundos() {
        return (System.currentTimeMillis() - tempoInicio) / 1000;
    }

}
