package com.service.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskId;
    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "name cannot be null")
    private String description;
    @NotNull(message = "teacher cannot be null")
    private Long teacherId;
}
