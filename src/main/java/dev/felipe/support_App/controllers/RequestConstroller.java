package dev.felipe.support_App.controllers;
import dev.felipe.support_App.models.Request;
import dev.felipe.support_App.services.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/support-request")
@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class RequestConstroller {
    private final RequestService service;
    
    public RequestConstroller(RequestService service){
        this.service = service;

    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests(){
        List<Request> requests = service.getAll();
        return ResponseEntity.ok(requests);
    } 
    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping 
    public ResponseEntity<?> createRequest(@Valid @RequestBody Request newRequest){
        try{
            Request createdRequest = service.store(newRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest); 
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("error creando la peticion: " + e.getMessage());
        }
        
        
    }  
    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id,@Valid @RequestBody Request updatedRequest){
        try{
            Request updated = service.update(id, updatedRequest);
            return ResponseEntity.ok(updated);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    } 
    @GetMapping("/health")
public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("API is working");
} 
} 



