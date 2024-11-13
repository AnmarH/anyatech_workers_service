package com.anyatech.workers_service.services;

import model.Worker;
import model.Workers;
import org.springframework.http.ResponseEntity;

public interface WorkersService {

    ResponseEntity<Worker> getWorkerById(Long id);

    ResponseEntity<Workers> getWorkers();

}
