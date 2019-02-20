package com.example.demo.controller;


import com.example.demo.model.Billings;
import com.example.demo.model.Spools;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.SpoolsResponse;
import com.example.demo.repository.BillingsRepo;
import com.example.demo.repository.SpoolsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/spools")
public class SpoolsController {


    @Autowired
    SpoolsRepo spoolsRepo;

    @Autowired
    BillingsRepo billingsRepo;


    @GetMapping("/get")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Spools> getAllSpools()
    {
        return spoolsRepo.findAll();
    }


    @GetMapping("/getOne/{id}")
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    public Spools getOne(@PathVariable(value = "id") long id){return spoolsRepo.getOne(id); }

    @PostMapping("/delete")
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteSpool(@Valid @RequestBody SpoolsResponse spools){

        spoolsRepo.deleteById(spools.getId());

        return ResponseEntity.ok(new ApiResponse(true, "Spoon deleted"));

    }

    @PostMapping("/saveSpool")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> saveSpool(@Valid @RequestBody SpoolsResponse spools){
        Billings billings= billingsRepo.getOne(spools.getBillings());
        if(spools.getId()!=0){
           Spools spools1= spoolsRepo.getOne(spools.getId());
           spools1.setName(spools.getName());
           spools1.setBillings(billings);
           spoolsRepo.save(spools1);


        }
        else{
            Spools spools1=new Spools();
            spools1.setName(spools.getName());
            spools1.setBillings(billings);
            spoolsRepo.save(spools1);

        }

        return ResponseEntity.ok(new ApiResponse(true, "Spoon saved"));

    }
}


