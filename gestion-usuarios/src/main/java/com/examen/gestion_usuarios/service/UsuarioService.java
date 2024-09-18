package com.examen.gestion_usuarios.service;

import com.examen.gestion_usuarios.aggregates.request.UsuarioRequest;
import com.examen.gestion_usuarios.aggregates.response.UsuarioResponse;


public interface UsuarioService {
    UsuarioResponse listarUsuarios();
    UsuarioResponse crearUsuario(UsuarioRequest request);
    UsuarioResponse actualizarUsuario(Long id,UsuarioRequest request);
    UsuarioResponse eliminarUsuario (Long id );
    UsuarioResponse buscarUsuarioDni(String dni);

}
