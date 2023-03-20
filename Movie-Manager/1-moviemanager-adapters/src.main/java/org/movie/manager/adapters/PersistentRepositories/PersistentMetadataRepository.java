package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.adapters.Database;
import org.movie.manager.adapters.EntityManager;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Metadata.MetadataRepository;
import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class PersistentMetadataRepository implements MetadataRepository {
    private final EntityManager entityManager;

    private Database csvDB;

    public PersistentMetadataRepository(EntityManager entityManager, Database csvDB) {
        this.entityManager = entityManager;
        this.csvDB = csvDB;
    }

    @Override
    public Collection<Metadata> getAllMetadata() {
        return entityManager.find(Metadata.class);
    }

    @Override
    public Optional<Metadata> getMetadata(UUID metadataID) {
        return Optional.of((Metadata)entityManager.find(Metadata.class, metadataID));
    }

    @Override
    public void update(Metadata metadata) {
        entityManager.remove(entityManager.find(Movie.class, metadata.getImbDdata()));
        try {
            entityManager.persist(metadata);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
