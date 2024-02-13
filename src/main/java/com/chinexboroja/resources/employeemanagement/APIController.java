package com.chinexboroja.resources.employeemanagement;

import com.chinexboroja.constants.URIConstants;
import com.chinexboroja.core.model.employee.Employee;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

@Path("client-root-path")
public class APIController {

    private Client jerseyClient;

    public APIController(Client jerseyClient) {
        this.jerseyClient = jerseyClient;
    }

    @GET
    @Path("/employees/")
    public String getEmployees() {
        WebTarget webTarget = jerseyClient.target(URIConstants.EMPLOYEES_BASE_URI);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        ArrayList<Employee> employees = response.readEntity(ArrayList.class);
        return  employees.toString();
    }

    @GET
    @Path("/employees/{id}")
    public String getEmployeeById(@PathParam("id") int id) {
        WebTarget webTarget = jerseyClient.target(URIConstants.EMPLOYEES_BASE_URI + id);
        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Employee employee = response.readEntity(Employee.class);
        return employee.toString();
    }
}
