package org.movie.manager.application.Services;

import org.movie.manager.domain.Credits.CreditsRepository;

public class CreditsService {

    private final CreditsRepository repository;

    public CreditsService(CreditsRepository repository) {
        super();
        this.repository = repository;
    }
}
