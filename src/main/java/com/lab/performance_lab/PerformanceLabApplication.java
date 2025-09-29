package com.lab.performance_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.micrometer.core.aop.TimedAspect;
import org.springframework.context.annotation.Bean;
import io.micrometer.core.instrument.MeterRegistry;
import com.lab.performance_lab.controller.*;
import com.lab.performance_lab.repository.ProductsRepository;
import com.lab.performance_lab.service.ProductService;


@SpringBootApplication
public class PerformanceLabApplication {

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PerformanceLabApplication.class, args);
	}
}
