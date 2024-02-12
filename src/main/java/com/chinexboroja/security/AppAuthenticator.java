package com.chinexboroja.security;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AppAuthenticator implements Authenticator<BasicCredentials, UserPrincipal> {

    private static final Map<String, Set<String>> VALID_USERS = ImmutableMap.of(
        "guest", ImmutableSet.of(),
        "user", ImmutableSet.of("USER"),
        "admin", ImmutableSet.of("ADMIN", "USER")
    );
    @Override
    public Optional<UserPrincipal> authenticate(BasicCredentials basicCredentials) {

        if (VALID_USERS.containsKey(basicCredentials.getUsername())
            && "password".equals(basicCredentials.getPassword())) {
            return Optional.of(new UserPrincipal(basicCredentials.getUsername(), VALID_USERS.get(basicCredentials.getUsername())));
        }
        return Optional.empty();
    }
}
