package com.layermark.interviewtask.vaccine;

import com.layermark.interviewtask.doctor.Doctor;
import com.layermark.interviewtask.doctor.DoctorRepository;
import com.layermark.interviewtask.patient.Patient;
import com.layermark.interviewtask.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VaccineService {

    private final VaccineRepository vaccineRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public List<Vaccine> getVaccines() { return vaccineRepository.findAll(); }

    public void addNewVaccine(Vaccine vaccine) {
        Optional<Vaccine> vaccineOptional = vaccineRepository.findVaccineByName(vaccine.getName());
        if (vaccineOptional.isPresent()) {
            throw new IllegalStateException("A vaccine with that name already exists.");
        }
        vaccineRepository.save(vaccine);
    }

    public void deleteVaccine(Long vaccineId) {
        if (!vaccineRepository.existsById(vaccineId)) {
            throw new IllegalStateException("A vaccine with that ID does not exist.");
        }
        vaccineRepository.deleteById(vaccineId);
    }

    public void assignPatient(Long vaccineId, Long patientId) {
        if (!vaccineRepository.existsById(vaccineId)) {
            throw new IllegalStateException("A vaccine with that ID does not exist.");
        }
        if (!patientRepository.existsById(patientId)) {
            throw new IllegalStateException("A patient with that ID does not exist.");
        }
        Vaccine vaccine = vaccineRepository.findById(vaccineId).get();
        Patient patient = patientRepository.findById(patientId).get();
        vaccine.vaccinatePatient(patient);
        patient.assignVaccine(vaccine);
        vaccineRepository.save(vaccine);
    }

    public void assignDoctor(Long vaccineId, Long doctorId) {
        if (!vaccineRepository.existsById(vaccineId)) {
            throw new IllegalStateException("A vaccine with that ID does not exist.");
        }
        if (!doctorRepository.existsById(doctorId)) {
            throw new IllegalStateException("A doctor with that ID does not exist.");
        }
        Vaccine vaccine = vaccineRepository.findById(vaccineId).get();
        Doctor doctor = doctorRepository.findById(doctorId).get();
        vaccine.assignDoctor(doctor);
        doctor.assignVaccine(vaccine);
        vaccineRepository.save(vaccine);
    }


    @Transactional
    public void updateVaccine(Long vaccineId, String name) {
        Vaccine vaccine = vaccineRepository.findById(vaccineId).orElseThrow(() -> new IllegalStateException("A vaccine with that ID does not exist."));
        if (name != null && name.length() != 0 && !Objects.equals(name, vaccine.getName())) {
            Optional<Vaccine> vaccineOptional = vaccineRepository.findVaccineByName(name);
            if (vaccineOptional.isPresent()) {
                throw new IllegalStateException("A vaccine with that email already exists");
            }
            vaccine.setName(name);
        }
    }
}
