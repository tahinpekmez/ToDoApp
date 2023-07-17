package com.job.todo.Controller;

import com.job.todo.Exception.ResourceNotFoundException;
import com.job.todo.Exception.SameTaskException;
import com.job.todo.Model.ToDoModel;
import com.job.todo.Repository.ToDoRepository;
import com.job.todo.Request.CreateRequest;
import com.job.todo.Request.UpdateRequest;
import com.job.todo.Service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;
    private final ToDoRepository repository;


    @PostMapping("/createTask")
    public ResponseEntity<CreateRequest> createTask(@RequestBody CreateRequest request) throws SameTaskException {
        service.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);

    }

    @GetMapping("/tasklist")
    public ResponseEntity<List<ToDoModel>> getTasks() {
        List<ToDoModel> tasklist = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tasklist);

    }


    @PutMapping("/update/{taskName}")
    public ResponseEntity<ToDoModel> update(@PathVariable String taskName, @RequestBody UpdateRequest updateRequest) {

        ToDoModel updateModel = repository.findByTaskName(taskName)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with taskName: " + taskName));

        updateModel.setStatus(updateRequest.getJobStatus());
        updateModel.setTaskDescription(updateRequest.getTaskDescription());
        updateModel.setTaskName(updateRequest.getTaskName());

        repository.save(updateModel);

        return ResponseEntity.ok(updateModel);


    }


    @DeleteMapping("/delete/{taskName}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "taskName") String taskName)
            throws ResourceNotFoundException {
        ToDoModel model = repository.findByTaskName(taskName)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + taskName));

        repository.delete(model);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }
}