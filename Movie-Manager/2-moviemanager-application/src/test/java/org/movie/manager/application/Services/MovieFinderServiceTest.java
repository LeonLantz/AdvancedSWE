package org.movie.manager.application.Services;

import static org.assertj.core.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Metadata.MetadataID;
import org.movie.manager.domain.Metadata.MetadataRepository;
import org.movie.manager.domain.Metadata.Rating;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieRepository;
import org.movie.manager.domain.Persistable;

import java.util.*;
import java.util.stream.Stream;

public class MovieFinderServiceTest {
    @Test
    public void shouldGetMovieByUUID() {
        UUID testUUID = UUID.randomUUID();

        Movie movie = EasyMock.createMock(Movie.class);
        EasyMock.replay(movie);
        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        EasyMock.expect(movieRepository.getMovie(testUUID)).andReturn(Optional.of(movie));
        EasyMock.replay(movieRepository);

        MovieFinderService codeUnderTest = new MovieFinderService(movieRepository, null, null);

        Optional<Movie> optionalMovie = codeUnderTest.getMovie(testUUID);

        assertThat(optionalMovie).hasValue(movie);
    }

    @Test
    public void shouldNotGetMovieByInvalidUUID() {
        UUID wrongUUID = UUID.randomUUID();

        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        EasyMock.expect(movieRepository.getMovie(wrongUUID)).andReturn(Optional.empty());
        EasyMock.replay(movieRepository);

        MovieFinderService codeUnderTest = new MovieFinderService(movieRepository, null, null);

        Optional<Movie> optionalMovie = codeUnderTest.getMovie(wrongUUID);

        assertThat(optionalMovie).isEmpty();
    }

    @Test
    public void shouldGetAllMovies() {
        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        Movie movie1 = EasyMock.createMock(Movie.class);
        Movie movie2 = EasyMock.createMock(Movie.class);
        Movie movie3 = EasyMock.createMock(Movie.class);
        EasyMock.expect(movieRepository.getAllMovies()).andReturn(List.of(movie1, movie2, movie3));

        EasyMock.replay(movieRepository);
        MovieFinderService codeUnderTest = new MovieFinderService(movieRepository, null, null);

        Collection<Movie> movieCollection = codeUnderTest.getAllMovies();

        EasyMock.verify(movieRepository);
        assertThat(movieCollection)
                .hasSize(3)
                .contains(movie1)
                .contains(movie2)
                .contains(movie3);
    }

