package org.movie.manager.application.Services;

import org.junit.jupiter.api.Test;
import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.FilmProfessional.FilmProfessionalRepository;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Metadata.MetadataRepository;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;
import org.easymock.EasyMock;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieEditServiceTest {
    @Test
    public void shouldSaveNewMovie() {
        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        MetadataRepository metadataRepository = EasyMock.createMock(MetadataRepository.class);
        FilmProfessionalRepository filmProfessionalRepository = EasyMock.createMock(FilmProfessionalRepository.class);
        Movie movie = new Movie(null, null, null, 0, 0, null, null, null, null);
        Metadata metadata = new Metadata(null, null, null, null, null);
        FilmProfessional filmProfessional = new FilmProfessional(null, null, null, null, null);

        movieRepository.update(movie);
        metadataRepository.update(metadata);
        filmProfessionalRepository.update(filmProfessional);

        EasyMock.replay(movieRepository);
        EasyMock.replay(metadataRepository);
        EasyMock.replay(filmProfessionalRepository);

        MovieEditService codeUnderTest = new MovieEditService(movieRepository, metadataRepository, filmProfessionalRepository);
        codeUnderTest.saveNewMovie(movie, metadata, new ArrayList<>(Arrays.asList(filmProfessional)));

        EasyMock.verify(movieRepository);
        EasyMock.verify(metadataRepository);
        EasyMock.verify(filmProfessionalRepository);
    }

}
