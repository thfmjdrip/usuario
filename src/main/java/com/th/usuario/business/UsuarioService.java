package com.th.usuario.business;

import com.th.usuario.business.converter.UsuarioConverter;
import com.th.usuario.business.dto.UsuarioDto;
import com.th.usuario.infrastrucutre.entity.Usuario;
import com.th.usuario.infrastrucutre.execeptions.ConflictException;
import com.th.usuario.infrastrucutre.execeptions.ResourceNotFoundExeception;
import com.th.usuario.infrastrucutre.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto){
        emailExiste(usuarioDto.getEmail());
        usuarioDto.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        Usuario usuario = converter.paraUsuarioDto(usuarioDto);
        usuario = repository.save(usuario);
        return converter.paraUsuarioDto(usuario);

    }

    public boolean verificaEmailExistente(String  email){
        return repository.existsByEmail(email);
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe){
                throw new ConflictException("usuario ja cadastrado: "+email);
            }
        }catch (ConflictException e){
            throw new ConflictException("usuario ja cadastrado: "+e.getCause());
        }
    }

    public Usuario buscarPorEmail(String email){
        try {
            return (Usuario) repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundExeception("Usuario nao encontrado"));
        } catch (ResourceNotFoundExeception e) {
            throw new ResourceNotFoundExeception("Usuario nao encontrado"+e.getCause());
        }
    }

    public void deletarPorEmail(String email){
        repository.deleteByEmail(email);
    }



}
