package com.example.serviceb;


import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class ServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBApplication.class, args);
    }
}

@RestController
class ServiceBController {
    private static final Logger logger = LogManager.getLogger(ServiceBController.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @GetMapping("/info")
    @WithSpan
    public String getInfo() {
        String timestamp = LocalDateTime.now().format(formatter);
        logger.debug("Sending response to Service A at : {}", timestamp);
        logger.info("Sending info entry here while responding to Service a at {}",timestamp);
        return "Hello from Service B";
    }
}
