package com.th.usuario.business;

import com.th.usuario.business.converter.UsuarioConverter;
import com.th.usuario.business.dto.UsuarioDto;
import com.th.usuario.infrastrucutre.entity.Usuario;
import com.th.usuario.infrastrucutre.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioConverter converter;

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = converter.paraUsuarioDto(usuarioDto);
        usuario = repository.save(usuario);
        return converter.paraUsuarioDto(usuario);

    }
}
