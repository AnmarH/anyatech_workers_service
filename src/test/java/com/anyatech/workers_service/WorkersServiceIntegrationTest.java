package com.anyatech.workers_service;

import com.anyatech.workers_service.controllers.WorkersController;
import model.Worker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WorkersServiceIntegrationTest {

    @Autowired
    private WorkersController workersController;

    @Test
    @DisplayName("This is a full service integration test which will test this service's getWorkerById " +
            "endpoint all the way to the DB and assert on the response at the controller layer. This means absolutely nothing is mocked")
    public void getWorkerById(){


        ResponseEntity<Worker> workerResponseEntity = workersController.getWorkerById(1L);

        assertEquals(workerResponseEntity.getBody().getId(),1L);
        assertEquals(workerResponseEntity.getBody().getIncome(), 130000);
    }


}
