package com.ai.opt.sol.web.base.exception;

import java.io.Serializable;

public class GenericException extends RuntimeException implements Serializable {
    
    private static final long serialVersionUID = 1L;

    String errorCode;
    
    String errorMessage;
    
    Exception errOri;
    
    public GenericException(){
        
    }
    
    public GenericException(String message) {
        super(message);
    }
    
    public GenericException(Exception oriEx) {
        super(oriEx);
    }
    
    public GenericException(String message, Exception oriEx) {
        super(message, oriEx);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Exception getErrOri() {
        return errOri;
    }

    public void setErrOri(Exception errOri) {
        this.errOri = errOri;
    } 

    
}
