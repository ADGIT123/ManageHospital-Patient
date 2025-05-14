package com.ehr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ehr.entity.TreatmentHistory;

public interface TreatmentHistoryRepository extends JpaRepository<TreatmentHistory, Long> {
    // Custom query methods if needed
}

