package org.ilaborie.jub.feign.client;

import feign.RequestLine;

/**  */
public interface CatClient {

    @RequestLine("GET /cat")
    String findAll();

//    List<Cat> findAll();
//    Cat findById(String id);
//    Cat create(Cat cat);
//    Cat update(String id, Cat cat);
//    Cat delete(String id);
}
