package com.chinexboroja;

import com.chinexboroja.core.model.SongWriter;
import com.chinexboroja.db.employeerepo.EmployeeRepository;
import com.chinexboroja.db.repository.SongWriterRepository;
import com.chinexboroja.health.TemplateHealthCheck;
import com.chinexboroja.health.employeemanagement.ApplicationHealthCheck;
import com.chinexboroja.resources.HelloWorldResource;
import com.chinexboroja.resources.Message;
import com.chinexboroja.resources.SongWriters;
import com.chinexboroja.resources.employeemanagement.EmployeeController;
import com.chinexboroja.security.AppAuthenticator;
import com.chinexboroja.security.AppAuthorizer;
import com.chinexboroja.security.UserPrincipal;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import jakarta.ws.rs.client.Client;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyExampleAppApplication extends Application<MyExampleAppConfiguration> {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyExampleAppApplication.class);
    public static void main(final String[] args) throws Exception {
        new MyExampleAppApplication().run(args);
    }

    @Override
    public String getName() {
        return "MyExampleApp";
    }

    @Override
    public void initialize(final Bootstrap<MyExampleAppConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MyExampleAppConfiguration configuration,
                    final Environment environment) {
        //Implement application
        HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(resource);

        environment.jersey().register(Message.class);

        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        //Registering the SongWriters handler

        // preload songwriters
        List<SongWriter> preload = new ArrayList<>();
        configuration.getSongWriters().forEach(sw -> preload.add(new SongWriter(sw, new ArrayList<>())));
        SongWriterRepository songWriterRepository = new SongWriterRepository(preload);
        SongWriters songWriters = new SongWriters(songWriterRepository);
        environment.jersey().register(songWriters);

        LOGGER.info("Registering Jersey Client");
        final Client client = new JerseyClientBuilder(environment)
            .using(configuration.getJerseyClientConfiguration())
                .build(getName());

        LOGGER.info("Registering REST resources....");
        environment.jersey().register(new EmployeeController(environment.getValidator(), new EmployeeRepository()));

        LOGGER.info("Registering Application Health Check");
        environment.healthChecks().register("application", new ApplicationHealthCheck(client));


        //**** Dropwizard security ****//
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<UserPrincipal>()
            .setAuthenticator(new AppAuthenticator())
            .setAuthorizer(new AppAuthorizer())
            .setRealm("BASIC-AUTH-REALM")
            .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserPrincipal.class));

    }
}
