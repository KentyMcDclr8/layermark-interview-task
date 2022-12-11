package com.layermark.interviewtask.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("task/api/vaccine")
public class VaccineController {

    private final VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    public List<Vaccine> getVaccines() { return vaccineService.getVaccines();}

    @PostMapping
    public void addNewVaccine(@RequestBody Vaccine vaccine) {
        vaccineService.addNewVaccine(vaccine);
    }

    @DeleteMapping(path="{vaccineId}")
    public void deleteVaccine(@PathVariable("vaccineId") Long vaccineId) {
        vaccineService.deleteVaccine(vaccineId);
    }

    @PutMapping(path = "{vaccineId}")
    public void upDateVaccine(
            @PathVariable("vaccineId") Long vaccineId,
            @RequestParam(required = false) String name) {
        vaccineService.updateVaccine(vaccineId, name);
    }

    @PutMapping("/{vaccineId}/patient/{patientId}")
    public void assignPatient(@PathVariable("vaccineId") Long vaccineId, @PathVariable("patientId") Long patientId) {
        vaccineService.assignPatient(vaccineId, patientId);
    }

    @PutMapping("/{vaccineId}/doctor/{doctorId}")
    public void assignDoctor(@PathVariable("vaccineId") Long vaccineId, @PathVariable("doctorId") Long doctorId) {
        vaccineService.assignDoctor(vaccineId, doctorId);
    }

}
