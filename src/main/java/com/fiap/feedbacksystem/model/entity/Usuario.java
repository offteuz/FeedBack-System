package com.fiap.feedbacksystem.model.entity;

import com.fiap.feedbacksystem.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence", allocationSize = 1)
    int id;

    String nome;

    @Column(unique = true)
    String email;

    @Enumerated(EnumType.STRING)
    TipoUsuario tipoUsuario;
}
