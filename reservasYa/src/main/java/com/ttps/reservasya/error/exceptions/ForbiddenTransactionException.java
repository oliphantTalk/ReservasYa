package com.ttps.reservasya.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Operacion Prohibida")
public class ForbiddenTransactionException extends RuntimeException {
}
