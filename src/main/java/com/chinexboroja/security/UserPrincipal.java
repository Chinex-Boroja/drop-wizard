package com.chinexboroja.security;

import java.security.Principal;
import java.util.Set;
import javax.security.auth.Subject;

public class UserPrincipal implements Principal {

    private final String name;
    private final Set<String> roles;

    public UserPrincipal(String name) {
        this.name = name;
        this.roles = null;
    }

    public UserPrincipal(String name, Set<String> roles) {
        this.name = name;
        this.roles = roles;
    }
    @Override
    public String getName() {
        return name;
    }

    public int getId() {
        return (int) (Math.random() * 100);
    }

    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
