package com.example.product_service.util.exception;
import org.springframework.http.HttpStatus;


public class DatabaseException extends BaseException {
    public DatabaseException(String message) {
        super(buildMessage(),message, HttpStatus.INTERNAL_SERVER_ERROR, "DB_ERROR");
    }
    public DatabaseException(String message, Throwable cause){
        super(buildMessage(),message,cause,HttpStatus.INTERNAL_SERVER_ERROR, "DB_ERROR");
    }
    private static String buildMessage() {
        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(stream ->
                        stream
                                .filter(frame -> !frame.getClassName().equals(DatabaseException.class.getName()))
                                .findFirst()
                                .map(frame -> "METHOD::" + frame.getMethodName() )
                                .orElse("METHOD::UNKNOWN")
                );
    }
}



