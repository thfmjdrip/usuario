package com.th.usuario.infrastrucutre.execeptions;

public class ResourceNotFoundExeception extends RuntimeException{
    public ResourceNotFoundExeception(String msg){
        super(msg);
    }

    public ResourceNotFoundExeception(String msg,Throwable throwable){
        super(msg,throwable);
    }
}
