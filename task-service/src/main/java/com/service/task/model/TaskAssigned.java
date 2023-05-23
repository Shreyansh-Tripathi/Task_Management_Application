package com.service.task.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskAssigned {

    @NotNull(message = "task Id cannot be null")
    private Long taskId;

    @NotNull(message = "student roll number cannot be null")
    private Long studentRollNum;
}
