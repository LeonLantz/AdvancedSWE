package org.movie.manager.adapters;
import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Persistable;

import java.util.List;

public interface EntityManager {
    boolean contains(Persistable element);

    void persist(Persistable element) throws Exception;
    void remove(Persistable element);

    Persistable find(Class<?> c, Object key);

//    List<Persistable> find(Class<?> c);
    List<Movie> findMovies();
    List<FilmProfessional> findFilmProfessionals();
    List<Metadata> findMetadata();

    Persistable find(Object key);
}
