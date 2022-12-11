package com.layermark.interviewtask.doctor;

import com.layermark.interviewtask.patient.Patient;
import com.layermark.interviewtask.patient.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DoctorConfig {

    @Bean
    CommandLineRunner commandLineRunner2(DoctorRepository doctorRepository) {
        return args -> {
            Doctor stephen = new Doctor(1L, "Stephen Hawking","stevehawk@gmail.com");
            Doctor mike = new Doctor(2L, "Michael Jackson", "billiejean@gmail.com");
            doctorRepository.saveAll(List.of(stephen, mike));
        };
    }
}
