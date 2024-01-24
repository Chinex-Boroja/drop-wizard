package com.chinexboroja;

import com.chinexboroja.health.TemplateHealthCheck;
import com.chinexboroja.resources.HelloWorldResource;
import com.chinexboroja.resources.Message;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class MyExampleAppApplication extends Application<MyExampleAppConfiguration> {

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
    }

}
