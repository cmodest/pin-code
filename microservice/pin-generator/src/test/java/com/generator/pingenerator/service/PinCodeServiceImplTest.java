package com.generator.pingenerator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.generator.pingenerator.model.PhoneRegistry;
import com.generator.pingenerator.repository.PhoneRegistryRepository;
import com.mongodb.assertions.Assertions;

@ExtendWith(MockitoExtension.class)
public class PinCodeServiceImplTest {

    @Mock
    private static PhoneRegistryRepository registryRepository;

    @InjectMocks
    private static PinCodeService service;

    @BeforeAll
    static void setUp(){
        service = new PinCodeServiceImpl();
    }

    @Test
    void testCheckPinNumber() {
        try{
            PhoneRegistry testPhone = new PhoneRegistry();
            testPhone.setPhoneNumber("+34123456789");
            testPhone.setPinCode("1234");
            when(registryRepository.findById(testPhone.getPhoneNumber())).thenReturn(Optional.of(testPhone));
            when(registryRepository.existsById(testPhone.getPhoneNumber())).thenReturn(true);
            String result = service.checkPinNumber("+34123456789", "1234");
            assertEquals("Correct authentication", result);
            verify(registryRepository, times(1)).save(testPhone);
            verify(registryRepository, times(1)).existsById(testPhone.getPhoneNumber());
            verify(registryRepository, times(1)).findById(testPhone.getPhoneNumber());
        }
        catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    void testGetNewPinNumber() {
        try{
            when(registryRepository.existsById("+34123456789")).thenReturn(false);
            assertNotNull(service.getNewPinNumber("+34123456789"));
            verify(registryRepository, times(1)).existsById("+34123456789");
            verify(registryRepository, times(1)).save(any());
        }
        catch (Exception e){
            Assertions.fail();
        }
    }
}
