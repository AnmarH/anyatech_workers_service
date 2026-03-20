package com.anyatech.workers_service.services;

import com.anyatech.workers_service.pojo.Animal;
import com.anyatech.workers_service.pojo.Cat;
import com.anyatech.workers_service.pojo.Cow;
import com.anyatech.workers_service.pojo.Employee;
import lombok.RequiredArgsConstructor;
import model.Worker;
import model.Workers;
import model.WorkersStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkersService {

    private final RemunerationService remunerationService;
    private final DataStructureAlgorithms algorithms;


    public ResponseEntity<Worker> getWorkerById(Long id) {

        Worker worker  = new Worker();
        worker.setId(id);
        worker.setName("Anmar Hammadi");
        worker.setStatus(Worker.StatusEnum.ACTIVE);
        worker.setType(Worker.TypeEnum.FULL_TIME);
        remunerationService.addSalary(worker);



        return new ResponseEntity<>(worker, HttpStatus.OK);


    }


    public ResponseEntity<Workers> getWorkers() {

        Workers workers = new Workers();
        List<Worker> workersList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            workersList.add(createWorker(1));
        }

        for (int i = 0; i < 10; i++) {
            workersList.add(createWorker(2));
        }

        for (int i = 0; i < 10; i++) {
            workersList.add(createWorker(3));
        }

        for (int i = 0; i < 10; i++) {
            workersList.add(createWorker(4));
        }


        List<Worker> filteredWorkersList = workersList.stream()
        .filter(w-> w.getType().equals(Worker.TypeEnum.FULL_TIME) && w.getStatus().equals(Worker.StatusEnum.INACTIVE))
                .collect(Collectors.toList());

        //map operation
        List<Worker.StatusEnum> mappedWorkersList = workersList.stream()
                .map(w -> w.getStatus()).toList();

        //map operation
        List<Worker.StatusEnum> mappedWorkersListMethodReference = workersList.stream()
                .map(Worker::getStatus).toList();


        List<Worker> parraellelStreamList = workersList.parallelStream()
                .filter(w-> w.getType().equals(Worker.TypeEnum.FULL_TIME) && w.getStatus().equals(Worker.StatusEnum.INACTIVE))
                .toList(); // since Java 16, toList(); is all that is required but it returns an immutable list so parraellelStreamList.add()

        Set<Worker> filteredWorkersSet = workersList.stream()
                .filter(w-> w.getType().equals(Worker.TypeEnum.FULL_TIME) && w.getStatus().equals(Worker.StatusEnum.INACTIVE))
                .collect(Collectors.toSet());

        workersList.forEach(worker -> worker.setIncome(100000)); // This forEach loop does not return anything
        List<Worker> highlyPaidWorkersList = workersList; //simply assign old list to new reference representing the changes we made


        List<String> namesList = List.of("Divya", "Anmar", "Tom", "Priya");
        Set<String> uniqueNames = new TreeSet<>();

        for(String name : namesList){
            if (name.equals("Anmar") || name.equals("Divya")) {
                uniqueNames.add(name);
            }
        }

        for(String name : uniqueNames){
            System.out.println(name);
        }

        //will print Anmar, Divya due to Treesets natural ordering not insertion order





        //workers.setWorkers(filteredWorkersList);

        // This is how you can suggest GC by the JVM but there is no guarantee it will happen, the JVM decides based on the need
//        Employee emp = new Employee();
//        System.gc();
//        Runtime.getRuntime().gc();
//        emp = null;


        /*
            Below Comparator interface has only one abstract method,
            therefore is a functional interface and has the @FunctionalInterface annotation against it.
            This annotation will warn you, at compile time, if you have anymore than one abstract method.
        */
        //Comparator<Employee> byName = (emp1, emp2) -> emp1.compareTo(emp2);
        //Instead of using a lambda expression above (the parenthesis, arrow and statement with or without curly braces), you can use a method reference
        Comparator<Employee> comparatorByAge = Employee::compareTo;

        //Comparator<Employee> comparatorByName = (emp1, emp2) -> emp1.getName().compareTo(emp2.getName());



        Employee emp1 = Employee.builder().name("Anmar").age(41).build();
        Employee emp2 = Employee.builder().name("Divya").age(37).build();
        Employee emp3 = Employee.builder().name("Tom").age(23).build();
        Employee emp4 = Employee.builder().name("zzzz").age(67).build();
        Employee emp5 = Employee.builder().name("Mary").age(25).build();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);
        employeeList.add(emp4);
        employeeList.add(emp5);



        Collections.sort(employeeList, comparatorByAge);
        for (Employee emp : employeeList) {
            System.out.println(emp.toString());
        }

        Runnable runnable = () -> System.out.println("This is the runnable interface");
        runnable.run();


        final int[] nums = {5,7,9,11,13,15,17,19,21,22,23,24,25,26,27,28,29,30};

        int linearSearchResult = algorithms.linearSearch(nums,25);
        int binarySearchResult = algorithms.binarySearch(nums,25);

        if(linearSearchResult != -1) {
            System.out.println("Linear search element found at index: " + linearSearchResult);
        } else System.out.println("Linear search element not found");

        if(binarySearchResult != -1) {
            System.out.println("Binary search element found at index: " + binarySearchResult);
        } else System.out.println("Binary search element not found");





        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    public ResponseEntity<Workers> getUniqueWorkers(){

        //Purposely added duplicates to verify HashSet is only keeping unique items.
        Set<Worker> workerSet = new HashSet<>();
        workerSet.add(this.createWorker(1));
        workerSet.add(this.createWorker(1));
        workerSet.add(this.createWorker(2));
        workerSet.add(this.createWorker(2));
        workerSet.add(this.createWorker(3));
        workerSet.add(this.createWorker(4));

        Workers workers = new Workers();
        workers.setWorkers(workerSet);

        return new ResponseEntity<>(workers,HttpStatus.OK);
    }

    public ResponseEntity<WorkersStatus> getWorkersStatus(){

        WorkersStatus workersStatus = new WorkersStatus();
        Set<Worker> workerSet = new HashSet<>();
        workerSet.add(this.createWorker(2));
        workerSet.add(this.createWorker(2));
        workerSet.add(this.createWorker(3));
        workerSet.add(this.createWorker(3));
        workerSet.add(this.createWorker(3));
        workerSet.add(this.createWorker(4));

        Optional<Worker> result = workerSet.stream()
                .filter(w -> w.getStatus().equals(Worker.StatusEnum.ACTIVE) && w.getType().equals(Worker.TypeEnum.FULL_TIME))
                .findAny();

        if(result.isPresent()) {
            workersStatus.setIsActiveFullTimePresent(Boolean.TRUE);
        } else {
            workersStatus.setIsActiveFullTimePresent(Boolean.FALSE);
        }

        return ResponseEntity.ok(workersStatus);


    }
    

    private Worker createWorker(int value){

        Worker worker  = new Worker();

        if (value == 1) {
//            worker.setId(new Random().nextLong());
            worker.setId(1L);
            worker.setName("1. Active Fulltime");
            worker.setStatus(Worker.StatusEnum.ACTIVE);
            worker.setType(Worker.TypeEnum.FULL_TIME);
            remunerationService.addSalary(worker);

        } else if (value == 2) {
//            worker.setId(new Random().nextLong());
            worker.setId(2L);
            worker.setName("2. Active Contractor");
            worker.setStatus(Worker.StatusEnum.ACTIVE);
            worker.setType(Worker.TypeEnum.CONTRACTOR);
            remunerationService.addSalary(worker);
        } else if (value == 3) {
//            worker.setId(new Random().nextLong());
            worker.setId(3L);
            worker.setName("3. Inactive Contractor");
            worker.setStatus(Worker.StatusEnum.INACTIVE);
            worker.setType(Worker.TypeEnum.CONTRACTOR);
            remunerationService.addSalary(worker);
        } else if (value == 4) {
//            worker.setId(new Random().nextLong());
            worker.setId(4L);
            worker.setName("4. Inactive Fulltime");
            worker.setStatus(Worker.StatusEnum.INACTIVE);
            worker.setType(Worker.TypeEnum.FULL_TIME);
            remunerationService.addSalary(worker);
        }

        return worker;

    }

    private void testJavaGenerics(){

        Cat cat = new Cat();
        Cow cow = new Cow();

        List<? extends Animal> cats = new ArrayList<Cat>();

        List<? super Animal> animals = new ArrayList<Object>();

    }

    /*
    * When we have a parameter as List<? super Animal> animals
    * Java generics here is introducing a compile time safety mechanism that
    * is designed to only allow writes to the animals collections and not reads
    *
    * */
    private void writeToGenericCollection(List<? super Animal> animals) {

        Cat cat = new Cat();
        animals.add(cat);



    }

    /*
     * When we have a parameter as List<? super Animal> animals
     * Java generics here is introducing a compile time safety mechanism that
     * is designed to only allow writes to the animals collections and not reads
     *
     * */
    private void readFromGenericCollection(List<? extends Animal> animals) {

        Animal cat = animals.get(0);

    }

}
