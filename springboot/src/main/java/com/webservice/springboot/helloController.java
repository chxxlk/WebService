package com.webservice.springboot;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class helloController {

    private final helloRepository repository;

    public helloController(helloRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot";
    }

    @GetMapping("/greeting")
    public Map<String, Object> sayGreeting() {
        return Map.of(
            "message",
            "Hello, Spring Boot",
            "timestamp",
            Instant.now().toString()
        );
    }

    @PostMapping("/")
    public hello create(@RequestBody hello greeting) {
        greeting.setTimestamp(java.time.Instant.now());
        return repository.save(greeting);
    }

    @GetMapping("/")
    public List<hello> getAll() {
        return repository.findAll();
    }
}
