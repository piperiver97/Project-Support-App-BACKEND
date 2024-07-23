package dev.felipe.support_App.services;
import org.springframework.stereotype.Service;
import dev.felipe.support_App.models.Request;
import dev.felipe.support_App.repositories.RequestRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
@Service 
public class RequestService {
    private final RequestRepository requestRepository;
    @Autowired
    public RequestService(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }
    public List<Request> getAll(){
        return requestRepository.findAll();

    }

    public Optional<Request> findById(Long id){
        return requestRepository.findById(id);
    }
    public Request store(@Valid Request request){
        if (request.getRname() == null || request.getRname().isBlank()){
            throw new IllegalArgumentException("se necesita un nombre");
        } 
        return requestRepository.save(request);
    
    }

    public Request update(Long id, @Valid Request updatedRequest){
        return requestRepository.findById(id)
        .map(existingRequest -> {
            if (updatedRequest.getRname() != null){

                existingRequest.setRname(updatedRequest.getRname());

            }
        if (updatedRequest.getSubject() != null){
            existingRequest.setSubject(updatedRequest.getSubject());
        }
        if (updatedRequest.getDescription() != null){
            existingRequest.setDescription(updatedRequest.getDescription());
        }
        return requestRepository.save(existingRequest);
        
        })
        .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id " + id));
    }
    public void delete(Long id){
        if (!requestRepository.existsById(id)){
            throw new RuntimeException("Solicitud no encontrada con id " + id);
        }
        requestRepository.deleteById(id);
    }


}

