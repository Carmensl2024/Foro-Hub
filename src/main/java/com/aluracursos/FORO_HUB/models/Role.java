package com.aluracursos.FORO_HUB.models;

import lombok.Getter;

import java.security.Permissions;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {
    ADMIN(Arrays.asList(Permisos.values())),
    USER(Arrays.asList(Permisos.READ, Permisos.UPDATE));

    private final List<Permisos> permisos;

    Role(List<Permisos> permisos) {
        this.permisos = permisos;
    }

//    public String getName() {
//        return name();
//    }
}
