package com.fiap.functions.dto;

import java.util.List;
import java.util.Map;

public class RelatorioSemanalDTO {

    private int totalFeedbacks;
    private int totalUrgentes;
    private double mediaNotas;
    private Map<String, Integer> feedbacksPorDia;

    public RelatorioSemanalDTO(int totalFeedbacks, int totalUrgentes, double mediaNotas, Map<String, Integer> feedbacksPorDia) {
        this.totalFeedbacks = totalFeedbacks;
        this.totalUrgentes = totalUrgentes;
        this.mediaNotas = mediaNotas;
        this.feedbacksPorDia = feedbacksPorDia;
    }

    public RelatorioSemanalDTO() {

    }

    public int getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(int totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public int getTotalUrgentes() {
        return totalUrgentes;
    }

    public void setTotalUrgentes(int totalUrgentes) {
        this.totalUrgentes = totalUrgentes;
    }

    public double getMediaNotas() {
        return mediaNotas;
    }

    public void setMediaNotas(double mediaNotas) {
        this.mediaNotas = mediaNotas;
    }

    public Map<String, Integer> getFeedbacksPorDia() {
        return feedbacksPorDia;
    }

    public void setFeedbacksPorDia(Map<String, Integer> feedbacksPorDia) {
        this.feedbacksPorDia = feedbacksPorDia;
    }
}
