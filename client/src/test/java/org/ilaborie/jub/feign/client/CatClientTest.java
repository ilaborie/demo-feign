package org.ilaborie.jub.feign.client;

import java.util.List;
import java.util.function.Supplier;

/**  */
public class CatClientTest {

    public static void main(String[] args) {
        CatClient client = ClientBuilder.create("http://localhost:8000");

//        process("Find all", client::findAll);
        List<Cat> result = process("Find all", client::findAll);

//        process("Find by id", () -> client.findById(result.get(0).getId()));
        
//        Cat created = process("Create", () -> {
//            Cat newCat = new Cat();
//            newCat.setName("The Dude");
//            newCat.setRace(Cat.CatRace.Angora);
//            return client.create(newCat);
//        });
        
//        Cat updated = process("Update", () -> {
//            created.setRace(Cat.CatRace.Sphynx);
//            return client.update(created.getId(), created);
//        });
        
//        process("Delete", () -> client.delete(updated.getId()));
    }
    
    private static <T> T process(String name, Supplier<T> supplier) {
        System.out.println(name);
        T result = supplier.get();
        System.out.print('\t');
        System.out.println(result);
        System.out.println();
        return result;
    }
}

