package com.layermark.interviewtask.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {

    @Bean
    CommandLineRunner commandLineRunner1(PatientRepository patientRepository) {
        return args -> {
            Patient ben = new Patient(1L, "Benjamin Franklin","benfrank@gmail.com", LocalDate.of(1973, Month.MARCH, 16));
            Patient jack = new Patient(2L, "Jack Daniels", "jackdan@gmail.com", LocalDate.of(1986, Month.NOVEMBER, 28));
            patientRepository.saveAll(List.of(ben, jack));
        };
    }
}