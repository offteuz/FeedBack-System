package com.fiap.feedbacksystem.model.dto.usuario;

import com.fiap.feedbacksystem.model.enums.TipoUsuario;

public class UsuarioRequestDTO {

    String nome;
    String email;
    TipoUsuario tipoUsuario; // "Estudante" ou "Administrador"

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}
