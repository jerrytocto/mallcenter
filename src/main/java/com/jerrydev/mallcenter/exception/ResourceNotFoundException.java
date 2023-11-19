package com.jerrydev.mallcenter.exception;

import lombok.Builder;
import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException{

    private String nombreDelRecurso;
    private String nombreDelCampo;
    private int valorDelCampo;
    private String valorDelCampoString;

    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, int valorDelCampo) {
        super(String.format("%s No encontrado con: %s : '%s'", nombreDelRecurso, nombreDelCampo, valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }
    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, String valorDelCampoString) {
        super(String.format("%s No encontrado con: %s : '%s'", nombreDelRecurso, nombreDelCampo, valorDelCampoString));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampoString = valorDelCampoString;
    }
}
