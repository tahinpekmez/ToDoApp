package com.job.todo.Service;

import com.job.todo.Exception.SameTaskException;
import com.job.todo.Model.JobStatus;
import com.job.todo.Model.ToDoModel;
import com.job.todo.Repository.ToDoRepository;
import com.job.todo.Request.CreateRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class ToDoService {

    private final ToDoRepository repository;

    public void createTask(@RequestBody CreateRequest request) throws SameTaskException {
        ToDoModel createTask = new ToDoModel();
        createTask.setTaskName(request.getTaskName());
        createTask.setTaskDescription(request.getTaskDescription());
        createTask.setStatus(JobStatus.IN_PROGRESS);

        if(repository.existsByTaskName(request.getTaskName())) {
            throw new SameTaskException(new Date(), "The task is already been created!");
        }
        repository.save(createTask);
    }
}