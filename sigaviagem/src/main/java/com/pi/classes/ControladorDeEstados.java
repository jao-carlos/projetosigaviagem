package com.pi.classes;

public class ControladorDeEstados {    
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
    private boolean paResolvido = false;
    private boolean ccoResolvido = false;
    private int posChaveReversora = 0;
    private int posChaveCBTC = 0;
    private int pontuacao = 0;

    public void setPontuacao(int pontos) {
        this.pontuacao = pontos;
    }

    public void incrementarPontuacao(int pontos) {
        this.pontuacao += pontos;
    }

    public int getPontuacao() {
        return pontuacao;
    }

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
    
    public int getPosChaveCBTC() {
        return posChaveCBTC;
    }

    public void setPosChaveCBTC(int pos) {
        this.posChaveCBTC = pos;
    }
   
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

    
    
    public boolean isPaResolvido() {
        return paResolvido;
    }

    public void setPaResolvido(boolean paResolvido) {
        this.paResolvido = paResolvido;
    }

    public boolean isCcoResolvido() {
        return ccoResolvido;
    }

    public void setCcoResolvido(boolean ccoResolvido) {
        this.ccoResolvido = ccoResolvido;
    }

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

    public int calcularPontuacaoFinal() {
        int pontos = 0;    //GENTE SE ALGUEM LEMBRAR DE MAIS ALGO ADCIONA POR FAVOR E FALA COMIGO QUE EU AJUDO = Leo

        // Ações positivas
        if (paSegurarPorta || paProblema) pontos += 1; // Emitiu PA
        if (avisoCCO) pontos += 1; // Comunicou o CCO
        if (!painelExternoAberto) pontos += 1; // Fechou o painel externo
        if (portaComFita) pontos += 3; // Usou fita na porta
        if (portaFechada) pontos += 4; // Porta finalizada fechada
        if (posChaveReversora == 0) pontos += 1; // Deixou em neutro
        if (posChaveCBTC == 0) pontos += 1; // CBTC em AM no final
        if (verificouAlgoNaPorta) pontos += 2; // Verificou a porta

        // Penalizações
        if (painelExternoAberto) pontos -= 2; // Deixou painel aberto
        if (!portaFechada) pontos -= 4; // Porta ainda aberta
        if (posChaveReversora == 2) pontos -= 3; // Chave em ré na partida
        if (posChaveCBTC != 0) pontos -= 2; // CBTC fora de AM no final
        if (!avisoCCO) pontos -= 1; // Não comunicou o CCO
        if (!paSegurarPorta && !paProblema) pontos -= 1; // Não deu nenhum PA
        if (!verificouAlgoNaPorta) pontos -= 2; // Não verificou a porta
        if (chave1Ativa) pontos -= 3;
        if (chave2Ativa) pontos -= 3;
        if (chave4Ativa) pontos -= 3;

        return pontos;
    }



}
