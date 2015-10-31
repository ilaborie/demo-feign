package org.ilaborie.jub.feign.client;

import java.util.List;

import feign.Param;
import feign.RequestLine;

/**  */
public interface CatClient {

    @RequestLine("GET /cat")
    List<Cat> findAll();

    @RequestLine("GET /cat/{id}")
    Cat findById(@Param("id") String id);
    
//    Cat create(Cat cat);
//    Cat update(String id, Cat cat);
//    Cat delete(String id);
}
