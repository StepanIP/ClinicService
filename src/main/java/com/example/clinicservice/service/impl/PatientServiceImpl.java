package com.example.clinicservice.service.impl;

import com.example.clinicservice.model.Patient;
import com.example.clinicservice.repository.PatientRepository;
import com.example.clinicservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient readById(Long patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    @Override
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
