package com.code.supplierinventoryservice.service;

import com.code.supplierinventoryservice.entity.Drugs;
import com.code.supplierinventoryservice.repository.DrugsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return drugsRepository.insert(drug);
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
            throw new Exception();
        }
    }

    public List<Drugs> getDrugsBySupplier(String supplier) throws Exception {
            List<Drugs> drug = drugsRepository.findBySupplier(supplier);
            if(drug.isEmpty()) {
                throw new Exception();
            }
            else
            {
                return drug;
            }
    }
    public List<Drugs> getDrugsByDrugName(String drugName) throws Exception {
        List<Drugs> drug = drugsRepository.findByDrugName(drugName);
        if(drug.isEmpty()) {
            throw new Exception();
        }
        else
        {
            return drug;
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
            throw new Exception();
        }
    }

    public void deleteDrug(String id)  {
        Optional<Drugs> op=drugsRepository.findById(id);
        if(op.isPresent()){
            drugsRepository.delete(op.get());
            }
        }


    public void disableDrug(String id)  {
        Optional<Drugs> op=drugsRepository.findById(id);
        if(op.isPresent()){
            // drugsRepository.delete(op.get());
            Drugs drug=op.get();
            if(drug.isDeleted()==false) {
                drug.setDeleted(true);
                drugsRepository.save(drug);
            }

        }

    }
}
