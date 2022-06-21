package com.generator.pingenerator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generator.pingenerator.exception.PhoneRegistryException;
import com.generator.pingenerator.model.PhoneRegistry;
import com.generator.pingenerator.service.PinCodeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("api/pinCode")
public class PinCodeController {

    private static final Logger logger = LoggerFactory.getLogger(PinCodeController.class);	

    @Autowired
    PinCodeService service;

    @GetMapping("/getPin")
    public ResponseEntity<Object> getPin(@RequestParam(name = "phoneNumber") String phoneNumber){
        try{
            logger.info("Received petition for a new pin, ".concat(phoneNumber));
            String result = service.getNewPinNumber(phoneNumber);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (PhoneRegistryException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.RESET_CONTENT);
        }
    }

    @PostMapping(path = "/checkPin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkPin(@RequestBody PhoneRegistry phoneRegistry){
        try{
            logger.info("checking valid pin");
            String result = service.checkPinNumber(phoneRegistry.getPhoneNumber(), phoneRegistry.getPinCode());

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (PhoneRegistryException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
    }
}