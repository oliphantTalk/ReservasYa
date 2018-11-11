package com.ttps.reservasya.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "El usuario no se encontro en el sistema")

public class UserNotFoundException extends RuntimeException {
}
