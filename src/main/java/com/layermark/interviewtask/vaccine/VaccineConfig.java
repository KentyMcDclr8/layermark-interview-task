package com.layermark.interviewtask.vaccine;

import com.layermark.interviewtask.patient.Patient;
import com.layermark.interviewtask.patient.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class VaccineConfig {
    @Bean
    CommandLineRunner commandLineRunner3(VaccineRepository vaccineRepository) {
        return args -> {
            Vaccine covid = new Vaccine(1L, "COVID-19");
            Vaccine hepB = new Vaccine(2L, "Hepatitis B");
            vaccineRepository.saveAll(List.of(covid, hepB));
        };
    }
}
