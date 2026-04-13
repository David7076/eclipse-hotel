package com.eclipsehotel.reservations.infra.exceptions;

public class EntityNotFoundExcetpion extends RuntimeException {
    public EntityNotFoundExcetpion(String message) {
        super(message);
    }
}
