package com.layermark.interviewtask.doctor;

import com.layermark.interviewtask.patient.Patient;
import com.layermark.interviewtask.patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class defines a controller layer for the API, dealing with mapping inputs and outputs between the service layer and the application
 */

@RestController
@RequestMapping("task/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getDoctors() { return doctorService.getDoctors(); }

    @PostMapping
    public void addNewDoctor(@RequestBody Doctor doctor) {
        doctorService.addNewDoctor(doctor);
    }

    @DeleteMapping(path="{doctorId}")
    public void deleteDoctor(@PathVariable("doctorId") Long doctorId) {
        doctorService.deleteDoctor(doctorId);
    }

    @PutMapping(path = "{doctorId}")
    public void upDatePatient(
            @PathVariable("doctorId") Long doctorId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        doctorService.updatePatient(doctorId, name, email);
    }
}
