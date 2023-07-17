package com.job.todo.Repository;

import com.job.todo.Model.ToDoUser;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends CouchbaseRepository<ToDoUser, String> {


    boolean existsByUsername(String username);
    Optional<ToDoUser> findByUsername(String username);
}
