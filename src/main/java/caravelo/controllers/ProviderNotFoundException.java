package caravelo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProviderNotFoundException extends RuntimeException {

    public ProviderNotFoundException(String providerId) {
        super("could not find provider '" + providerId + "'.");
    }
}