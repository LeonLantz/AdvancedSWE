package org.movie.manager.adapters;

import org.assertj.core.api.SoftAssertions;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.FilmProfessional.FilmProfessionalID;
import org.movie.manager.domain.Metadata.*;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class EntityFactoryTest {
    @Test
    public void shouldCreateMovie() throws Exception {
        EntityManager entityManager = EasyMock.createMock(EntityManager.class);
        Movie movie = new Movie(new MovieID(UUID.fromString("739100ba-f611-4355-9975-a6ccc1569890")), "12 Angry Men", "Drama", 1957, 96,  new MetadataID(UUID.fromString("317b5174-f3d1-4690-9038-3e205522c773")), null, null, null);
        String[] data = {
                "739100ba-f611-4355-9975-a6ccc1569890",
                "12 Angry Men",
                "Drama",
                "1957",
                "317b5174-f3d1-4690-9038-3e205522c773",
                "96",
                "",
                "",
                ""
        };
        EntityFactory codeUnderTest = new EntityFactory(entityManager, null);
        Movie createdMovie = (Movie) codeUnderTest.createMovie(data);

        SoftAssertions movieBundle = new SoftAssertions();
        movieBundle.assertThat(movie.getPrimaryKey()).isEqualTo(createdMovie.getPrimaryKey());
        movieBundle.assertThat(movie.getTitel()).isEqualTo(createdMovie.getTitel());
        movieBundle.assertThat(movie.getGenre()).isEqualTo(createdMovie.getGenre());
        movieBundle.assertThat(movie.getReleaseYear()).isEqualTo(createdMovie.getReleaseYear());
        movieBundle.assertThat(movie.getMetadataID()).isEqualTo(createdMovie.getMetadataID());
        movieBundle.assertAll();
    }

    @Test
    public void shouldCreateMetadata() throws Exception {
        EntityManager entityManager = EasyMock.createMock(EntityManager.class);
        Metadata metadata = new Metadata(new MetadataID(UUID.fromString("317b5174-f3d1-4690-9038-3e205522c773")), new Availability(Ownership.PHYSICALLY, "DVD", ""), new IMDbData("tt0050083", 9.0, 97), new Rating(10), new MovieID(UUID.fromString("739100ba-f611-4355-9975-a6ccc1569890")));
        String[] data = {
                "317b5174-f3d1-4690-9038-3e205522c773",
                "PHYSICALLY",
                "DVD",
                "",
                "tt0050083",
                "9.0",
                "97",
                "10",
                "739100ba-f611-4355-9975-a6ccc1569890"
        };
        EntityFactory codeUnderTest = new EntityFactory(entityManager, null);
        Metadata createdMetadata = (Metadata) codeUnderTest.createMetadata(data);

        SoftAssertions metadataBundle = new SoftAssertions();
        metadataBundle.assertThat(metadata.getPrimaryKey()).isEqualTo(createdMetadata.getPrimaryKey());
        metadataBundle.assertThat(metadata.getAvailability().getDescription()).isEqualTo(createdMetadata.getAvailability().getDescription());
        metadataBundle.assertThat(metadata.getAvailability().getNameOrMedium()).isEqualTo(createdMetadata.getAvailability().getNameOrMedium());
        metadataBundle.assertThat(metadata.getAvailability().getOwnership().ordinal()).isEqualTo(createdMetadata.getAvailability().getOwnership().ordinal());
        metadataBundle.assertThat(metadata.getOwnRating()).isEqualTo(createdMetadata.getOwnRating());
        metadataBundle.assertThat(metadata.getMetadataID()).isEqualTo(createdMetadata.getMetadataID());
        metadataBundle.assertAll();
    }

    @Test
    public void shouldCreateFilmProfessional() throws Exception {
        EntityManager entityManager = EasyMock.createMock(EntityManager.class);
        FilmProfessional filmProfessional = new FilmProfessional(new FilmProfessionalID(UUID.fromString("dca9d317-946e-4b5c-b586-edb3bc7cf566")), "Sidney", "Lumet", "bio", new ArrayList<>(Arrays.asList(new MovieID(UUID.fromString("739100ba-f611-4355-9975-a6ccc1569890")))));
        String[] data = {
                "dca9d317-946e-4b5c-b586-edb3bc7cf566",
                "Sidney",
                "Lumet",
                "bio",
                "739100ba-f611-4355-9975-a6ccc1569890"
        };

        EntityFactory codeUnderTest = new EntityFactory(entityManager, null);
        FilmProfessional createdFilmProfessional = (FilmProfessional) codeUnderTest.createFilmProfessional(data);

        SoftAssertions filmProfessionalBundle = new SoftAssertions();
        filmProfessionalBundle.assertThat(filmProfessional.getPrimaryKey()).isEqualTo(createdFilmProfessional.getPrimaryKey());
        filmProfessionalBundle.assertThat(filmProfessional.getBiography()).isEqualTo(createdFilmProfessional.getBiography());
        filmProfessionalBundle.assertThat(filmProfessional.getFirstName()).isEqualTo(createdFilmProfessional.getFirstName());
        filmProfessionalBundle.assertThat(filmProfessional.getSecondName()).isEqualTo(createdFilmProfessional.getSecondName());
        filmProfessionalBundle.assertThat(filmProfessional.getContributeMovies()).isEqualTo(createdFilmProfessional.getContributeMovies());
        filmProfessionalBundle.assertAll();
    }


}
