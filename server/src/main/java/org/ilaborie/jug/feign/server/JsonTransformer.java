package org.ilaborie.jug.feign.server;

import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

import com.google.gson.Gson;

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
