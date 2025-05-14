package com.ehr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ehr.entity.Patient;



@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
}
