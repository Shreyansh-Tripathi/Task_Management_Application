package com.service.teacher.request;

import java.util.List;

public record Task(long taskId, String name, String description, List<Long> studentIds, Long teacherId) {

}
