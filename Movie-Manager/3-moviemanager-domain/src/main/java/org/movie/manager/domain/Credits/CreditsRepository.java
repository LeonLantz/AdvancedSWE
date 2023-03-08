package org.movie.manager.domain.Credits;

import org.movie.manager.domain.Metadaten.Metadata;
import org.movie.manager.domain.Movie.Movie;

import java.util.Collection;
import java.util.UUID;

public interface CreditsRepository {

    Collection<Credits> getAllCredits();

    Credits getCredits(UUID creditsID);

    void update(Credits credits);
}
