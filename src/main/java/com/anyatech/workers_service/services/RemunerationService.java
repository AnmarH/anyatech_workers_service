package com.anyatech.workers_service.services;

import model.Worker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RemunerationService {

    @Value("${worker.type.fulltime.salary}")
    private Integer fullTimeSalary;


    public void addSalary(Worker worker) {

        if(worker.getType().equals(Worker.TypeEnum.FULL_TIME)){
            worker.setIncome(fullTimeSalary);
        } else if (worker.getType().equals(Worker.TypeEnum.CONTRACTOR)) {
            worker.setIncome(150000);
        } else if (worker.getType().equals(Worker.TypeEnum.INTERN)) {
            worker.setIncome(25000);
        }

    }

}
