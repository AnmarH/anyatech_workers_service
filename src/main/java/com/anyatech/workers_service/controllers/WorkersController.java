package com.anyatech.workers_service.controllers;

import api.WorkersApi;
import com.anyatech.workers_service.services.WorkersService;
import lombok.extern.slf4j.Slf4j;
import model.Worker;
import model.Workers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WorkersController implements WorkersApi {

    private WorkersService workersServiceImpl;

    public WorkersController(@Autowired WorkersService workersServiceImpl){
        this.workersServiceImpl = workersServiceImpl;
    }


    public ResponseEntity<Worker> getWorkerById(Long id) {
        return this.workersServiceImpl.getWorkerById(id);
    }

    public ResponseEntity<Workers> getWorkers() {
        return this.workersServiceImpl.getWorkers();
    }


}
