package com.anyatech.workers_service.controllers;

import api.WorkersApi;
import lombok.extern.slf4j.Slf4j;
import model.Worker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WorkersController implements WorkersApi {

    public ResponseEntity<Worker> getWorkerById(Long workerId) {

        Worker worker  = new Worker();
        worker.setId(workerId);
        worker.setName("Anmar Hammadi");
        worker.setStatus(Worker.StatusEnum.ACTIVE);

        return new ResponseEntity<>(worker, HttpStatus.OK);
    }


}
