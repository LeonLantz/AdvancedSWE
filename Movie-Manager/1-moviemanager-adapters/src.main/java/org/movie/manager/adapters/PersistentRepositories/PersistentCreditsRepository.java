package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Credits.CreditsRepository;

import java.util.Collection;
import java.util.UUID;

public class PersistentCreditsRepository implements CreditsRepository {

    private final GenericEntityManager entityManager;


    public PersistentCreditsRepository(GenericEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Credits> getAllCredits() {
        return entityManager.find(Credits.class);
    }

    @Override
    public Credits getMetadata(UUID creditsID) {
        return (Credits)entityManager.find(Credits.class, creditsID);
    }

    @Override
    public void remove(UUID creditsID) {

    }

    @Override
    public void update(Credits credits) {

    }
}
