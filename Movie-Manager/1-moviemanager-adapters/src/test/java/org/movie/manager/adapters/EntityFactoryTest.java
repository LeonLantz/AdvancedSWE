package org.movie.manager.adapters;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.movie.manager.domain.Metadata.*;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Movie.MovieID;
import org.movie.manager.domain.Persistable;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class EntityFactoryTest {
    @Test
    public void shouldCreateMovie() throws Exception {
        EntityManager entityManager = EasyMock.createMock(EntityManager.class);
        Persistable movie = new Movie(new MovieID(UUID.fromString("739100ba-f611-4355-9975-a6ccc1569890")), "12 Angry Men", "Drama", 1957, 96,  new MetadataID(UUID.fromString("317b5174-f3d1-4690-9038-3e205522c773")), null, null, null);
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
        Persistable createdMovie = codeUnderTest.createMovie(data);

        assertThat(movie.getPrimaryKey())
                .isEqualTo(createdMovie.getPrimaryKey());
    }

    @Test
    public void shouldCreateMetadata() throws Exception {
        EntityManager entityManager = EasyMock.createMock(EntityManager.class);
        Persistable metadata = new Metadata(new MetadataID(UUID.fromString("317b5174-f3d1-4690-9038-3e205522c773")), new Availability(Ownership.PHYSICALLY, "DVD", ""), new IMBDdata("tt0050083", 9.0, 97), new Rating(10), new MovieID(UUID.fromString("739100ba-f611-4355-9975-a6ccc1569890")));
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
        Persistable createdMetadata = codeUnderTest.createMetadata(data);

        assertThat(metadata.getPrimaryKey())
                .isEqualTo(createdMetadata.getPrimaryKey());
    }
}
