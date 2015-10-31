package org.ilaborie.jub.feign.client;

import feign.Feign;

/**  */
public final class ClientBuilder {
    
    private ClientBuilder(){
        throw new AssertionError();
    }
    
    public static CatClient create(String url) {
        return Feign.builder()
                .target(CatClient.class, url);
    }
}
