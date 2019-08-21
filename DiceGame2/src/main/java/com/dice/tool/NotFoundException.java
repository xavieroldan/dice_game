package com.dice.tool;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "No existe el valor introducido")
public class NotFoundException extends Exception
{

    public NotFoundException(String msg)
    {
        super(msg);
    }

    public NotFoundException()
    {
        super();
    }
}
