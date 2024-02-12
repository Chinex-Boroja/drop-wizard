package com.chinexboroja.resources.employeemanagement;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Map;
import java.util.Set;

@Produces(MediaType.APPLICATION_JSON)
@Path("/status")
public class HealthCheckController {

    private HealthCheckRegistry registry;

    public HealthCheckController(HealthCheckRegistry registry) {
        this.registry = registry;
    }

    @GET
    @PermitAll
    public Set<Map.Entry<String, HealthCheck.Result>> getStatus() {
        return registry.runHealthChecks().entrySet();
    }
}
