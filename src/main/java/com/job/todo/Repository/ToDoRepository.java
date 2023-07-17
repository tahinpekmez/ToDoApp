package com.job.todo.Repository;

import com.job.todo.Model.ToDoModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ToDoRepository extends CouchbaseRepository<ToDoModel, String> {


    Optional<ToDoModel> findById(String id);
    boolean existsByTaskName(String taskName);
    Optional <ToDoModel> findByTaskName(@NotNull String taskName);
}
