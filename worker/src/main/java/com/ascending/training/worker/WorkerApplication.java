package com.ascending.training.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
//      SQSMessageService messageService = app.getBean(SQSMessageService.class);
//      messageService.receiveMessage();
    }
}
