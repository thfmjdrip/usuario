package com.th.usuario.business;

import com.th.usuario.business.converter.UsuarioConverter;
import com.th.usuario.business.dto.EnderecoDto;
import com.th.usuario.business.dto.TelefoneDto;
import com.th.usuario.business.dto.UsuarioDto;
import com.th.usuario.infrastrucutre.entity.Endereco;
import com.th.usuario.infrastrucutre.entity.Telefone;
import com.th.usuario.infrastrucutre.entity.Usuario;
import com.th.usuario.infrastrucutre.execeptions.ConflictException;
import com.th.usuario.infrastrucutre.execeptions.ResourceNotFoundExeception;
import com.th.usuario.infrastrucutre.repository.EnderecoRepository;
import com.th.usuario.infrastrucutre.repository.TelefoneRepository;
import com.th.usuario.infrastrucutre.repository.UsuarioRepository;
import com.th.usuario.infrastrucutre.security.JwtUtil;
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
    private final JwtUtil jwtUtil;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;

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

    public UsuarioDto buscarPorEmail(String email){
        try {
            return  converter.paraUsuarioDto((Usuario) repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundExeception("Usuario nao encontrado")));
        } catch (ResourceNotFoundExeception e) {
            throw new ResourceNotFoundExeception("Usuario nao encontrado"+e.getCause());
        }
    }

    public void deletarPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public UsuarioDto atualizaDadosUsuario(UsuarioDto usuarioDto,String token){
        //buscando no banco com token
        String email = jwtUtil.extractUsername(token.substring(7));
        Usuario entity = (Usuario) repository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundExeception("Email nao localizado"));

        usuarioDto.setSenha(usuarioDto.getSenha() != null ? passwordEncoder.encode(usuarioDto.getSenha()): null);

        //conferindo dados passados
        Usuario usuario = converter.updateUsuario(usuarioDto,entity);
        return converter.paraUsuarioDto(usuario);

    }

    public EnderecoDto atualizaEndereco(Long id, EnderecoDto enderecoDto){
        Endereco entity = enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeception("id nao encontrado:"+id));
        Endereco endereco = converter.updateEndereco(enderecoDto, entity);

        return converter.paraEnderecoDto(enderecoRepository.save(endereco));
    }

    public TelefoneDto atalizaTelefone(Long id,TelefoneDto telefoneDto){
        Telefone entity = telefoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExeception("id nao encontrado"+id));
        Telefone telefone = converter.updateTelefone(telefoneDto,entity);
        return converter.paraTelefoneDto(telefoneRepository.save(telefone));
    }

}
