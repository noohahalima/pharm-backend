package com.code.supplierinventoryservice.service;

import com.code.supplierinventoryservice.entity.Supplier;
import com.code.supplierinventoryservice.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;



    public Supplier addSupplier(Supplier supplier ) {
        return supplierRepository.save(supplier);
    }


    public Supplier updateSupplier(Supplier supplier ) throws Exception {
        Optional< Supplier > op = this.supplierRepository.findById(supplier.getId());

        if (op.isPresent()) {
            Supplier supplier1 = op.get();

            supplierRepository.save(supplier);
            return supplier;
        } else {
            throw new Exception("Record not found with id : " + supplier.getId());
        }
    }


    public List< Supplier > getAllSuppliers() {
        return this.supplierRepository.findAll();
    }


    public Supplier getSupplierById(String id) throws Exception {

        Optional < Supplier > op = this.supplierRepository.findById(id);

        if (op.isPresent()) {
            return op.get();
        } else {
            throw new Exception("Record not found with id : " + id);
        }
    }


    public void deleteSupplier(String id) throws Exception {
        Optional < Supplier > op = this.supplierRepository.findById(id);

        if (op.isPresent()) {
            this.supplierRepository.delete(op.get());
        } else {
            throw new Exception("Record not found with id : " + id);
        }

    }
}

