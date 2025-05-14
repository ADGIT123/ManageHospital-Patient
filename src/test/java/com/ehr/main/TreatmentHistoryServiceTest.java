package com.ehr.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ehr.entity.TreatmentHistory;
import com.ehr.repository.TreatmentHistoryRepository;
import com.ehr.service.TreatmentHistoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TreatmentHistoryServiceTest {

    @Mock
    private TreatmentHistoryRepository historyRepository;

    @InjectMocks
    private TreatmentHistoryService historyService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTreatmentHistory() {
        TreatmentHistory historyToCreate = new TreatmentHistory(1L, "Treatment details");
        TreatmentHistory createdHistory = new TreatmentHistory(1L, "Treatment details");
        createdHistory.setId(1L);

        when(historyRepository.save(any(TreatmentHistory.class))).thenReturn(createdHistory);

        TreatmentHistory result = historyService.createTreatmentHistory(historyToCreate);

        assertEquals(createdHistory.getId(), result.getId());
        assertEquals(createdHistory.getPatientId(), result.getPatientId());
        assertEquals(createdHistory.getTreatmentDetails(), result.getTreatmentDetails());

        verify(historyRepository, times(1)).save(any(TreatmentHistory.class));
    }

    @Test
    public void testGetTreatmentHistoryById() {
        Long historyId = 1L;
        TreatmentHistory history = new TreatmentHistory(1L, "Treatment details");
        history.setId(historyId);

        when(historyRepository.findById(historyId)).thenReturn(Optional.of(history));

        Optional<TreatmentHistory> result = Optional.of(historyService.getTreatmentHistoryById(historyId));

        assertEquals(history.getId(), result.get().getId());
        assertEquals(history.getPatientId(), result.get().getPatientId());
        assertEquals(history.getTreatmentDetails(), result.get().getTreatmentDetails());

        verify(historyRepository, times(1)).findById(historyId);
    }

    @Test
    public void testGetAllTreatmentHistories() {
        TreatmentHistory history1 = new TreatmentHistory(1L, "Treatment details 1");
        TreatmentHistory history2 = new TreatmentHistory(2L, "Treatment details 2");
        List<TreatmentHistory> histories = Arrays.asList(history1, history2);

        when(historyRepository.findAll()).thenReturn(histories);

        List<TreatmentHistory> result = historyService.getAllTreatmentHistories();

        assertEquals(histories.size(), result.size());
        assertEquals(histories.get(0).getId(), result.get(0).getId());
        assertEquals(histories.get(1).getId(), result.get(1).getId());

        verify(historyRepository, times(1)).findAll();
    }

  

    @Test
    public void testDeleteTreatmentHistory() {
        Long historyId = 1L;

        when(historyRepository.existsById(historyId)).thenReturn(true);
        doNothing().when(historyRepository).deleteById(historyId);

        historyService.deleteTreatmentHistory(historyId);

     

        verify(historyRepository, times(1)).deleteById(historyId);
    }
}

