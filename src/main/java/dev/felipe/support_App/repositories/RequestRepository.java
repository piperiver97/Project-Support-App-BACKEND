package dev.felipe.support_App.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import dev.felipe.support_App.models.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

    
} 
    

