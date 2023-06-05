package com.AlexandreLoiola.FormularioEmpresa.service.exceptions;

import ch.qos.logback.classic.spi.IThrowableProxy;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ObjectNotFoundException(String msg) {
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
