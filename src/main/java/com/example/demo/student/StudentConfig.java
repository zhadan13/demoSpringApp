package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student Artem = new Student(
                    1L, "Artem", LocalDate.of(2001, Month.OCTOBER, 21), "artem01zh@gmail.com"
            );
            Student Andrew = new Student("Andrew", LocalDate.of(1991, Month.DECEMBER, 13), "andrey91zh@gmail.com"
            );

            repository.saveAll(List.of(Artem, Andrew));
        };
    }
}
