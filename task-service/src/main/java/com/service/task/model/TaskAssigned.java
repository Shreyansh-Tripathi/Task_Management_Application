package com.service.task.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskAssigned {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "task Id cannot be null")
    private Long taskId;

    @NotNull(message = "student roll number cannot be null")
    private Long studentRollNum;

    public TaskAssigned(Long taskId, Long rollNum){
        this.taskId=taskId;
    }
}
