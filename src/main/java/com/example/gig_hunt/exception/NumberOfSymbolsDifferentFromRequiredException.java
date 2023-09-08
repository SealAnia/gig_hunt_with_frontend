package com.example.gig_hunt.exception;

public class NumberOfSymbolsDifferentFromRequiredException extends Exception {

    public static final String MESSAGE = "The required number of chars is: ";

    public NumberOfSymbolsDifferentFromRequiredException(int i) {
        super(MESSAGE + i);
    }

}
