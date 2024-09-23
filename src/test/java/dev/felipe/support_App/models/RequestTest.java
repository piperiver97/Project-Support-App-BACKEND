package dev.felipe.support_App.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.time.LocalDateTime;
import java.util.Set;

public class RequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testRequestDateDefault() {
        Request request = new Request();
        assertNotNull(request.getRequestDate());
        assertTrue(LocalDateTime.now().isAfter(request.getRequestDate()) || LocalDateTime.now().isEqual(request.getRequestDate()));
    }

    @Test
    public void testValidRequest() {
        Request request = new Request();
        request.setRequestName("John Doe");
        request.setSubject("Subject");
        request.setDescription("Description");

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testRequestNameNotBlank() {
        Request request = new Request();
        request.setRequestName("");
        request.setSubject("Subject");
        request.setDescription("Description");

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Requester name is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testSubjectNotBlank() {
        Request request = new Request();
        request.setRequestName("John Doe");
        request.setSubject("");
        request.setDescription("Description");

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Subject is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testDescriptionNotBlank() {
        Request request = new Request();
        request.setRequestName("John Doe");
        request.setSubject("Subject");
        request.setDescription("");

        Set<ConstraintViolation<Request>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Description is required", violations.iterator().next().getMessage());
    }

    @Test
    public void testSettersAndGetters() {
        Request request = new Request();
        request.setId(1L);
        request.setRequestName("John Doe");
        request.setSubject("Subject");
        request.setDescription("Description");

        assertEquals(1L, request.getId());
        assertEquals("John Doe", request.getRequestName());
        assertEquals("Subject", request.getSubject());
        assertEquals("Description", request.getDescription());
    }
}
