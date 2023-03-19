package org.movie.manager.application.Services;

import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.FilmProfessional.FilmProfessionalRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class FilmProfessionalService {

    private final FilmProfessionalRepository repository;

    public FilmProfessionalService(FilmProfessionalRepository repository) {
        super();
        this.repository = repository;
    }

    public Collection<FilmProfessional> getAllFilmProfessionals() {
        return this.repository.getAllFilmProfessionals();
    }
    public Optional<FilmProfessional> getFilmProfessional(UUID filmProfessionalID) {
        return this.repository.getFilmProfessional(filmProfessionalID);
    }
}
