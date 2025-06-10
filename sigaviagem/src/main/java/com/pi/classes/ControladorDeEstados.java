package com.pi.classes;

public class ControladorDeEstados {    // Verifica se o usuario verificou se algo esta impedindo da porta fechar
    private boolean verificouAlgoNaPorta = false;
    private boolean portaFechada=false;
    private boolean portaComFita=false;
    private boolean chave1Ativa = false;
    private boolean chave2Ativa = false;
    private boolean chave3Ativa = false;
    private boolean chave4Ativa = false;
    private boolean paSegurarPorta = false;
    private boolean paProblema = false;
    private boolean avisoCCO = false;
    private boolean boleira = false;
    private int posChaveReversora = 0;
    private int posChaveCBTC = 0;


    
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
    //Chave CBTC
    public int getPosChaveCBTC() {
        return posChaveCBTC;
    }

    public void setPosChaveCBTC(int pos) {
        this.posChaveCBTC = pos;
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
    
    // Estados individuais das chaves

    public boolean isPaSegurarPorta() {
        return paSegurarPorta;
    }

    public void setPaSegurarPorta(boolean paSegurarPorta) {
        this.paSegurarPorta = paSegurarPorta;
    }

    public boolean isPaProblema() {
        return paProblema;
    }

    public void setPaProblema(boolean paProblema) {
        this.paProblema = paProblema;
    }

    public boolean isAvisoCCO() {
        return avisoCCO;
    }

    public void setAvisoCCO(boolean avisoCCO) {
        this.avisoCCO = avisoCCO;
    }

    public boolean isBoleira() {
        return boleira;
    }

    public void setBoleira(boolean boleira) {
        this.boleira = boleira;
    }

    // public int getPosChaveCBTC() {
    //     return posChaveCBTC;
    // }

    // public void setPosChaveCBTC(int pos) {
    //     this.posChaveCBTC = pos;
    // }

    public int getPosChaveReversora() {return posChaveReversora;}
    public void setPosChaveReversora(int pos) {this.posChaveReversora = pos;}

    public boolean isChave1Ativa() { return chave1Ativa; }
    public void setChave1Ativa(boolean chave1Ativa) { this.chave1Ativa = chave1Ativa; }

    public boolean isChave2Ativa() { return chave2Ativa; }
    public void setChave2Ativa(boolean chave2Ativa) { this.chave2Ativa = chave2Ativa; }

    public boolean isChave3Ativa() { return chave3Ativa; }
    public void setChave3Ativa(boolean chave3Ativa) { this.chave3Ativa = chave3Ativa; }

    public boolean isChave4Ativa() { return chave4Ativa; }
    public void setChave4Ativa(boolean chave4Ativa) { this.chave4Ativa = chave4Ativa; }
}
