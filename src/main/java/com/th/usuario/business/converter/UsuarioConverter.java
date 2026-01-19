package com.th.usuario.business.converter;

import com.th.usuario.business.dto.EnderecoDto;
import com.th.usuario.business.dto.TelefoneDto;
import com.th.usuario.business.dto.UsuarioDto;
import com.th.usuario.infrastrucutre.entity.Endereco;
import com.th.usuario.infrastrucutre.entity.Telefone;
import com.th.usuario.infrastrucutre.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuarioDto(UsuarioDto usuarioDto){
        return Usuario.builder().nome(usuarioDto.getNome())
                .senha(usuarioDto.getSenha())
                .email(usuarioDto.getEmail())
                .enderecos(paraListaEndereco(usuarioDto.getEnderecoDtos()))
                .telefones(paraListaTelefone(usuarioDto.getTelefoneDtos()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDto> enderecoDtos){
        return  enderecoDtos.stream().map(this::paraEndereco).toList();
    }

    public Endereco paraEndereco(EnderecoDto enderecoDto){
        return Endereco.builder()
                .cep(enderecoDto.getCep())
                .rua(enderecoDto.getRua())
                .numero(enderecoDto.getNumero())
                .complemento(enderecoDto.getComplemento())
                .estado(enderecoDto.getEstado())
                .cidade(enderecoDto.getCidade())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDto> telefoneDtos){
        return telefoneDtos.stream().map(this::paraLista).toList();
    }

    public Telefone paraLista(TelefoneDto telefoneDto){
        return Telefone.builder()
                .ddd(telefoneDto.getDdd())
                .numero(telefoneDto.getNumero())
                .build();
    }

    public UsuarioDto paraUsuarioDto(Usuario usuario){
        return UsuarioDto.builder().nome(usuario.getNome())
                .senha(usuario.getSenha())
                .email(usuario.getEmail())
                .enderecoDtos(paraListaEnderecoDto(usuario.getEnderecos()))
                .telefoneDtos(paraListaTelefoneDto(usuario.getTelefones()))
                .build();
    }

    public List<EnderecoDto> paraListaEnderecoDto(List<Endereco> enderecos){
        return enderecos.stream().map(this::paraEnderecoDto).toList();
    }

    public EnderecoDto paraEnderecoDto(Endereco endereco){
        return EnderecoDto.builder()
                .numero(endereco.getNumero())
                .rua(endereco.getRua())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .build();
    }

    public List<TelefoneDto> paraListaTelefoneDto(List<Telefone> telefones){
        return telefones.stream().map(this::paraTelefoneDto).toList();
    }

    public TelefoneDto paraTelefoneDto(Telefone telefone){
        return TelefoneDto.builder().
                ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }
}
