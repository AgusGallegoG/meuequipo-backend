package com.web.meuequipo.core.audit;

import com.web.meuequipo.core.auth.security.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails customUserDetails) {
            return Optional.of(customUserDetails.getUsername());
        }

        return Optional.of(principal.toString()); // SI no es instancia de CustomUserDetails -> Auditor principal.toString -> no deberia pasar nunca
    }
}
