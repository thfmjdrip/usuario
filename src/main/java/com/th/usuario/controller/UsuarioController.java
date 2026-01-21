package com.th.usuario.controller;

import com.th.usuario.business.UsuarioService;
import com.th.usuario.business.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(service.salvarUsuario(usuarioDto));

    }
}
