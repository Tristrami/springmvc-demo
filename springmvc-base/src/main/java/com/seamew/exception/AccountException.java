package com.seamew.exception;

public class AccountException extends BusinessException
{
    public AccountException()
    {
    }

    public AccountException(String message)
    {
        super(message);
    }

    public AccountException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AccountException(Throwable cause)
    {
        super(cause);
    }

    public AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
