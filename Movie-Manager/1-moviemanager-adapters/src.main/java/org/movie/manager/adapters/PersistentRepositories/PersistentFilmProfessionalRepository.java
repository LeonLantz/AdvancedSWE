package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.adapters.Database;
import org.movie.manager.adapters.EntityManager;
import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.FilmProfessional.FilmProfessionalRepository;
import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class PersistentFilmProfessionalRepository implements FilmProfessionalRepository {

    private final EntityManager entityManager;

    private Database csvDB;


    public PersistentFilmProfessionalRepository(EntityManager entityManager, Database csvDB) {
        this.entityManager = entityManager;
        this.csvDB = csvDB;
    }

    @Override
    public Collection<FilmProfessional> getAllFilmProfessionals() {
        return entityManager.findFilmProfessionals();
    }

    @Override
    public Optional<FilmProfessional> getFilmProfessional(UUID filmProfessionalID) {
        return Optional.of((FilmProfessional)entityManager.find(FilmProfessional.class, filmProfessionalID));
    }

    @Override
    public void update(FilmProfessional filmProfessional) {
        entityManager.remove(entityManager.find(Movie.class, filmProfessional.getFilmProfessionalID()));
        try {
            entityManager.persist(filmProfessional);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
