package com.crymuzz.evotingapispring.entity.dto;

import lombok.Data;

/**
 * DTO para la respuesta de autentication -> Bearer
 * Incluye el token de acceso y los datos básicos del usuario autenticado.
 */
@Data
public class AuthResponseDTO {
    // Token de acceso JWT del usuario autenticado
    private String token;

    // Nombre del usuario autenticado
    private String firstName;

    // Apellido del usuario autenticado
    private String lastName;

    // Rol del usuario en el sistema
    private String role;
}
