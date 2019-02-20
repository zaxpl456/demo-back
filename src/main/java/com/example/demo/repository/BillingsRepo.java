package com.example.demo.repository;

import com.example.demo.model.Billings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingsRepo extends JpaRepository<Billings,Long> {


}
