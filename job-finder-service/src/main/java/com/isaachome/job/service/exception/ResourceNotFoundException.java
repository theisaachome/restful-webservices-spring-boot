package com.isaachome.job.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException{

    private String resource;

    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resource,String fieldName,long fieldValue){
        super(String.format("%s not Found with %s %d .",resource,fieldName,fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
