package com.web.meuequipo.core.season.exception;

import com.web.meuequipo.core.exception.BaseException;

public class SeasonException extends BaseException {
    public SeasonException(String message) {
        super(message);
    }

    public SeasonException(String message, Throwable cause) {
        super(message, cause);
    }
}
