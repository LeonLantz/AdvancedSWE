/*
Adopted/inspired by the lecture Software Engineering 4th semester DHBW 2022 by Mr. Lutz
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.movie.manager.application;
import org.movie.manager.domain.IPersistable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenericEntityManager<T extends IPersistable> {
    private Map<Object, T> allElements;

    public GenericEntityManager() {
        allElements = new HashMap();
    }

    public boolean contains(T element) {
        return this.allElements.containsKey(element.getPrimaryKey());
    }

    public void persist(T element) throws Exception {
        if (this.contains(element)) {
            throw new Exception("Element existiert bereits! ");
        } else {
            this.allElements.put(element.getPrimaryKey(), element);
        }
    }
    public void remove(T element) {
        this.allElements.remove(element.getPrimaryKey());
    }

    public T find(Class<?> c, String method, Object key) {
        Iterator var4 = this.allElements.values().iterator();

        IPersistable t;
        do {
            if (!var4.hasNext()) {
                return null;
            }

            t = (IPersistable)var4.next();
        } while(!c.isInstance(t) || !t.getPrimaryKey().equals(key));

        return (T) t;
    }

    public List<T> find(Class<?> c) {
        return (List)this.allElements.values().stream().filter((e) -> {
            return c.isInstance(e);
        }).collect(Collectors.toList());
    }

    public T find(Object key) {
        return (T) this.allElements.get(key);
    }
}

