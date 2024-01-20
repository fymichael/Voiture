package com.project.Voiture.util;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MyJSON implements Serializable{
    private String error = "";
    private Object data = null;

    public void setData(Object data) throws JsonProcessingException {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    


    
    

}
