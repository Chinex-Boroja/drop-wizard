package com.chinexboroja.resources.employeemanagement;

import com.chinexboroja.constants.URIConstants;
import com.chinexboroja.core.model.employee.Employee;
import com.chinexboroja.db.employeerepo.EmployeeRepository;
import com.chinexboroja.security.UserPrincipal;
import io.dropwizard.auth.Auth;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

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
    @PermitAll
    public Response getEmployees(@Auth UserPrincipal user) {
        return Response.ok(repository.getEmployees()).build();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getEmployeeById(@PathParam("id") Integer id, @Auth UserPrincipal user) {

        Employee employee = repository.getEmployee(id);
        if (employee != null) {
            return Response.ok(employee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @RolesAllowed({"ADMIN"})
    public Response createEmployee(Employee employee, @Auth UserPrincipal user) throws URISyntaxException {

        // validation
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        if (!violations.isEmpty()) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Employee> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();

        }

        Employee checkEmployee = repository.getEmployee(employee.getId());
        if (checkEmployee != null) {
            repository.updateEmployee(employee.getId(), employee);
            return Response.created(new URI(URIConstants.EMPLOYEE_URI + employee.getId())).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @PermitAll
    public Response updateEmployeeById(@PathParam("id") Integer id, Employee employee, @Auth UserPrincipal user) {

        // validation
        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Employee checkEmployee = repository.getEmployee(employee.getId());

        if (!violations.isEmpty()) {
            ArrayList<String> validationMessages = new ArrayList<>();
            for (ConstraintViolation<Employee> violation : violations) {
                validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(validationMessages).build();

        }

        if (checkEmployee != null) {
            employee.setId(id);
            repository.updateEmployee(id, employee);
            return Response.ok(employee).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response removeEmployeeById(@PathParam("id") Integer id, @Auth UserPrincipal user) {
        Employee employee = repository.getEmployee(id);
        if (employee != null) {
            repository.removeEmployee(id);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
