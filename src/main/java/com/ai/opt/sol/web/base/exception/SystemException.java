package com.ai.opt.sol.web.base.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SystemException extends GenericException {

    private static Logger LOG = LogManager.getLogger(SystemException.class);

    private static final long serialVersionUID = 1L;

    public SystemException(String message) {
        super(message); 
        this.errorMessage = message;
        this.writeSysException();
    }

    public SystemException(Exception oriEx) {
        super(oriEx);
        this.errOri = oriEx; 
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(os);
        this.errOri.printStackTrace(p);
        this.writeSysException();
    }

    public SystemException(String message, Exception oriEx) {
        super(message, oriEx); 
        this.errorMessage = message;
        this.errOri = oriEx;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(os);
        this.errOri.printStackTrace(p);
        this.writeSysException();
    }

    private void writeSysException() { 
        LOG.error("系统异常信息:" + this.errorMessage);
    }

}
