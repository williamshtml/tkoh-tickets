package com.tkoh.util;

public class Constants {

    //  Rutas de la API
    public static final String API_AUTH = "/api/auth/**";
    public static final String API_EVENTS = "/api/events/**";
    public static final String API_RESERVATIONS = "/api/reservations/**";
    public static final String API_USERS = "/api/users/**";

    // 🛡️ Seguridad
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final long TOKEN_EXPIRATION_TIME = 36_000_000; // 10 horas en milisegundos

    //  Estados
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    //  Mensajes de Error Comunes
    public static final String ERROR_USER_NOT_FOUND = "Usuario no encontrado";
    public static final String ERROR_EVENT_NOT_FOUND = "Evento no encontrado";
    public static final String ERROR_INSUFFICIENT_TICKETS = "No hay suficientes tickets disponibles";
    public static final String ERROR_UNAUTHORIZED = "No tienes permisos para realizar esta acción";

    // Constructor privado para evitar que alguien instancie esta clase
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}