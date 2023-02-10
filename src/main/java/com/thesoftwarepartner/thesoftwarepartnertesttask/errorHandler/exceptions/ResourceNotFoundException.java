package com.thesoftwarepartner.thesoftwarepartnertesttask.errorHandler.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
