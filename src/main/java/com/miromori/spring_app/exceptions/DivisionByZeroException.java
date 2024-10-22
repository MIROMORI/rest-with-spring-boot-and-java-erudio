package com.miromori.spring_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DivisionByZeroException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8074709991188139553L;

    public DivisionByZeroException(String str){
        super(str);
    }
}
