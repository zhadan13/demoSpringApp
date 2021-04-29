package com.example.demo.student;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

// @MappedSuperclass
// @Basic
// @Access(AccessType.PROPERTY)
// @Cacheable
// @Column // JPA Hibernate annotation for column name
// @NamedQuery()
// @NamedNativeQuery()

@Entity(name = "Student") // JPA
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(name = "student_email_unique", columnNames = "email")})
// JPA
public class Student {
    @Id // Primary key
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;

    // @Value("$name") // add properties to bean

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Transient // JPA
    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    // JPA
    public Student() {
    }

    public Student(Long id, String name, LocalDate dob, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Student(String name, LocalDate dob, String email) {
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student { " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", age = " + age +
                ", dob = " + dob +
                ", email = '" + email + '\'' +
                '}';
    }

    /*
    @Embeddable
    static class Embedded {

    }
    */
}