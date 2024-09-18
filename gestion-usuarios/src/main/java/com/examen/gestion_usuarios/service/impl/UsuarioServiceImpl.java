package com.examen.gestion_usuarios.service.impl;

import com.examen.gestion_usuarios.aggregates.request.UsuarioRequest;
import com.examen.gestion_usuarios.aggregates.response.UsuarioResponse;
import com.examen.gestion_usuarios.entity.Usuario;
import com.examen.gestion_usuarios.repository.UsuarioRepository;
import com.examen.gestion_usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse listarUsuarios() {
        Optional<List<Usuario>> usuarioList = Optional.of(usuarioRepository.findAll());
        if (Objects.nonNull(usuarioList)) {
            return new UsuarioResponse(200, "Usuarios encontrados",
                    usuarioList);
        } else {
            UsuarioResponse response = new UsuarioResponse(404,
                    "No se encontraron usuarios", Optional.empty());
            return response;
        }
    }

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        try {
            Usuario usuario = getEntity(request);
            if (Objects.nonNull(usuario)) {
                return new UsuarioResponse(200, "",
                        Optional.of(usuarioRepository.save(usuario)));
            }
        } catch (Exception e) {
            UsuarioResponse response = new UsuarioResponse(404
                    , e.getMessage(), Optional.empty());
            return response;
        }

        return null;
    }

    @Override
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

            if (usuarioOptional.isPresent()) {
                Usuario usuarioDB = usuarioOptional.get();
                Usuario usuario = getEntity(request);

                // Actualizar los campos
                usuarioDB.setNombres(usuario.getNombres());
                usuarioDB.setApellidoPaterno(usuario.getApellidoPaterno());
                usuarioDB.setApellidoMaterno(usuario.getApellidoMaterno());

                // Guardar y devolver el resultado
                usuarioRepository.save(usuarioDB);
                return new UsuarioResponse(200, "Usuario actualizado", Optional.of(usuarioDB));
            } else {
                // Si el usuario no existe
                return new UsuarioResponse(404, "Usuario no encontrado", Optional.empty());
            }
        } catch (Exception e) {
            // Manejo de errores
            return new UsuarioResponse(500, "Error al actualizar usuario: " + e.getMessage(), Optional.empty());
        }
    }

    @Override
    public UsuarioResponse eliminarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            usuario.setEstado(0);

            return new UsuarioResponse(200, "Usuario eliminado",
                    Optional.of(usuarioRepository.save(usuario)));
        } else {
            return new UsuarioResponse(404
                    , "Usuario no encontrado", Optional.empty());
        }
    }

    @Override
    public UsuarioResponse buscarUsuarioDni(String dni) {
        Optional<Usuario> usuario = usuarioRepository.findByNumeroDocumento(dni);
        if (usuario.isPresent()) {
            return new UsuarioResponse(200, "Usuario encontrado",
                    usuario);
        } else {
            return new UsuarioResponse(404,
                    "No se encontro al usuario", Optional.empty());
        }
    }

    private Usuario getEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setId(request.getId());
        usuario.setNombres(request.getNombres());
        usuario.setApellidoPaterno(request.getApellidoPaterno());
        usuario.setApellidoMaterno(request.getApellidoMaterno());

        return usuario;
    }
}


