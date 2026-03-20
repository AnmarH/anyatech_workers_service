package com.anyatech.workers_service.controllers;

import api.WorkersApi;
import com.anyatech.workers_service.services.WorkersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.Worker;
import model.Workers;

import model.WorkersStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WorkersController implements WorkersApi {

    private final WorkersService workersService;

    public ResponseEntity<Worker> getWorkerById(Long id) {

        log.info(">>>>>>>>>>>>>>> getWorkerById endpoint hit: <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return this.workersService.getWorkerById(id);
    }

    public ResponseEntity<Workers> getWorkers() {

        log.info(">>>>>>>>>>>>>>> getWorkers endpoint hit: <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return this.workersService.getUniqueWorkers();
    }

    public ResponseEntity<WorkersStatus> getWorkersStatus() {

        return this.workersService.getWorkersStatus();
    }


}
