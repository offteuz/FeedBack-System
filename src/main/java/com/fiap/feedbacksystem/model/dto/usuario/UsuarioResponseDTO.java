package com.fiap.feedbacksystem.model.dto.usuario;

import com.fiap.feedbacksystem.model.enums.TipoUsuario;

public class UsuarioResponseDTO {

    int id;
    String nome;
    String email;
    TipoUsuario tipoUsuario; // "Estudante" ou "Administrador"

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}
