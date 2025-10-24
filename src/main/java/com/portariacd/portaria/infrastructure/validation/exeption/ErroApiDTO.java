package com.portariacd.portaria.infrastructure.validation.exeption;

import java.time.OffsetDateTime;

public record ErroApiDTO(OffsetDateTime timestamp, int status, String error, String message, String path) {


}
