package com.ehr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.ehr.entity.Patient;
import com.ehr.entity.TreatmentHistory;
import com.ehr.repository.PatientRepository;
import com.ehr.repository.TreatmentHistoryRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TreatmentHistoryRepository treatmentHistoryRepository;

    @Transactional(readOnly = true)
    public Patient getPatientById(String id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(String id, Patient patient) {
        patient.setId(id); // ensure ID is set for update
        return patientRepository.save(patient);
    }

    @Transactional
    public boolean deletePatient(String id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public CompletableFuture<TreatmentHistory> addTreatmentHistory(String patientId, TreatmentHistory history) {
        // Simulate a long-running task with CompletableFuture
        return CompletableFuture.supplyAsync(() -> {
            Patient patient = patientRepository.findById(patientId).orElse(null);
            if (patient == null) {
                throw new IllegalArgumentException("Patient not found with ID: " + patientId);
            }
            history.setPatientId(patientId);
            return treatmentHistoryRepository.save(history);
        });
    }
}
