package com.service.teacher.request;

import java.util.List;

public record Task(Long taskId, String name, String description, Long teacherId) {

}
