package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldFindStudentByEmail() {
        // given
        String email = "artem@mail.com";
        Student student = new Student("Artem", LocalDate.of(2001, Month.OCTOBER, 21), email);
        underTest.save(student);

        // when
        Optional<Student> optionalStudent = underTest.findStudentByEmail(email);

        // then
        assertThat(optionalStudent).isPresent();
    }

    @Test
    void itShouldNotFindStudentByEmail() {
        // given
        String email = "artem123@mail.com";

        // when
        Optional<Student> optionalStudent = underTest.findStudentByEmail(email);

        // then
        assertThat(optionalStudent).isEmpty();
    }
}
