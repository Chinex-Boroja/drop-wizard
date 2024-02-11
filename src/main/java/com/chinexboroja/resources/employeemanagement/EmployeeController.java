package com.chinexboroja.resources.employeemanagement;

import com.chinexboroja.db.employeerepo.EmployeeRepository;
import jakarta.validation.Validator;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private Validator validator;
    private EmployeeRepository repository;

    public EmployeeController(Validator validator, EmployeeRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    @GET
    public Response getEmployees() {
        return Response.ok()
    }
}
