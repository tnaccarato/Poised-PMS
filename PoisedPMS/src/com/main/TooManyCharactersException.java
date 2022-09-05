package com.main;

/**
 * Custom exception that stops users from inputting more characters than then database can handle.
 */
class TooManyCharactersException extends Exception {
    public TooManyCharactersException() {
        super();
    }
}