/*
Adopted/inspired by the lecture Software Engineering 4th semester DHBW 2022 by Mr. Lutz
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.movie.manager.plugin.genericentitymanager;
import org.movie.manager.adapters.EntityManager;
import org.movie.manager.domain.FilmProfessional.FilmProfessional;
import org.movie.manager.domain.Metadata.Metadata;
import org.movie.manager.domain.Movie.Movie;
import org.movie.manager.domain.Persistable;

import java.util.*;
import java.util.stream.Collectors;

public class GenericEntityManager implements EntityManager {
    private Map<Object, Persistable> allElements;

    public GenericEntityManager() {
        allElements = new HashMap();
    }

    public boolean contains(Persistable element) {
        return this.allElements.containsKey(element.getPrimaryKey());
    }

    public void persist(Persistable element) throws Exception {
        if (this.contains(element)) {
            throw new Exception("Element exist! ");
        } else {
            this.allElements.put(element.getPrimaryKey(), element);
        }
    }
    public void remove(Persistable element) {
        this.allElements.remove(element.getPrimaryKey());
    }

    public Persistable find(Class<?> c, Object key) {
        Iterator var4 = this.allElements.values().iterator();

        Persistable t;
        do {
            if (!var4.hasNext()) {
                return null;
            }

            t = (Persistable)var4.next();
        } while(!c.isInstance(t) || !t.getPrimaryKey().equals(key));

        return (Persistable) t;
    }

    public List<Movie> findMovies() {
        return (List)this.allElements.values().stream().filter((e) -> {
            return Movie.class.isInstance(e);
        }).collect(Collectors.toList());
    }

    public List<FilmProfessional> findFilmProfessionals() {
        return (List)this.allElements.values().stream().filter((e) -> {
            return FilmProfessional.class.isInstance(e);
        }).collect(Collectors.toList());
    }

    public List<Metadata> findMetadata() {
        return (List)this.allElements.values().stream().filter((e) -> {
            return Metadata.class.isInstance(e);
        }).collect(Collectors.toList());
    }

    public Persistable find(Object key) {
        return (Persistable) this.allElements.get(key);
    }
}

