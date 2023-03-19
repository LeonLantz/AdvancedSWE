package org.movie.manager.domain.FilmProfessional;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface FilmProfessionalRepository {

    Collection<FilmProfessional> getAllFilmProfessionals();

    Optional<FilmProfessional> getFilmProfessional(UUID FilmProfessionalID);

    void update(FilmProfessional filmProfessional);
}
