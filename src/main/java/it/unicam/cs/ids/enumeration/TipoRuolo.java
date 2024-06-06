package it.unicam.cs.ids.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum TipoRuolo implements GrantedAuthority {
    TURISTA,
    CONTRIBUTOR,
    CURATORE,
    ANIMATORE,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
