package com.generator.pingenerator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generator.pingenerator.exception.PhoneRegistryException;
import com.generator.pingenerator.model.PhoneRegistry;
import com.generator.pingenerator.repository.PhoneRegistryRepository;

@Service
public class PinCodeServiceImpl implements PinCodeService{

    private static final Logger logger = LoggerFactory.getLogger(PinCodeServiceImpl.class);

    @Autowired
    PhoneRegistryRepository repository;

    @Override
    public String getNewPinNumber (String phoneNumber) throws PhoneRegistryException{
        logger.info("Entering the service");
        PhoneRegistry newPhoneRegistry = new PhoneRegistry();
        logger.info("Creating phone registry");
        if(newPhoneRegistry.setPhoneNumber(phoneNumber) && !repository.existsById(phoneNumber)){
            logger.info("checked valid phone number");
            repository.save(newPhoneRegistry);
            logger.info("Saved in mongo");
            logger.info(phoneNumber);
            return newPhoneRegistry.getPinCode();
        }
        else{
            throw new PhoneRegistryException(PhoneRegistryException.PIN_ALREADY_CREATED);
        }
    } 

    @Override
    public String checkPinNumber (String phoneNumber, String pinNumber) throws PhoneRegistryException{
        logger.info("Entering the service");
        String result = "";
        PhoneRegistry incomingPhoneData = new PhoneRegistry();
        logger.info("Creating phone registry");
        if(incomingPhoneData.setPhoneNumber(phoneNumber) && incomingPhoneData.setPinCode(pinNumber) && 
        repository.existsById(phoneNumber)){
            logger.info("Phone and pin are correct, registry exists in the database");
            PhoneRegistry savedPhoneRegistry = repository.findById(phoneNumber).orElse(null);
            logger.info(savedPhoneRegistry.getPinCode().concat(", ".concat(incomingPhoneData.getPinCode())));
            if(savedPhoneRegistry.getAttemps() > 0 && 
            savedPhoneRegistry.getPinCode().equals(incomingPhoneData.getPinCode())){
                logger.info("Success authentication");
                savedPhoneRegistry.restartAttemps();
                repository.save(savedPhoneRegistry);
                result = "Correct authentication";
            }
            else if(savedPhoneRegistry.getAttemps() <= 0){
                repository.deleteById(savedPhoneRegistry.getPhoneNumber());
                throw new PhoneRegistryException(PhoneRegistryException.BLOCKED_ATTEMPS);
            }
            else if(!savedPhoneRegistry.getPinCode().equals(incomingPhoneData.getPinCode())){
                savedPhoneRegistry.reduceAttemps();
                repository.save(savedPhoneRegistry);
                throw new PhoneRegistryException(PhoneRegistryException.WRONG_PIN);
            }
        }
        
        return result;
    }
}