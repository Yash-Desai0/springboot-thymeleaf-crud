package com.codemechSolutions.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ActorMoviePortalException extends Exception {

    private final String localMessage;

    private final HttpStatus httpStatus;

    public ActorMoviePortalException(String message) {
        super(message);
        this.localMessage = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ActorMoviePortalException(String message, HttpStatus httpStatus) {
        super(message);
        this.localMessage = message;
        this.httpStatus = httpStatus;
    }


    public ActorMoviePortalException(String message, Throwable throwable) {
        super(message, throwable);
        this.localMessage = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ActorMoviePortalException(Throwable throwable) {
        super(throwable);
        this.localMessage = "";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getLocalMessage() {
        return localMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}