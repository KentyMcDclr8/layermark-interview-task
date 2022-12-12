package com.layermark.interviewtask.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class defines a service layer for the API, dealing with the required business logic and the inputs from the controller layer
 */

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public void addNewDoctor(Doctor doctor) {
        Optional<Doctor> doctorOptional = doctorRepository.findDoctorByEmail(doctor.getEmail());
        if (doctorOptional.isPresent()) {
            throw new IllegalStateException("A doctor with that email already exists.");
        }
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long doctorId) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new IllegalStateException("A doctor with that ID does not exist.");
        }
        doctorRepository.deleteById(doctorId);
    }

    public void updatePatient(Long doctorId, String name, String email) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new IllegalStateException("A doctor with that ID does not exist."));
        if (name != null && name.length() != 0 && !Objects.equals(name, doctor.getName())) {
            doctor.setName(name);
        }
        if (email != null && email.length() != 0 && !Objects.equals(email, doctor.getEmail())) {
            Optional<Doctor> doctorOptional = doctorRepository.findDoctorByEmail(email);
            if (doctorOptional.isPresent()) {
                throw new IllegalStateException("A patient with that email already exists");
            }
            doctor.setEmail(email);
        }
    }
}
