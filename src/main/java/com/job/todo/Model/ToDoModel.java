package com.job.todo.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.index.CompositeQueryIndex;
import org.springframework.data.couchbase.core.index.QueryIndexed;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@CompositeQueryIndex(fields = {"id", "taskName desc"})
public class ToDoModel {


    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    @QueryIndexed
    private String taskName;

    @NotNull
    @Size(min = 10, message = "Enter at least 10 Characters...")
    private String taskDescription;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime lastUpdate = LocalDateTime.now();

    @NotNull
    private JobStatus status;

}