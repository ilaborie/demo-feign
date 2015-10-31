package org.ilaborie.jub.feign.client;

import feign.Feign;
import feign.gson.GsonDecoder;

/**  */
public final class ClientBuilder {
    
    private ClientBuilder(){
        throw new AssertionError();
    }
    
    public static CatClient create(String url) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(CatClient.class, url);
    }
}
