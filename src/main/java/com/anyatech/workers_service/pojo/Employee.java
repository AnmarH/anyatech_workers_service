package com.anyatech.workers_service.pojo;

import lombok.*;

import java.lang.ref.Cleaner;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Employee implements Comparable<Employee> {

    private String name;
    private int age;

    private static final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable = null;

    @Override
    public int compareTo(Employee emp) {
        return Integer.compare(this.age, emp.age);
    }

    //public Employee() {
//        this.cleanable = cleaner.register(this, () -> {
//            System.out.println("Employee cleaned up");
//        });
    //}



/*
    What this gives you
• 	The log message will eventually run
• 	It runs off the main thread
• 	It’s safe (no resurrection issues)
• 	It’s the supported mechanism going forward
    What it does not give you
• 	A guarantee when cleanup happens
• 	A guarantee cleanup happens before JVM shutdown
    But this is as close as Java gets to “log when GC happens.”
*/


}
