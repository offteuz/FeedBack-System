package com.fiap.functions.dto;

public class RelatorioSemanalDTO {

    private String periodo;
    private long totalFeedbacks;
    private double mediaAvaliacao;

    public RelatorioSemanalDTO() {
    }

    public RelatorioSemanalDTO(String periodo, long totalFeedbacks, double mediaAvaliacao) {
        this.periodo = periodo;
        this.totalFeedbacks = totalFeedbacks;
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public long getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(long totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }
}

