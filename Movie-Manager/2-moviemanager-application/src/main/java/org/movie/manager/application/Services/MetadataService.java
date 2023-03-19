package org.movie.manager.application.Services;

import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Metadaten.MetadataRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MetadataService {

    private final MetadataRepository repository;

    public MetadataService(MetadataRepository repository) {
        super();
        this.repository = repository;
    }
    public Collection<Metadata> getAllMetadata() {
        return this.repository.getAllMetadata();
    }
    public Optional<Metadata> getMetadata(UUID metadataID) {
        return this.repository.getMetadata(metadataID);
    }
}
