package com.code.supplierinventoryservice.repository;

import com.code.supplierinventoryservice.entity.Drugs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugsRepository extends MongoRepository<Drugs,String> {
}
