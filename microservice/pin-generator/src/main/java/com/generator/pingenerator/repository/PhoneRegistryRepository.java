package com.generator.pingenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.generator.pingenerator.model.PhoneRegistry;

@Repository
public interface PhoneRegistryRepository extends MongoRepository<PhoneRegistry, String>{
    
}
