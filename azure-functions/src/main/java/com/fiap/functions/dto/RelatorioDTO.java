package com.fiap.functions.dto;

public class RelatorioDTO {
    private Long id;
    private String comentario;
    private Integer nota;
    private String tipoFeedback;
    private String usuario;
    private String email;
    private String aula;
    private String tipoDisciplina;

    public RelatorioDTO(Long id, String comentario, Integer nota, String tipoFeedback, String usuario, String email, String aula, String tipoDisciplina) {
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
        this.tipoFeedback = tipoFeedback;
        this.usuario = usuario;
        this.email = email;
        this.aula = aula;
        this.tipoDisciplina = tipoDisciplina;
    }

    public RelatorioDTO() {

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

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getTipoFeedback() {
        return tipoFeedback;
    }

    public void setTipoFeedback(String tipoFeedback) {
        this.tipoFeedback = tipoFeedback;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getTipoDisciplina() {
        return tipoDisciplina;
    }

    public void setTipoDisciplina(String tipoDisciplina) {
        this.tipoDisciplina = tipoDisciplina;
    }
}
