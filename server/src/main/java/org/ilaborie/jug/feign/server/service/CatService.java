package org.ilaborie.jug.feign.server.service;

import static org.ilaborie.jug.feign.server.model.Cat.CatRace;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.ilaborie.jug.feign.server.model.Cat;

/**  */
public class CatService {
    
    private final Map<String, Cat> cats;

    public CatService() {
        super();
        cats = new ConcurrentHashMap<>();
        dummy();
    }

    private void dummy() {
        List<CatRace> races = Arrays.asList(CatRace.values());
        Supplier<CatRace> randomRace = () -> {
            Collections.shuffle(races);
            return races.get(0);
        };
        Stream.of("Isis", "Nut", "Anubis", "Osiris", "Thoth")
                .map(name -> new Cat(name, randomRace.get()))
                .forEach(cat -> cats.put(cat.getId(), cat));
    }

    public Collection<Cat> findAll(){
        return cats.values().stream()
                .collect(Collectors.toList());
    }
    
    public Optional<Cat> findById(String catId) {
        assert catId!=null;
        return Optional.ofNullable(cats.get(catId));
    }
    
    public Cat create(String name, CatRace race) {
        assert name!=null;
        assert race!=null;
        Cat result = new Cat(name, race);
        cats.put(result.getId(), result);
        return result;
    }
    
    public Cat update(String catId, Cat cat) throws NoSuchElementException{
        assert catId!=null;
        assert cat!=null;
        Cat internalCat = findById(catId).orElseThrow(() -> new NoSuchElementException(catId));
        internalCat.setName(cat.getName());
        internalCat.setRace(cat.getRace());
        return internalCat;
    }
    
    public Cat delete(String catId) {
        return cats.remove(catId);
    }
    
    
}
