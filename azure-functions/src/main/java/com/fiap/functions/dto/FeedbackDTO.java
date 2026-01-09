package com.fiap.functions.dto;

import java.time.LocalDate;

public class FeedbackDTO {

    private Long id;
    private String comentario;
    private int nota;
    private String tipoFeedback;

    public FeedbackDTO(Long id, String comentario, int nota, String tipoFeedback) {
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
        this.tipoFeedback = tipoFeedback;
    }

    public FeedbackDTO() {

    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoFeedback() {
        return tipoFeedback;
    }

    public void setTipoFeedback(String tipoFeedback) {
        this.tipoFeedback = tipoFeedback;
    }
}

