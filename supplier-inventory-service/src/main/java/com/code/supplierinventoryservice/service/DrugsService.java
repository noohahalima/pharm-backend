package com.code.supplierinventoryservice.service;

import com.code.supplierinventoryservice.entity.Drugs;
import com.code.supplierinventoryservice.repository.DrugsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DrugsService {
    @Autowired
    DrugsRepository drugsRepository;

    public Drugs addDrug(Drugs drug)
    {
        return drugsRepository.save(drug);
    }

    public Drugs updateDrug(Drugs drug) throws Exception {
        Optional<Drugs> op=drugsRepository.findById(drug.getId());
        if(op.isPresent())
        {
            Drugs drug1=op.get();
            drugsRepository.save(drug);
            return drug;
        }
        else {
            throw new Exception("record not found with id"+drug.getId());
        }
    }

    public List<Drugs> getAllDrugs(){
        return drugsRepository.findAll();
    }

    public Drugs getDrugById(String id) throws Exception {
        Optional<Drugs> op= drugsRepository.findById(id);
        if(op.isPresent()) {
            return op.get();
        }
        else
        {
            throw new Exception("drug not found with id"+id);
        }

    }

    public void deleteDrug(String id) throws Exception {
        Optional<Drugs> op=drugsRepository.findById(id);
        if(op.isPresent()){
            drugsRepository.delete(op.get());
        }
        else {
            throw new Exception("drug not found with id"+id);
        }
    }
}
