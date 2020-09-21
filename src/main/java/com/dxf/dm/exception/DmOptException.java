package com.dxf.dm.exception;

import org.omg.SendingContext.RunTime;

/**
 * @author GuJun
 * @date 2020/9/21
 */
public class DmOptException extends RuntimeException {

    public DmOptException() {
        super();
    }

    public DmOptException(String msg) {
        super(msg);
    }

}
