package com.ehr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ehr.entity.TreatmentHistory;
import com.ehr.repository.TreatmentHistoryRepository;

import java.util.List;
import java.util.Optional;


@Service
public class TreatmentHistoryService {

    @Autowired
    private TreatmentHistoryRepository historyRepository;

    public TreatmentHistory createTreatmentHistory(TreatmentHistory history) {
        return historyRepository.save(history);
    }

    @Cacheable(value = "treatmentHistory", key = "#id")
    public TreatmentHistory getTreatmentHistoryById(Long id) {
        return historyRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "treatmentHistory", key = "#id")
    public String getPatientIdByTreatmentHistoryId(Long id) {
        TreatmentHistory history = historyRepository.findById(id).orElse(null);
        return (history != null) ? history.getPatientId() : null;
    }

    public List<TreatmentHistory> getAllTreatmentHistories() {
        return historyRepository.findAll();
    }

    @CacheEvict(value = "treatmentHistory", key = "#id")
    public void deleteTreatmentHistory(Long id) {
        historyRepository.deleteById(id);
    }
}



