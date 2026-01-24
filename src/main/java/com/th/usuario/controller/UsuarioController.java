package com.th.usuario.controller;

import com.th.usuario.business.UsuarioService;
import com.th.usuario.business.dto.EnderecoDto;
import com.th.usuario.business.dto.TelefoneDto;
import com.th.usuario.business.dto.UsuarioDto;
import com.th.usuario.infrastrucutre.entity.Usuario;
import com.th.usuario.infrastrucutre.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(service.salvarUsuario(usuarioDto));

    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDto usuarioDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDto.getEmail(),usuarioDto.getSenha())
        );
        return jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<UsuarioDto> buscarUsuarioPorEmail(@RequestParam("email")String email){
        return ResponseEntity.ok(service.buscarPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        service.deletarPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDto> atualizarDadosUsuario(@RequestBody UsuarioDto dto,@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(service.atualizaDadosUsuario(dto,token));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDto> atualizaEndereco(@RequestBody EnderecoDto dto,@RequestParam ("id")Long id){
        return ResponseEntity.ok(service.atualizaEndereco(id,dto));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDto> atualizaTelefone(@RequestBody TelefoneDto dto,@RequestParam("id")Long id){
        return ResponseEntity.ok(service.atalizaTelefone(id,dto));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDto> atualizarDadosUsuarioEndereco(@RequestBody EnderecoDto dto,@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(service.cadastrarEndereco(token,dto));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDto> atualizarDadosUsuarioTelefone(@RequestBody TelefoneDto dto,@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(service.cadastroTelefone(token,dto));
    }

}
