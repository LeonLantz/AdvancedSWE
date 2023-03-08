package org.movie.manager.application.Services;

import org.movie.manager.domain.Metadaten.MetadataRepository;

public class MetadataService {

    private final MetadataRepository repository;

    public MetadataService(MetadataRepository repository) {
        super();
        this.repository = repository;
    }
}
