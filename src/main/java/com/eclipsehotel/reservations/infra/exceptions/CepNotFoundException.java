package com.eclipsehotel.reservations.infra.exceptions;

public class CepNotFoundException extends RuntimeException {
    public CepNotFoundException(String message) {
        super(message);
    }
}
