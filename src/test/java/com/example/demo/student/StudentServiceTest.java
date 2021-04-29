package com.example.demo.student;

import com.example.demo.exception.ApiRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    private AutoCloseable autoCloseable;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentService(studentRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getStudents() {
        // when
        underTest.getStudents();

        // then
        verify(studentRepository).findAll();
    }

    @Test
    void addNewStudentIfNotExists() {
        // given
        Student student = new Student("Artem", LocalDate.of(2001, Month.OCTOBER, 21), "email");

        // when
        underTest.addNewStudent(student);

        // then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());
        Student capturedStudent = studentArgumentCaptor.getValue();
        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void addNewStudentIfExists() {
        // given
        Student student = new Student("Artem", LocalDate.of(2001, Month.OCTOBER, 21), "email");

        given(studentRepository.findStudentByEmail(student.getEmail())).willReturn(Optional.of(new Student()));

        // when
        // then
        assertThatThrownBy(() -> underTest.addNewStudent(student))
                .isInstanceOf(ApiRequestException.class)
                .hasMessage("email taken");
        verify(studentRepository, never()).save(any());
    }

    @Test
    @Disabled
    void deleteStudentIfExists() {

    }

    @Test
    void deleteStudentIfNotExists() {
        // given
        Student student = new Student("Artem", LocalDate.of(2001, Month.OCTOBER, 21), "email");

        // when
        // then
        assertThatThrownBy(() -> underTest.deleteStudent(student.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("student with id " + student.getId() + " does not exists");
        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    @Disabled
    void updateStudent() {

    }
}