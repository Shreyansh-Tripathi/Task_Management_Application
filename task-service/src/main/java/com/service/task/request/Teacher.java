package com.service.task.request;

import java.util.List;

public record Teacher(Long empId, String name, String email, String contact, List<Long> studentIds, List<Long> taskIds) {
}
