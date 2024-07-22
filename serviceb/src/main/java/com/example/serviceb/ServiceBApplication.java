package com.example.serviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
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
    private final Tracer tracer;

    public ServiceBController(OpenTelemetry openTelemetry) {
        this.tracer = openTelemetry.getTracer(ServiceBController.class.getName());
    }

    @GetMapping("/info")
    public String getInfo() {
        Span span = tracer.spanBuilder("getInfo").startSpan();
        try (var scope = span.makeCurrent()) {
            return "Hello from Service B, this is a test! Making a change :)";
        } finally {
            span.end();
        }
    }
}
