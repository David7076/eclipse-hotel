package com.eclipsehotel.reservations.infra.exceptions;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException(String email) {
        super("O Email " + email + " já está cadastrado.");
    }
}
