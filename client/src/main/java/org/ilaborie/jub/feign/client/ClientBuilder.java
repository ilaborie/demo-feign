package org.ilaborie.jub.feign.client;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**  */
public final class ClientBuilder {
    
    private ClientBuilder(){
        throw new AssertionError();
    }
    
    public static CatClient create(String url) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .encoder(new GsonEncoder())
                .requestInterceptor(template -> template.header("Role", "BigBoss"))
                .target(CatClient.class, url);
    }
}
