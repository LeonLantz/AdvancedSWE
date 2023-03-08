package org.movie.manager.application.Services;

import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Credits.CreditsRepository;

import java.util.Collection;
import java.util.UUID;

public class CreditsService {

    private final CreditsRepository repository;

    public CreditsService(CreditsRepository repository) {
        super();
        this.repository = repository;
    }

    public Collection<Credits> getAllCredits() {
        return this.repository.getAllCredits();
    }
    public Credits getCredits(UUID creditsID) {
        return this.repository.getCredits(creditsID);
    }
}
