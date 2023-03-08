package org.movie.manager.domain.Metadaten;

import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.UUID;

public interface MetadataRepository {

    Collection<Metadata> getAllMetadata();

    Metadata getMetadata(UUID metadataID);

    void remove(UUID metadataID);

    void update(Movie Metadata);
}
