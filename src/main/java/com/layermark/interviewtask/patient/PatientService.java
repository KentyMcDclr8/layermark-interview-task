package com.layermark.interviewtask.patient;

import com.layermark.interviewtask.doctor.Doctor;
import com.layermark.interviewtask.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class defines a service layer for the API, dealing with the required business logic and the inputs from the controller layer
 */

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public void addNewPatient(Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findPatientByEmail(patient.getEmail());
        if (patientOptional.isPresent()) {
            throw new IllegalStateException("A patient with that email already exists.");
        }
        patientRepository.save(patient);
    }

    public void deletePatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new IllegalStateException("A patient with that ID does not exist.");
        }
        patientRepository.deleteById(patientId);
    }

    public void assignDoctor(Long patientId, Long doctorId) {
        if (!patientRepository.existsById(patientId)) {
            throw new IllegalStateException("A patient with that ID does not exist.");
        }
        if (!doctorRepository.existsById(doctorId)) {
            throw new IllegalStateException("A doctor with that ID does not exist.");
        }
        Patient patient = patientRepository.findById(patientId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();
        patient.assignDoctor(doctor);
        doctor.assignPatient(patient);
        patientRepository.save(patient);
    }

    @Transactional
    public void updatePatient(Long patientId, String name, String email) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalStateException("A patient with that ID does not exist."));
        if (name != null && name.length() != 0 && !Objects.equals(name, patient.getName())) {
            patient.setName(name);
        }
        if (email != null && email.length() != 0 && !Objects.equals(email, patient.getEmail())) {
            Optional<Patient> patientOptional = patientRepository.findPatientByEmail(email);
            if (patientOptional.isPresent()) {
                throw new IllegalStateException("A patient with that email already exists");
            }
            patient.setEmail(email);
        }
    }
}
