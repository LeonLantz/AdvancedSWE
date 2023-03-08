package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Metadaten.MetadataRepository;
import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.UUID;

public class PersistentMetadataRepository implements MetadataRepository {
    private final GenericEntityManager entityManager;


    public PersistentMetadataRepository(GenericEntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Collection<Metadata> getAllMetadata() {
        return entityManager.find(Metadata.class);
    }

    @Override
    public Metadata getMetadata(UUID metadataID) {
        return (Metadata)entityManager.find(Metadata.class, metadataID);
    }

    @Override
    public void remove(UUID movieID) {

    }

    @Override
    public void update(Movie movie) {

    }
}
