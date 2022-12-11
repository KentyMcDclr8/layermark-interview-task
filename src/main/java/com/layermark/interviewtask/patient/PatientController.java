package com.layermark.interviewtask.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task/api/patient")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    public void addNewPatient(@RequestBody Patient patient) {
        patientService.addNewPatient(patient);
    }

    @DeleteMapping(path="{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.deletePatient(patientId);
    }

    @PutMapping(path = "{patientId}")
    public void upDatePatient(
            @PathVariable("patientId") Long patientId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        patientService.updatePatient(patientId, name, email);
    }

    @PutMapping("/{patientId}/doctor/{doctorId}")
    public void assignDoctor(@PathVariable("patientId") Long patientId, @PathVariable("doctorId") Long doctorId) {
        patientService.assignDoctor(patientId, doctorId);
    }

}
