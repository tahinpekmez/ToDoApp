package com.job.todo.Repository;

import com.job.todo.Model.ToDoUser;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.Optional;

public interface UserRepository extends CouchbaseRepository<ToDoUser, String> {

    boolean existsByUsername(String username);
    Optional<ToDoUser> findByUsername(String username);
}
