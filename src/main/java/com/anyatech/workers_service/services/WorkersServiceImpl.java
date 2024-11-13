package com.anyatech.workers_service.services;

import model.Worker;
import model.Workers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkersServiceImpl implements WorkersService{

    @Override
    public ResponseEntity<Worker> getWorkerById(Long id) {

        Worker worker  = new Worker();
        worker.setId(id);
        worker.setName("Anmar Hammadi");
        worker.setStatus(Worker.StatusEnum.ACTIVE);

        return new ResponseEntity<>(worker, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<Workers> getWorkers() {

        Workers workers = new Workers();
        List<Worker> workersList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            workersList.add(createWorker());
        }

        workers.setWorkers(workersList);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    private Worker createWorker(){
        Worker worker  = new Worker();
        worker.setId(new Random().nextLong());
        worker.setName("Random Name");
        worker.setStatus(Worker.StatusEnum.ACTIVE);
        return worker;
    }
}
