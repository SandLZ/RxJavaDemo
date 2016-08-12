package me.sandlz.rxjavademo.core.exception;

/**
 * Created by liuzhu on 16/8/12.
 * Description :
 * Usage :
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BaseException() {
        super();
    }

    public BaseException(String detailMessage) {
        super(detailMessage);
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
