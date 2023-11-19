package com.jerrydev.mallcenter.exception;

import lombok.Data;

@Data
public class NumberFormatException extends RuntimeException{

    private String operacion;
    private String mensaje;

    public NumberFormatException(String operacion, String mensaje, Throwable causa) {
        super(String.format("Error durante la operación '%s': %s", operacion, mensaje), causa);
        this.operacion = operacion;
        this.mensaje = mensaje;
    }
}
