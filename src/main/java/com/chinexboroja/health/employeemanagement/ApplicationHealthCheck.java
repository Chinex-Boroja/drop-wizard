package com.chinexboroja.health.employeemanagement;

import com.chinexboroja.constants.URIConstants;
import com.chinexboroja.core.model.employee.Employee;
import com.codahale.metrics.health.HealthCheck;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

public class ApplicationHealthCheck extends HealthCheck {

    private final Client client;

    public ApplicationHealthCheck(Client client) {
        super();
        this.client = client;
    }
    @Override
    protected Result check() throws Exception {

        WebTarget webTarget = client.target(URIConstants.EMPLOYEES_BASE_URI);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON_TYPE);

        Response response = invocationBuilder.get();
        ArrayList<Employee> employees = response.readEntity(ArrayList.class);

        if (employees != null && !employees.isEmpty()) {
            return Result.healthy();
        }
        return Result.unhealthy("API Failed");
    }
}
