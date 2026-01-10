package com.fiap.functions.dto;

public class RelatorioDTO {

    private String tipo;
    private int totalFeedbacks;
    private String periodo;

    public RelatorioDTO() {
    }

    public RelatorioDTO(String tipo, int totalFeedbacks, String periodo) {
        this.tipo = tipo;
        this.totalFeedbacks = totalFeedbacks;
        this.periodo = periodo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTotalFeedbacks(int totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
