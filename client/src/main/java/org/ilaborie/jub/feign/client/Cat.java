package org.ilaborie.jub.feign.client;

import java.util.Objects;

/**  */
public class Cat {

    private String id;
    private String name;
    private CatRace race;
    
    @Override
    public String toString() {
        return String.format("Cat{id='%s', name='%s', race=%s}", id, name, race);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CatRace getRace() {
        return race;
    }

    public void setRace(CatRace race) {
        this.race = race;
    }

    public enum CatRace {
        Angora, Bengal, Cyprus, Persian, Ragdoll, Siamese, Sphynx;
    }
}
