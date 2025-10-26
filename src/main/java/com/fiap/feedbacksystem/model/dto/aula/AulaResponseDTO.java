package com.fiap.feedbacksystem.model.dto.aula;

import com.fiap.feedbacksystem.model.entity.Usuario;
import com.fiap.feedbacksystem.model.enums.TipoDisciplina;

public class AulaResponseDTO {

    int id;
    TipoDisciplina tipoDisciplina; // "Matemática", "Portugues" ou "História"
    String descricao;
    Usuario administrador;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public TipoDisciplina getTipoDisciplina() { return tipoDisciplina; }
    public void setTipoDisciplina(TipoDisciplina tipoDisciplina) { this.tipoDisciplina = tipoDisciplina; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Usuario getAdministrador() { return administrador; }
    public void setAdministrador(Usuario administrador) { this.administrador = administrador; }
}
