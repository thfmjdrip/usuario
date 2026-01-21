package com.th.usuario.infrastrucutre.execeptions;

public class ConflictException extends RuntimeException  {
    public ConflictException(String mensagem){
        super(mensagem);
    }

    public  ConflictException(String messagem,Throwable throwable){
        super(messagem);
    }
}