    @Test
    public void shouldGetAllMovieData() {
        UUID movieID = UUID.randomUUID();
        UUID metadataID = UUID.randomUUID();

        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        Movie movie = EasyMock.createMock(Movie.class);
        EasyMock.expect(movieRepository.getMovie(movieID)).andReturn(Optional.of(movie));
        EasyMock.replay(movieRepository);

        MetadataRepository metadataRepository = EasyMock.createMock(MetadataRepository.class);
        Metadata metadata = EasyMock.createMock(Metadata.class);
        EasyMock.expect(metadataRepository.getMetadata(metadataID)).andReturn(Optional.of(metadata));
        EasyMock.replay(metadataRepository);

        MovieFinderService codeUnderTest = new MovieFinderService(movieRepository, metadataRepository, null);

        ArrayList<Persistable> allMovieData = codeUnderTest.getAllMovieData(movieID, metadataID);

        assertThat(allMovieData)
                .hasSize(2)
                .contains(movie)
                .contains(metadata);
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    public void shouldFindFilteredMovies(MovieRepository movieRepository, MetadataRepository metadataRepository, List<Filter> filterList, List<Movie> validMovies) {

        MovieFinderService codeUnderTest = new MovieFinderService(movieRepository, metadataRepository, null);

        Collection<Movie> movieCollection = codeUnderTest.getMoviesWithFilter(filterList);

        System.out.println("---Matching Movies---");
        for(Movie mov : movieCollection) {
            System.out.println(mov.getTitel());
        }

        for(Movie validMovie : validMovies) {
            assertThat(movieCollection).contains(validMovie);
        }
    }

    static Stream<Arguments> getTestData() {

        UUID uuid1 = UUID.randomUUID();
        MetadataID metadataID1 = new MetadataID(uuid1);
        Movie movie1 = new Movie(null, "Movie One", null, 1980, 85, metadataID1, null, null, null);
        Metadata metadata1 = new Metadata(metadataID1, null, null, new Rating(3), null);

        UUID uuid2 = UUID.randomUUID();
        MetadataID metadataID2 = new MetadataID(uuid2);
        Movie movie2 = new Movie(null, "Movie Two", null, 1990, 90, metadataID2, null, null, null);
        Metadata metadata2 = new Metadata(metadataID2, null, null, new Rating(5), null);

        UUID uuid3 = UUID.randomUUID();
        MetadataID metadataID3 = new MetadataID(uuid3);
        Movie movie3 = new Movie(null, "Movie Three", null, 2000, 95, metadataID3, null, null, null);
        Metadata metadata3 = new Metadata(metadataID3, null, null, new Rating(7), null);

        UUID uuid4 = UUID.randomUUID();
        MetadataID metadataID4 = new MetadataID(uuid4);
        Movie movie4 = new Movie(null, "Movie Four", null, 2010, 100, metadataID4, null, null, null);
        Metadata metadata4 = new Metadata(metadataID4, null, null, new Rating(9), null);

        MetadataRepository metadataRepository = EasyMock.createMock(MetadataRepository.class);
        EasyMock.expect(metadataRepository.getMetadata(uuid1)).andReturn(Optional.of(metadata1)).anyTimes();
        EasyMock.expect(metadataRepository.getMetadata(uuid2)).andReturn(Optional.of(metadata2)).anyTimes();
        EasyMock.expect(metadataRepository.getMetadata(uuid3)).andReturn(Optional.of(metadata3)).anyTimes();
        EasyMock.expect(metadataRepository.getMetadata(uuid4)).andReturn(Optional.of(metadata4)).anyTimes();
        EasyMock.replay(metadataRepository);

        MovieRepository movieRepository = EasyMock.createMock(MovieRepository.class);
        EasyMock.expect(movieRepository.getAllMovies()).andReturn(List.of(movie1, movie2, movie3, movie4)).anyTimes();
        EasyMock.replay(movieRepository);

        Filter filter1 = new Filter("ownratingBigger", 5);
        Filter filter2 = new Filter("ownratingSmaller", 5);
        Filter filter3 = new Filter("ownratingBigger", 4);
        Filter filter4 = new Filter("ownratingSmaller", 6);
        Filter filter5 = new Filter("ownratingSmaller", 1);

        return Stream.of(
                Arguments.of(movieRepository, metadataRepository, List.of(filter1), List.of(movie3, movie4)),
                Arguments.of(movieRepository, metadataRepository, List.of(filter2), List.of(movie1)),
                Arguments.of(movieRepository, metadataRepository, List.of(filter5), Collections.emptyList()),
                Arguments.of(movieRepository, metadataRepository, List.of(filter3), List.of(movie2, movie3, movie4)),
                Arguments.of(movieRepository, metadataRepository, List.of(filter3, filter4), List.of(movie2)),
                Arguments.of(movieRepository, metadataRepository, List.of(filter1, filter3, filter4), Collections.emptyList())
        );
    }
}



//    Collection<Movie> filteredMovies = new ArrayList<>();
//    Collection<Movie> allMovies = this.getAllMovies();
//
//        for (Movie movie : allMovies) {
//                boolean passFilter = true;
//                for (Filter filter : filters) {
//                Optional<Metadata> metadataOptional = metadataRepository.getMetadata(movie.getMetadataID().getMetadataID());
//        Metadata metadata;
//        if (metadataOptional != null) {
//        metadata = metadataOptional.get();
//
//        switch (filter.getName()) {
//        case "ownratingBigger":
//        passFilter &= metadata.getOwnRating().getRating() > (int) filter.getValue();
//        break;
//        case "ownratingSmaller":
//        passFilter &= metadata.getOwnRating().getRating() < (int) filter.getValue();
//        break;
//        case "ownership":
//        passFilter &= metadata.getAvailability().getOwnership().toString() == filter.getValue();
//        break;
//default:
//        break;
//        }
//        if (!passFilter) {
//        break;
//        }
//        }
