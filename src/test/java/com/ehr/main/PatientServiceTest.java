package com.ehr.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ehr.entity.Patient;
import com.ehr.repository.PatientRepository;
import com.ehr.repository.TreatmentHistoryRepository;
import com.ehr.service.PatientService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private TreatmentHistoryRepository treatmentHistoryRepository;

    @InjectMocks
    private PatientService patientService;

    private Patient testPatient;

    @BeforeEach
    public void setUp() {
        testPatient = new Patient("1", "John Doe", 30);
    }

    @Test
    public void testGetPatientById() {
        when(patientRepository.findById("1")).thenReturn(Optional.of(testPatient));

        Patient result = patientService.getPatientById("1");

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals(30, result.getAge());
    }

    @Test
    public void testGetAllPatients() {
        Patient patient2 = new Patient("2", "Jane Smith", 25);
        when(patientRepository.findAll()).thenReturn(Arrays.asList(testPatient, patient2));

        List<Patient> patients = patientService.getAllPatients();

        assertNotNull(patients);
        assertEquals(2, patients.size());
    }

    @Test
    public void testCreatePatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

        Patient createdPatient = patientService.createPatient(testPatient);

        assertNotNull(createdPatient);
        assertEquals("1", createdPatient.getId());
        assertEquals("John Doe", createdPatient.getName());
    }

    @Test
    public void testUpdatePatient() {
        when(patientRepository.save(any(Patient.class))).thenReturn(testPatient);

        Patient updatedPatient = patientService.updatePatient("1", testPatient);

        assertNotNull(updatedPatient);
        assertEquals("1", updatedPatient.getId());
        assertEquals("John Doe", updatedPatient.getName());
    }

    @Test
    public void testDeletePatient() {
        when(patientRepository.existsById("1")).thenReturn(true);

        boolean deleted = patientService.deletePatient("1");

        assertTrue(deleted);
        verify(patientRepository, times(1)).deleteById("1");
    }
}

