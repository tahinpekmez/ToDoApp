package com.job.todo.Request;

import com.job.todo.Model.JobStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CreateRequest {

    private String taskName;
    private String taskDescription;
    private JobStatus jobStatus;




}
