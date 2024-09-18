package com.examen.gestion_usuarios.aggregates.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    @Schema(description = "Codigo de Respuesta")
    private Integer code;

    @Schema(description = "Mensaje de Respuesta")
    private String message;

    @Schema(description = "Objeto Final de Respuesta")
    private Optional data;
}
