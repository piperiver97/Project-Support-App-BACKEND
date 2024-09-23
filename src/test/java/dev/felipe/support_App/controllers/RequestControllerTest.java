package dev.felipe.support_App.controllers;

import dev.felipe.support_App.models.Request;
import dev.felipe.support_App.services.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService service;

    @InjectMocks
    private RequestController controller;

    private Request request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        
        request = new Request();
        request.setId(1L);
        request.setRequestName("John Doe");
        request.setSubject("Subject");
        request.setDescription("Description");
    }

    @Test
    public void testGetAllRequests() throws Exception {
        when(service.getAll()).thenReturn(Arrays.asList(request));

        mockMvc.perform(get("/api/support-requests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].requestName", is("John Doe")))
                .andExpect(jsonPath("$[0].subject", is("Subject")))
                .andExpect(jsonPath("$[0].description", is("Description")));
        
        verify(service, times(1)).getAll();
    }

    @Test
    public void testGetRequestById() throws Exception {
        when(service.findById(anyLong())).thenReturn(Optional.of(request));

        mockMvc.perform(get("/api/support-requests/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.requestName", is("John Doe")))
                .andExpect(jsonPath("$.subject", is("Subject")))
                .andExpect(jsonPath("$.description", is("Description")));
        
        verify(service, times(1)).findById(1L);
    }

    @Test
    public void testGetRequestByIdNotFound() throws Exception {
        when(service.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/support-requests/1"))
                .andExpect(status().isNotFound());
        
        verify(service, times(1)).findById(1L);
    }

    @Test
    public void testCreateRequest() throws Exception {
        when(service.store(any(Request.class))).thenReturn(request);

        mockMvc.perform(post("/api/support-requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"requestName\":\"John Doe\",\"subject\":\"Subject\",\"description\":\"Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.requestName", is("John Doe")))
                .andExpect(jsonPath("$.subject", is("Subject")))
                .andExpect(jsonPath("$.description", is("Description")));
        
        verify(service, times(1)).store(any(Request.class));
    }

    @Test
    public void testUpdateRequest() throws Exception {
        when(service.update(anyLong(), any(Request.class))).thenReturn(request);

        mockMvc.perform(put("/api/support-requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"requestName\":\"John Doe\",\"subject\":\"Subject\",\"description\":\"Description\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.requestName", is("John Doe")))
                .andExpect(jsonPath("$.subject", is("Subject")))
                .andExpect(jsonPath("$.description", is("Description")));
        
        verify(service, times(1)).update(anyLong(), any(Request.class));
    }

    @Test
    public void testUpdateRequestNotFound() throws Exception {
        when(service.update(anyLong(), any(Request.class))).thenThrow(new IllegalArgumentException());

        mockMvc.perform(put("/api/support-requests/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"requestName\":\"John Doe\",\"subject\":\"Subject\",\"description\":\"Description\"}"))
                .andExpect(status().isNotFound());
        
        verify(service, times(1)).update(anyLong(), any(Request.class));
    }

    @Test
    public void testDeleteRequest() throws Exception {
        doNothing().when(service).delete(anyLong());

        mockMvc.perform(delete("/api/support-requests/1"))
                .andExpect(status().isNoContent());
        
        verify(service, times(1)).delete(1L);
    }

    @Test
    public void testDeleteRequestNotFound() throws Exception {
        doThrow(new IllegalArgumentException()).when(service).delete(anyLong());

        mockMvc.perform(delete("/api/support-requests/1"))
                .andExpect(status().isNotFound());
        
        verify(service, times(1)).delete(1L);
    }

    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/api/support-requests/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("API is working"));
    }
}
