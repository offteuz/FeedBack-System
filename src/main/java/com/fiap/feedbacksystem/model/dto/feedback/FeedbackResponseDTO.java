package com.fiap.feedbacksystem.model.dto.feedback;

import com.fiap.feedbacksystem.model.enums.TipoFeedback;

public class FeedbackResponseDTO {

    int id;
    int idUsuario;
    int idAula;
    int nota; // Nota de 1 a 10
    String comentario;
    TipoFeedback tipoFeedback; // "URGENTE", "ELOGIO" ou "CRITICA"

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public int getIdAula() { return idAula; }
    public void setIdAula(int idAula) { this.idAula = idAula; }
    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public TipoFeedback getTipoFeedback() { return tipoFeedback; }
    public void setTipoFeedback(TipoFeedback tipoFeedback) { this.tipoFeedback = tipoFeedback; }
}
