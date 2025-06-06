package com.github.hanselmito.toymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String exceptionDetail;
    private Object fieldValue;

    /**
     * Constructor para ResourceNotFoundException.
     *
     * @param exceptionDetail Detalle de la excepción
     * @param fieldValue Valor del campo que causó la excepción
     */
    public ResourceNotFoundException(String exceptionDetail, Object fieldValue) {
        super(exceptionDetail + fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }
}