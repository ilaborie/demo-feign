package org.ilaborie.jub.feign.client;

import feign.RequestLine;

import java.util.List;

/**  */
public interface CatClient {

    @RequestLine("GET /cat")
    List<Cat> findAll();

//    Cat findById(String id);
//    Cat create(Cat cat);
//    Cat update(String id, Cat cat);
//    Cat delete(String id);
}
