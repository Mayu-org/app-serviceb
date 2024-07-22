package com.example.serviceb;


import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBApplication.class, args);
    }
}

@RestController
class ServiceBController {

    @GetMapping("/info")
    @WithSpan
    public String getInfo() {
        return "Hello from Service B";
    }
}
