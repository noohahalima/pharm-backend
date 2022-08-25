package com.code.supplierinventoryservice.controller;

import com.code.supplierinventoryservice.entity.Drugs;
import com.code.supplierinventoryservice.entity.Supplier;
import com.code.supplierinventoryservice.repository.DrugsRepository;
import com.code.supplierinventoryservice.service.DrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity <Drugs> getDrugById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(drugsService.getDrugById(id));
    }

    @PostMapping("/add")
    public ResponseEntity < Drugs > addDrug(@RequestBody Drugs drugs ) {
        return ResponseEntity.ok().body(drugsService.addDrug(drugs));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity < Drugs > updateDrug(@PathVariable String id, @RequestBody Drugs drugs) throws Exception {
        drugs.setId(id);
        return ResponseEntity.ok().body(drugsService.updateDrug(drugs));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct(@PathVariable String id) throws Exception {
        drugsService.deleteDrug(id);
        return HttpStatus.OK;
    }
}
