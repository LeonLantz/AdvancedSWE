package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.adapters.CSVDatabase;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Credits.Credits;
import org.movie.manager.domain.Credits.CreditsRepository;
import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.UUID;

public class PersistentCreditsRepository implements CreditsRepository {

    private final GenericEntityManager entityManager;

    private CSVDatabase csvDB;


    public PersistentCreditsRepository(GenericEntityManager entityManager, CSVDatabase csvDB) {
        this.entityManager = entityManager;
        this.csvDB = csvDB;
    }

    @Override
    public Collection<Credits> getAllCredits() {
        return entityManager.find(Credits.class);
    }

    @Override
    public Credits getCredits(UUID creditsID) {
        return (Credits)entityManager.find(Credits.class, creditsID);
    }

    @Override
    public void update(Credits credits) {
        entityManager.remove(entityManager.find(Movie.class, credits.getCreditsID()));
        try {
            entityManager.persist(credits);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
