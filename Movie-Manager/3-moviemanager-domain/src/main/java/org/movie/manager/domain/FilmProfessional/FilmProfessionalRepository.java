package org.movie.manager.domain.FilmProfessional;

import java.util.Collection;
import java.util.UUID;

public interface FilmProfessionalRepository {

    Collection<FilmProfessional> getAllFilmProfessionals();

    FilmProfessional getFilmProfessional(UUID FilmProfessionalID);

    void update(FilmProfessional filmProfessional);
}
