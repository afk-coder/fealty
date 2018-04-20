package com.fux.auth.exception;

import org.apache.shiro.authc.AccountException;

/**
 * Created by fuxiaoj on 2018/04/17 15:15
 */
public class CaptchaException extends AccountException {

    public CaptchaException() {
    }

    public CaptchaException(String message) {
        super(message);
    }

    public CaptchaException(Throwable cause) {
        super(cause);
    }

    public CaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
