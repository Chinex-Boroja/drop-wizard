package com.chinexboroja.security;

import io.dropwizard.auth.AuthorizationContext;
import io.dropwizard.auth.Authorizer;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.checkerframework.checker.nullness.qual.Nullable;

public class AppAuthorizer implements Authorizer<UserPrincipal> {
    @Override
    public boolean authorize(UserPrincipal user, String role, @Nullable ContainerRequestContext containerRequestContext) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }

    @Override
    public AuthorizationContext<UserPrincipal> getAuthorizationContext(UserPrincipal principal, String role,
                                                                       @Nullable ContainerRequestContext requestContext) {
        return Authorizer.super.getAuthorizationContext(principal, role, requestContext);
    }
}
