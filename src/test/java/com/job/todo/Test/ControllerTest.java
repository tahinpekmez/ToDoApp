package com.job.todo.Test;

import com.job.todo.Model.JobStatus;
import com.job.todo.Model.ToDoModel;
import com.job.todo.Repository.ToDoRepository;
import com.job.todo.Request.CreateRequest;
import com.job.todo.Request.LoginRequest;
import com.job.todo.Request.SignInRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.jsonwebtoken.lang.Classes.getResourceAsStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@AutoConfigureMockMvc
public class ControllerTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ToDoRepository repository;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testLogin() throws Exception {
        String uri = "/login";
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("string");
        loginRequest.setPassword("string");
        String inputJson = super.mapToJson(loginRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.startsWith("ey"));
    }

    @Test
    public void testSignup() throws Exception {
        String uri = "/signup";
        SignInRequest signupRequest = new SignInRequest();
        signupRequest.setUsername("ipek");
        signupRequest.setPassword("admin");
        String inputJson = super.mapToJson(signupRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.printf(content);
        assertEquals("{\"message\":\"User registered successfully!\"}", content);
    }

    @Test
    public void TestCreateTask() throws Exception {
        testLogin();
        String uri = "/createTask";
        CreateRequest testRequest = new CreateRequest();
        testRequest.setJobStatus(JobStatus.IN_PROGRESS);
        testRequest.setTaskName("xtask");
        testRequest.setTaskDescription("this is a test task");


        String inputJson = super.mapToJson(testRequest);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "{\"taskName\":\"xtask\",\"taskDescription\":\"this is a test task\",\"jobStatus\":\"IN_PROGRESS\"}");
    }

    @Test
    public void deleteTask() throws Exception {
        testLogin();
        String uri = "/delete/xtask";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "{\"deleted\":true}");
    }

    @Test
    public void getTaskList() throws Exception {
        testLogin();
        String uri = "/tasklist";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();

        String taskList = super.mapToJson(content);
        assertTrue(taskList.length() > 0);
    }

    @Test
    public void updateTask() throws Exception {
        testLogin();
        String uri = "/update/xtask";
        ToDoModel model = new ToDoModel();
        model.setTaskName("Lemon");
        model.setStatus(JobStatus.DONE);
        model.setTaskDescription("this is a test task to update");
        model.setLocalTime(model.getLocalTime());

        repository.save(model);

        String inputJson = super.mapToJson(model);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is updated successsfully");
    }

}

