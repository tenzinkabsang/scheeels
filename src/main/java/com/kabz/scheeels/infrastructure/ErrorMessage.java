package com.kabz.scheeels.infrastructure;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, String message) {
}
