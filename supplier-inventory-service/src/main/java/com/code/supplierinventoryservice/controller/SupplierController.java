package com.code.supplierinventoryservice.controller;

import com.code.supplierinventoryservice.entity.Supplier;
import com.code.supplierinventoryservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("/list")
    public ResponseEntity <List<Supplier>> getAllSuppliers() {
        return ResponseEntity.ok().body(supplierService.getAllSuppliers());
    }

    @GetMapping("list/{id}")
    public ResponseEntity <Supplier> getSupplierById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(supplierService.getSupplierById(id));
    }

    @PostMapping("/add")
    public ResponseEntity < Supplier > addSupplier(@RequestBody Supplier supplier ) {
        return ResponseEntity.ok().body(this.supplierService.addSupplier(supplier));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity < Supplier > updateSupplier(@PathVariable String id, @RequestBody Supplier supplier) throws Exception {
        supplier.setId(id);
        return ResponseEntity.ok().body(this.supplierService.updateSupplier(supplier));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable String id) throws Exception {
        this.supplierService.deleteSupplier(id);
        return HttpStatus.OK;
    }



}
