package com.lab.performance_lab.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class ProductMetrics {

    private final Counter productFilterRequests;

    public ProductMetrics(MeterRegistry registry) {
        this.productFilterRequests = Counter
                .builder("product_filter_requests_total")
                .description("Total number of /filter requests")
                .register(registry);
    }

    public void incrementFilterRequest() {
        productFilterRequests.increment();
    }
}