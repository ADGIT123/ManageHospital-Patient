package com.ehr.domain;

import java.util.List;

public class TreatmentHistory {
    public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public List<String> getTreatments() {
		return treatments;
	}
	public void setTreatments(List<String> treatments) {
		this.treatments = treatments;
	}
	private String patientId;
    private List<String> treatments; // example: could be a list of treatment IDs or details
    private String treatmentDetails;
	public String getTreatmentDetails() {
		return treatmentDetails;
	}
	public void setTreatmentDetails(String treatmentDetails) {
		this.treatmentDetails = treatmentDetails;
	}
}
