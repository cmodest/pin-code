package com.generator.pingenerator.exception;

public class PhoneRegistryException extends Exception{

    public static final String PIN_ALREADY_CREATED = "Pin Number already created!";
    public static final String WRONG_PIN = "Wrong Pin Number!";
    public static final String BLOCKED_ATTEMPS = "Exceeded number of attemps";

    public PhoneRegistryException(String message) {
        super(message);
    }

    public PhoneRegistryException(String message, Throwable cause){
        super(message, cause);
    }
}
