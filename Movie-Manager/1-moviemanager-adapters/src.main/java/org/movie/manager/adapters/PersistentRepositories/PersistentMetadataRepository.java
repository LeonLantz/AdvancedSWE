package org.movie.manager.adapters.PersistentRepositories;

import org.movie.manager.adapters.CSVDatabase;
import org.movie.manager.application.GenericEntityManager;
import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Metadaten.MetadataRepository;
import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.UUID;

public class PersistentMetadataRepository implements MetadataRepository {
    private final GenericEntityManager entityManager;

    private CSVDatabase csvDB;

    public PersistentMetadataRepository(GenericEntityManager entityManager, CSVDatabase csvDB) {
        this.entityManager = entityManager;
        this.csvDB = csvDB;
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
    public void update(Metadata metadata) {
        entityManager.remove(entityManager.find(Movie.class, metadata.getImbDdata()));
        try {
            entityManager.persist(metadata);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
