package br.com.fiap.core.exceptions;

public class BusinessException extends  RuntimeException {
    public BusinessException(String msg)  {
        super(msg);
    }
}