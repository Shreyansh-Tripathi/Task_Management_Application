package com.service.teacher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employeeId;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "email cannot be null")
    @Email(message = "invalid email")
    private String email;
    @NotNull(message = "contact cannot be null")
    @Length(min = 10, max = 10, message = "invalid contact number")
    private String contact;
    private List<String> studentIds;
    private List<String> taskIds;
}
