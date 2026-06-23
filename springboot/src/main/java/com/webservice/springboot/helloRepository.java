package com.webservice.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface helloRepository extends JpaRepository<hello, Long> {}
