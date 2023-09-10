package com.spring.example.demo.spring.example.students;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return  args -> {
           Student ahmed = new Student(
                        1L,"Ahmed Mashhour","ahmed@mail.com", LocalDate.of(1997, Month.AUGUST,23)
                );

            Student alex = new Student(
                    "Alex Mark","alex@mail.com", LocalDate.of(1993, Month.AUGUST,23)
            );

            studentRepository.saveAll(
                    List.of(ahmed,alex)
            );
        };
    }
}
