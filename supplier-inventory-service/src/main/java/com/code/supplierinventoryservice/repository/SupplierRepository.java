package com.code.supplierinventoryservice.repository;

import com.code.supplierinventoryservice.entity.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier,String> {
}
