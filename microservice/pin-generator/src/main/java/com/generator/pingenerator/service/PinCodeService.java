package com.generator.pingenerator.service;

import com.generator.pingenerator.exception.PhoneRegistryException;

public interface PinCodeService {

    public String getNewPinNumber (String phoneNumber) throws PhoneRegistryException;

    public String checkPinNumber (String phoneNumber, String pinNumber) throws PhoneRegistryException;
}