package com.fiap.feedbacksystem.service;

import com.fiap.feedbacksystem.model.dto.usuario.UsuarioRequestDTO;
import com.fiap.feedbacksystem.model.entity.Usuario;
import com.fiap.feedbacksystem.repository.UsuarioRepository;
import com.fiap.feedbacksystem.security.service.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));
        return new UserDetailsImpl(usuario);
    }

    public Usuario registerUser(UsuarioRequestDTO dto) {
        if (this.usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        String encryptedPassword = passwordEncoder.encode(dto.getSenha());

        Usuario newUser = new Usuario(dto.getNome(), dto.getEmail(), encryptedPassword, dto.getTipoUsuario());

        return this.usuarioRepository.save(newUser);
    }
}