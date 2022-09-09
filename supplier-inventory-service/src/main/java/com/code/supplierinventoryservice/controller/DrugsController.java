package com.code.supplierinventoryservice.controller;

import com.code.supplierinventoryservice.entity.Drugs;

import com.code.supplierinventoryservice.service.DrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/drugs")
public class DrugsController {

    @Autowired
    DrugsService drugsService;

    @GetMapping("/list")
    public ResponseEntity <List<Drugs>> getAllDrugs() {
        return ResponseEntity.ok().body(drugsService.getAllDrugs());
    }

    @GetMapping("list/{id}")
    public ResponseEntity <Object> getDrugById(@PathVariable String id)  {
        try {
            return ResponseEntity.ok().body(drugsService.getDrugById(id));
        }
        catch(Exception e)
        {
            return new ResponseEntity<Object>("Drug not found with id"+id,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("listby/{supplier}")
    public ResponseEntity <Object> getDrugBySupplierName(@PathVariable String supplier)  {
        try {
            return ResponseEntity.ok().body(drugsService.getDrugsBySupplier(supplier));
        }
        catch(Exception e)
        {
            return new ResponseEntity<Object>("Drug not found with supplier name "+supplier,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listbydrugname/{drugName}")
    public ResponseEntity <Object> getDrugByDrugName(@PathVariable String drugName)  {
        try {
            return ResponseEntity.ok().body(drugsService.getDrugsByDrugName(drugName));
        }
        catch(Exception e)
        {
            return new ResponseEntity<Object>("Drug not found with drug name "+drugName,HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/add")
    public ResponseEntity < Object > addDrug(@RequestBody Drugs drugs ) {
        try {
            return ResponseEntity.ok().body(drugsService.addDrug(drugs));
        }
        catch(Exception e) {
            return new ResponseEntity<Object>("Cannot add Drug", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <Object> updateDrug(@PathVariable String id, @RequestBody Drugs drugs) {
        try {
            drugs.setId(id);
            return ResponseEntity.ok().body(drugsService.updateDrug(drugs));
        }
        catch(Exception e)
        {
            return new ResponseEntity<Object>("Drug Not found with id "+id,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDrug(@PathVariable("id") String id)   {
        try {
            drugsService.deleteDrug(id);
            return new ResponseEntity<String>("Deleted drug with id "+id,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<String>("drug not found with id "+id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
