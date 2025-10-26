package com.fiap.feedbacksystem.service;

import com.fiap.feedbacksystem.mapper.UsuarioMapper;
import com.fiap.feedbacksystem.model.dto.usuario.UsuarioRequestDTO;
import com.fiap.feedbacksystem.model.dto.usuario.UsuarioResponseDTO;
import com.fiap.feedbacksystem.model.entity.Usuario;
import com.fiap.feedbacksystem.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        Usuario entity = UsuarioMapper.toEntity(dto);
        Usuario saved = repository.save(entity);
        return UsuarioMapper.toResponseDTO(saved);
    }

/*    public List<UsuarioResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(UsuarioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO findById(Integer id) {
        Usuario entity = repository.findById(id).orElseThrow();
        return UsuarioMapper.toResponseDTO(entity);
    }*/
}
