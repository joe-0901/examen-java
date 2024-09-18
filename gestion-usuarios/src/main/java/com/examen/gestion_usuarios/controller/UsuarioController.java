package com.examen.gestion_usuarios.controller;

import com.examen.gestion_usuarios.aggregates.request.UsuarioRequest;
import com.examen.gestion_usuarios.aggregates.response.UsuarioResponse;
import com.examen.gestion_usuarios.entity.Usuario;
import com.examen.gestion_usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public ResponseEntity<UsuarioResponse> findAll() {
        UsuarioResponse response = usuarioService.listarUsuarios();

        if (response.getCode().equals(200)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    //Obtener Usuario por DNI
    @GetMapping("/{dni}")
    public ResponseEntity<UsuarioResponse> buscarPorDni(@PathVariable String dni) {
        UsuarioResponse response = usuarioService.buscarUsuarioDni(dni);

        if (response.getCode().equals(200)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> crearUsuario(
            @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse response = usuarioService.crearUsuario(usuarioRequest);
        if (response.getCode().equals(200)) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.actualizarUsuario(id,request);
        if (response.getCode().equals(200)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponse> eliminarUsuario(@PathVariable Long id) {
        UsuarioResponse response = usuarioService.eliminarUsuario(id);
        if (response.getCode().equals(200)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
