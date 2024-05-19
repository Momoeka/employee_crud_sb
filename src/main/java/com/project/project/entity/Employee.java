package com.project.project.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Employee {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int empid;

    @Column(nullable = false)
    @NotBlank(message = "Please enter first name")
    public String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Please enter last name")
    public String lastName;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Please enter your email address")
    public String email;

    @Column(nullable = false)
    @Pattern(regexp = "(\\+[0-9]{1,3})?\\s?[0-9]{10}", message = "Phone number must contain only numbers")

    public String phonenumber;

    @Column(nullable = false)
    public String countrycode;
}
