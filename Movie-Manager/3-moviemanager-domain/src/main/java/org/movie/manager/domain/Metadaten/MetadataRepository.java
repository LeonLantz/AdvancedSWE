package org.movie.manager.domain.Metadaten;

import java.util.Collection;
import java.util.UUID;

public interface MetadataRepository {

    Collection<Metadata> getAllMetadata();

    Metadata getMetadata(UUID metadataID);

    void update(Metadata metadata);
}
