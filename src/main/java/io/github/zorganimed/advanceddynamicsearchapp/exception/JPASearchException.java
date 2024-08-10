package io.github.zorganimed.advanceddynamicsearchapp.exception;

import lombok.Getter;

@Getter
public class JPASearchException extends RuntimeException{

    public JPASearchException(String reason) {
        super(reason);
    }

    public JPASearchException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
