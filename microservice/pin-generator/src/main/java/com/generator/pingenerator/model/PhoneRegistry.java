package com.generator.pingenerator.model;

import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "library.msisdn")
public class PhoneRegistry {
    
    @Id
    private String phoneNumber;
    private String pinCode;
    private int attemps;

    public PhoneRegistry(){
        Random random = new Random();
        this.attemps = 3;
        this.pinCode = String.format("%04d", random.nextInt(10000));;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public boolean setPhoneNumber(String phoneNumber) {
        if (checkValidPhoneNumber(phoneNumber)){
            this.phoneNumber = phoneNumber;
            return true;
        }
        return false;
    }

    public String getPinCode() {
        return pinCode;
    }

    public boolean setPinCode(String pinCode) {
        if (checkValidPinCode(pinCode)){
            this.pinCode = pinCode;
            return true;
        }
        return false;
    }

    public int getAttemps() {
        return attemps;
    }

    public void restartAttemps() {
        this.attemps = 3;
    }

    public int reduceAttemps(){
        this.attemps = this.attemps - 1;
        return this.attemps;
    }

    private boolean checkValidPhoneNumber(String phoneNumber){
        String pattern = "\\+\\d{11}";

        return phoneNumber.matches(pattern);
    }

    private boolean checkValidPinCode(String pinCode){
        String pattern = "\\d{4}";

        return pinCode.matches(pattern);
    }

}
